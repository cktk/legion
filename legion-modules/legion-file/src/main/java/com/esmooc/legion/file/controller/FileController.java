package com.esmooc.legion.file.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SearchUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.service.UserService;
import com.esmooc.legion.core.entity.vo.OssSetting;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.manage.FileManageFactory;
import com.esmooc.legion.file.manage.impl.LocalFileManage;
import com.esmooc.legion.file.service.FileService;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "文件管理管理接口")
@RequestMapping("/legion/file")
@Transactional
@CacheConfig(cacheNames = "file")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FileController {

    FileService fileService;
    FileManageFactory fileManageFactory;
    SettingService settingService;
    UserService userService;
    RedisTemplateHelper redisTemplate;


    @PostMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<File>> getFileList(@RequestBody Map<String, Object>   search, PageVo pageVo) {


        Page<File> page = fileService.page(PageUtil.initPage(pageVo), SearchUtil.parseWhereSql(search));


        OssSetting os =  JSONUtil.toBean(settingService.getById(SettingConstant.LOCAL_OSS).getValue(), OssSetting.class);
        
        Map<String, String> map = new HashMap<>(16);


        for (File e : page.getRecords()) {
            if (e.getLocation() != null && CommonConstant.OSS_LOCAL.equals(e.getLocation())) {
                String url = os.getHttp() + os.getEndpoint() + "/";
                e.setUrl(url + e.getId());
            }
            if (StrUtil.isNotBlank(e.getCreateBy())) {
                if (!map.containsKey(e.getCreateBy())) {
                    User u = userService.findByUsername(e.getCreateBy());
                    if (u != null && StrUtil.isNotBlank(u.getNickname())) {
                        e.setNickname(u.getNickname());
                        map.put(e.getCreateBy(), u.getNickname());
                    }
                } else {
                    e.setNickname(map.get(e.getCreateBy()));
                }
            }
        }
        // GC
        map = null;
        return new ResultUtil<Page<File>>().setData(page);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    @ApiOperation(value = "文件复制")
    public Result<Object> copy(@RequestParam String id,
                               @RequestParam String key) throws Exception {

        File file = fileService.getFile(id);
        if (file.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }

        String toKey = "副本_" + key;
        // 特殊处理本地服务器
        if (CommonConstant.OSS_LOCAL.equals(file.getLocation())) {
            key = file.getUrl();
        }
        String newUrl = fileManageFactory.getFileManage(file.getLocation()).copyFile(key, toKey);
        File newFile = new File().setName(file.getName()).setFKey(toKey).setSize(file.getSize()).setType(file.getType())
                .setLocation(file.getLocation()).setUrl(newUrl);
        fileService.save(newFile);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/rename", method = RequestMethod.POST)
    @ApiOperation(value = "文件重命名")
    @CacheEvict(key = "#id")
    public Result<Object> upload(@RequestParam String id,
                                 @RequestParam String newKey,
                                 @RequestParam String newName) throws Exception {

        File file = fileService.getFile(id);
        if (file.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        String newUrl = "", oldKey = file.getFKey();
        if (!oldKey.equals(newKey)) {
            // 特殊处理本地服务器
            if (CommonConstant.OSS_LOCAL.equals(file.getLocation())) {
                oldKey = file.getUrl();
            }
            newUrl = fileManageFactory.getFileManage(file.getLocation()).renameFile(oldKey, newKey);
        }
        file.setName(newName);
        file.setFKey(newKey);
        if (!oldKey.equals(newKey)) {
            file.setUrl(newUrl);
        }
        fileService.updateById(file);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "文件删除")
    public Result<Object> delete(@RequestParam String[] ids) {

        for (String id : ids) {
            File file = fileService.getFile(id);
            if (file == null) {
                return ResultUtil.error("文件不存在");
            }
            if (file.getLocation() == null) {
                return ResultUtil.error("存储位置未知");
            }
            // 特殊处理本地服务器
            String key = file.getFKey();
            if (CommonConstant.OSS_LOCAL.equals(file.getLocation())) {
                key = file.getUrl();
            }
            fileManageFactory.getFileManage(file.getLocation()).deleteFile(key);
            fileService.removeById(id);
            redisTemplate.delete("file::" + id);
        }
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "本地存储预览文件")
    public void view(@PathVariable String id,
                     @RequestParam(required = false) String filename,
                     @RequestParam(required = false, defaultValue = "false") Boolean preview,
                     @RequestParam(required = false, defaultValue = "UTF-8") String charset,
                     HttpServletResponse response) throws IOException {

        File file = fileService.getFile(id);
        if (file == null) {
            throw new LegionException("文件ID：" + id + "不存在");
        }
        if (StrUtil.isBlank(filename)) {
            filename = file.getFKey();
        }

        if (!preview) {
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        }
        response.setContentLengthLong(file.getSize());
        response.setContentType(file.getType() + ";charset=" + charset);
        response.addHeader("Accept-Ranges", "bytes");
        if (file.getSize() != null && file.getSize() > 0) {
            response.addHeader("Content-Range", "bytes " + 0 + "-" + (file.getSize() - 1) + "/" + file.getSize());
        }
        response.setBufferSize(10 * 1024 * 1024);
        LocalFileManage.view(file.getUrl(), response);
    }
}
