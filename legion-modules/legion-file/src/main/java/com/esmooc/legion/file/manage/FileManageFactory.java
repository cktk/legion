package com.esmooc.legion.file.manage;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.file.manage.impl.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工厂模式
 * @author Daimao
 */
@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class FileManageFactory {

    SettingService settingService;
    QiniuFileManage qiniuFileManage;
    AliFileManage aliFileManage;
    TencentFileManage tencentFileManage;
    MinioFileManage minioFileManage;
    LocalFileManage localFileManage;

    /**
     * 使用配置的服务上传时location传入null 管理文件时需传入存储位置location
     * @param location
     * @return
     */
    public FileManage getFileManage(Integer location) {

        Setting setting = settingService.getById(SettingConstant.OSS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置OSS存储服务");
        }
        String type = setting.getValue();
        if ((type.equals(SettingConstant.QINIU_OSS) && location == null) || CommonConstant.OSS_QINIU.equals(location))
            return qiniuFileManage;
        if ((type.equals(SettingConstant.ALI_OSS) && location == null) || CommonConstant.OSS_ALI.equals(location))
            return aliFileManage;
        if ((type.equals(SettingConstant.TENCENT_OSS) && location == null) || CommonConstant.OSS_TENCENT.equals(location))
            return tencentFileManage;
        if ((type.equals(SettingConstant.MINIO_OSS) && location == null) || CommonConstant.OSS_MINIO.equals(location))
            return minioFileManage;
        if ((type.equals(SettingConstant.LOCAL_OSS) && location == null) || CommonConstant.OSS_LOCAL.equals(location))
            return localFileManage;

        throw new LegionException("暂不支持该存储配置，请检查配置");

    }
}
