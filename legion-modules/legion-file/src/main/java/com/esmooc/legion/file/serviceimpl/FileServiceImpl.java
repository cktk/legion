package com.esmooc.legion.file.serviceimpl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.limit.RedisRaterLimiter;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.*;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.OssSetting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.manage.FileManageFactory;
import com.esmooc.legion.file.mapper.FileMapper;
import com.esmooc.legion.file.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 文件管理接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "file")
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {


    @Autowired
    private RedisTemplateHelper redisTemplate;

    @Value("${legion.maxUploadFile}")
    private Long maxUploadFile;

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Autowired
    private FileManageFactory fileManageFactory;

    @Autowired
    private SettingService settingService;

    @Autowired
    private FileService fileService;
    @Autowired
    private SecurityUtil securityUtil;


    @Cacheable(key = "#id")
    public File getFile(String id) {

        // 避免缓存穿透
        String result = redisTemplate.get("file::" + id);
        if ("null".equals(result)) {
            return null;
        }
        File file = this.getById(id);
        if (file == null) {
            redisTemplate.set("file::" + id, "null", 5L, TimeUnit.MINUTES);
        }
        return file;
    }



    public File upload(MultipartFile file, String base64) {

        if (file.getSize() > maxUploadFile * 1024 *1024 ) {
            throw new LegionException("文件大小过大，不能超过" + maxUploadFile + "MB");
        }
        Setting setting = settingService.getById(SettingConstant.OSS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException( "您还未配置OSS存储服务",500);
        }

        if (StrUtil.isNotBlank(base64)) {
            // base64上传
            file = Base64DecodeMultipartFile.base64Convert(base64);
        }

        String result = "";
        String fKey = CommonUtil.renamePic(Objects.requireNonNull(file.getOriginalFilename()));
        File f = new File();
        try {
            InputStream inputStream = file.getInputStream();
            // 上传至第三方云服务或服务器
            result = fileManageFactory.getFileManage(null).inputStreamUpload(inputStream, fKey, file);
            // 保存数据信息至数据库
            f.setLocation(getType(setting.getValue())).setName(file.getOriginalFilename()).setSize(file.getSize())
                    .setType(file.getContentType()).setFKey(fKey).setUrl(result).setNickname(securityUtil.getCurrUser().getNickname());
            fileService.save(f);
        } catch (Exception e) {
            log.error(e.toString());
            throw new LegionException( e.toString());
        }

        if (setting.getValue().equals(SettingConstant.LOCAL_OSS)) {
            OssSetting os =  JSONUtil.toBean(settingService.getById(SettingConstant.LOCAL_OSS).getValue(), OssSetting.class);
            result = os.getHttp() + os.getEndpoint() + "/" + f.getId();
        }

        return f;
    }


    public Integer getType(String type) {
        switch (type) {
            case SettingConstant.QINIU_OSS:
                return CommonConstant.OSS_QINIU;
            case SettingConstant.ALI_OSS:
                return CommonConstant.OSS_ALI;
            case SettingConstant.TENCENT_OSS:
                return CommonConstant.OSS_TENCENT;
            case SettingConstant.MINIO_OSS:
                return CommonConstant.OSS_MINIO;
            case SettingConstant.LOCAL_OSS:
                return CommonConstant.OSS_LOCAL;
            default:
                return -1;
        }
    }





}
