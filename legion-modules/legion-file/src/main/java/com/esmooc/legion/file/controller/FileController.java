package com.esmooc.legion.file.controller;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.OssSetting;
import com.esmooc.legion.file.entity.File;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @ResponseBody
    public Result<Page<File>> getFileList(File file,
                                          SearchVo searchVo,
                                          PageVo pageVo,
                                          @RequestParam(required = false, defaultValue = "false") Boolean getCurrUser) {

        if (getCurrUser) {
            file.setCreateBy(SecurityUtil.getUser().getUsername());
        }
        Page<File> page = fileService.findByCondition(file, searchVo, PageUtil.initPage(pageVo));
        OssSetting os = new Gson().fromJson(settingService.get(SettingConstant.LOCAL_OSS).getValue(), OssSetting.class);
        for (File e : page.getContent()) {
            if (e.getLocation() != null && CommonConstant.OSS_LOCAL.equals(e.getLocation())) {
                String url = os.getHttp() + os.getEndpoint() + "/";
                entityManager.detach(e);
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
        File file = fileService.get(id);
        if (file.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        if (!user.getUsername().equals(file.getCreateBy())) {
            return ResultUtil.error("你无权删除非本人文件");
        }
        // 特殊处理本地服务器
        String key = file.getFKey();
        if (CommonConstant.OSS_LOCAL.equals(file.getLocation())) {
            key = file.getUrl();
        }
        try {
            fileManageFactory.getFileManage(file.getLocation()).deleteFile(key);
        } catch (Exception e) {
            log.error("服务器删除文件失败，ID：" + file.getId() + " 存储位置：" + file.getLocation());
        }
        fileService.delete(id);
        redisTemplate.delete("file::" + id);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/renameUserFile", method = RequestMethod.POST)
    @ApiOperation(value = "用户文件重命名")
    @ResponseBody
    @CacheEvict(key = "#id")
    public Result<Object> renameUserFile(@RequestParam String id,
                                         @RequestParam String newName) {

        User user = SecurityUtil.getUser();
        File file = fileService.get(id);
        if (file.getLocation() == null) {
            return ResultUtil.error("存储位置未知");
        }
        if (!user.getUsername().equals(file.getCreateBy())) {
            return ResultUtil.error("你无权删除非本人文件");
        }
        String oldName = file.getName();
        if (!oldName.equals(newName)) {
            file.setName(newName);
            fileService.update(file);
        }
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    @ApiOperation(value = "文件复制")
    @ResponseBody
    public Result<Object> copy(@RequestParam String id,
                               @RequestParam String key) throws Exception {

        File file = fileService.get(id);
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
    @ResponseBody
    @CacheEvict(key = "#id")
    public Result<Object> rename(@RequestParam String id,
                                 @RequestParam String newKey,
                                 @RequestParam String newName) throws Exception {

        File file = fileService.get(id);
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
        fileService.update(file);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "文件删除")
    @ResponseBody
    public Result<Object> delete(@RequestParam String[] ids) {

        for (String id : ids) {
            File file = fileService.get(id);
            if (file.getLocation() == null) {
                return ResultUtil.error("存储位置未知");
            }
            // 特殊处理本地服务器
            String key = file.getFKey();
            if (CommonConstant.OSS_LOCAL.equals(file.getLocation())) {
                key = file.getUrl();
            }
            try {
                fileManageFactory.getFileManage(file.getLocation()).deleteFile(key);
            } catch (Exception e) {
                log.error("服务器删除文件失败，ID：" + file.getId() + " 存储位置：" + file.getLocation());
            }
            fileService.delete(id);
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

        File file = fileService.findById(id);
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
