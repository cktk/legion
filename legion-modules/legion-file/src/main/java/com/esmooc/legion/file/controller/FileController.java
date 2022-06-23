package com.esmooc.legion.file.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.entity.vo.OssSetting;
import com.esmooc.legion.file.entity.LegionFile;
import com.esmooc.legion.file.manage.FileManageFactory;
import com.esmooc.legion.file.manage.impl.LocalFileManage;
import com.esmooc.legion.file.service.FileService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


/**
 * @author Exrick
 */
@Slf4j
@Controller
@Api(tags = "文件管理管理接口")
@RequestMapping("/legion/file")
@Transactional
@CacheConfig(cacheNames = "file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileManageFactory fileManageFactory;

    @Autowired
    private SettingService settingService;

    @Autowired
    private RedisTemplateHelper redisTemplate;



    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @ResponseBody
    public Result<IPage<LegionFile>> getFileList(LegionFile legionFile,
                                                 SearchVo searchVo,
                                                 PageVo pageVo,
                                                 @RequestParam(required = false, defaultValue = "false") Boolean getCurrUser) {

        if (getCurrUser) {
            legionFile.setCreateBy(SecurityUtil.getUser().getUsername());
        }
        IPage<LegionFile> page = fileService.findByCondition(legionFile, searchVo, pageVo);
        OssSetting os = new Gson().fromJson(settingService.get(SettingConstant.LOCAL_OSS).getValue(), OssSetting.class);
        for (LegionFile e : page.getRecords()) {
            if (e.getLocation() != null && CommonConstant.OSS_LOCAL.equals(e.getLocation())) {
                String url = os.getHttp() + os.getEndpoint() + "/";
                e.setUrl(url + e.getId());
            }
        }
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/deleteUserFile", method = RequestMethod.POST)
    @ApiOperation(value = "当前用户文件删除")
    @ResponseBody
    public Result<Object> deleteUserFile(@RequestParam String id) {

        User user = SecurityUtil.getUser();
        LegionFile legionFile = fileService.getById(id);
        if (legionFile.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        if (!user.getUsername().equals(legionFile.getCreateBy())) {
            return ResultUtil.error("你无权删除非本人文件");
        }
        // 特殊处理本地服务器
        String key = legionFile.getFKey();
        if (CommonConstant.OSS_LOCAL.equals(legionFile.getLocation())) {
            key = legionFile.getUrl();
        }
        try {
            fileManageFactory.getFileManage(legionFile.getLocation()).deleteFile(key);
        } catch (Exception e) {
            log.error("服务器删除文件失败，ID：" + legionFile.getId() + " 存储位置：" + legionFile.getLocation());
        }
        fileService.removeById(id);
        redisTemplate.delete("LegionFile::" + id);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/renameUserFile", method = RequestMethod.POST)
    @ApiOperation(value = "用户文件重命名")
    @ResponseBody
    @CacheEvict(key = "#id")
    public Result<Object> renameUserFile(@RequestParam String id,
                                         @RequestParam String newName) {

        User user = SecurityUtil.getUser();
        LegionFile legionFile = fileService.getById(id);
        if (legionFile.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        if (!user.getUsername().equals(legionFile.getCreateBy())) {
            return ResultUtil.error("你无权删除非本人文件");
        }
        String oldName = legionFile.getName();
        if (!oldName.equals(newName)) {
            legionFile.setName(newName);
            fileService.updateById(legionFile);
        }
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    @ApiOperation(value = "文件复制")
    @ResponseBody
    public Result<Object> copy(@RequestParam String id,
                               @RequestParam String key) {

        LegionFile legionFile = fileService.getById(id);
        if (legionFile.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }

        String toKey = "副本_" + key;
        // 特殊处理本地服务器
        if (CommonConstant.OSS_LOCAL.equals(legionFile.getLocation())) {
            key = legionFile.getUrl();
        }
        String newUrl = fileManageFactory.getFileManage(legionFile.getLocation()).copyFile(key, toKey);
        LegionFile newLegionFile = new LegionFile().setName(legionFile.getName()).setFKey(toKey).setSize(legionFile.getSize()).setType(legionFile.getType())
                .setLocation(legionFile.getLocation()).setUrl(newUrl);
        fileService.save(newLegionFile);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/rename", method = RequestMethod.POST)
    @ApiOperation(value = "文件重命名")
    @ResponseBody
    @CacheEvict(key = "#id")
    public Result<Object> rename(@RequestParam String id,
                                 @RequestParam String newKey,
                                 @RequestParam String newName){

        LegionFile legionFile = fileService.getById(id);
        if (legionFile.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        String newUrl = "", oldKey = legionFile.getFKey();
        if (!oldKey.equals(newKey)) {
            // 特殊处理本地服务器
            if (CommonConstant.OSS_LOCAL.equals(legionFile.getLocation())) {
                oldKey = legionFile.getUrl();
            }
            newUrl = fileManageFactory.getFileManage(legionFile.getLocation()).renameFile(oldKey, newKey);
        }
        legionFile.setName(newName);
        legionFile.setFKey(newKey);
        if (!oldKey.equals(newKey)) {
            legionFile.setUrl(newUrl);
        }
        fileService.updateById(legionFile);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "文件删除")
    @ResponseBody
    public Result<Object> delete(@RequestParam String[] ids) {

        for (String id : ids) {
            LegionFile legionFile = fileService.getById(id);
            if (legionFile.getLocation() == null) {
                return ResultUtil.error("存储位置未知");
            }
            // 特殊处理本地服务器
            String key = legionFile.getFKey();
            if (CommonConstant.OSS_LOCAL.equals(legionFile.getLocation())) {
                key = legionFile.getUrl();
            }
            try {
                fileManageFactory.getFileManage(legionFile.getLocation()).deleteFile(key);
            } catch (Exception e) {
                log.error("服务器删除文件失败，ID：" + legionFile.getId() + " 存储位置：" + legionFile.getLocation());
            }
            fileService.removeById(id);
            redisTemplate.delete("LegionFile::" + id);
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

        LegionFile legionFile = fileService.getById(id);
        if (legionFile == null) {
            throw new LegionException("文件ID：" + id + "不存在");
        }
        if (StrUtil.isBlank(filename)) {
            filename = legionFile.getFKey();
        }

        if (!preview) {
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        }
        response.setContentLengthLong(legionFile.getSize());
        response.setContentType(legionFile.getType() + ";charset=" + charset);
        response.addHeader("Accept-Ranges", "bytes");
        if (legionFile.getSize() != null && legionFile.getSize() > 0) {
            response.addHeader("Content-Range", "bytes " + 0 + "-" + (legionFile.getSize() - 1) + "/" + legionFile.getSize());
        }
        response.setBufferSize(10 * 1024 * 1024);
        LocalFileManage.view(legionFile.getUrl(), response);
    }
}
