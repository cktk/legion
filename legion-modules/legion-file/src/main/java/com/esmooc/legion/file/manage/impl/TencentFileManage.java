package com.esmooc.legion.file.manage.impl;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.OssSetting;
import com.esmooc.legion.file.manage.FileManage;
import com.google.gson.Gson;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.CopyObjectRequest;
import com.qcloud.cos.model.CopyResult;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Copy;
import com.qcloud.cos.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author DaiMao
 */
@Component
public class TencentFileManage implements FileManage {

    @Autowired
    private SettingService settingService;

    @Override
    public OssSetting getOssSetting() {

        Setting setting = settingService.get(SettingConstant.TENCENT_OSS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置腾讯云COS存储");
        }
        return new Gson().fromJson(setting.getValue(), OssSetting.class);
    }

    @Override
    public String inputStreamUpload(InputStream inputStream, String key, MultipartFile file) {

        OssSetting os = getOssSetting();

        COSCredentials cred = new BasicCOSCredentials(os.getAccessKey(), os.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(os.getBucketRegion()));
        COSClient cosClient = new COSClient(cred, clientConfig);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        PutObjectRequest putObjectRequest = new PutObjectRequest(os.getBucket(), key, inputStream, objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        return os.getHttp() + os.getEndpoint() + "/" + key;
    }

    @Override
    public String renameFile(String fromKey, String toKey) {

        OssSetting os = getOssSetting();
        copyFile(fromKey, toKey);
        deleteFile(fromKey);
        return os.getHttp() + os.getEndpoint() + "/" + toKey;
    }

    @Override
    public String copyFile(String fromKey, String toKey) {

        OssSetting os = getOssSetting();

        COSCredentials cred = new BasicCOSCredentials(os.getAccessKey(), os.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(os.getBucketRegion()));
        COSClient cosClient = new COSClient(cred, clientConfig);

        CopyObjectRequest copyObjectRequest = new CopyObjectRequest(os.getBucket(), fromKey, os.getBucket(), toKey);

        TransferManager transferManager = new TransferManager(cosClient);
        try {
            Copy copy = transferManager.copy(copyObjectRequest, cosClient, null);
            CopyResult copyResult = copy.waitForCopyResult();
        } catch (Exception e) {
            throw new LegionException("复制文件失败");
        }
        transferManager.shutdownNow();
        cosClient.shutdown();
        return os.getHttp() + os.getEndpoint() + "/" + toKey;
    }

    @Override
    public void deleteFile(String key) {

        OssSetting os = getOssSetting();

        COSCredentials cred = new BasicCOSCredentials(os.getAccessKey(), os.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(os.getBucketRegion()));
        COSClient cosClient = new COSClient(cred, clientConfig);

        cosClient.deleteObject(os.getBucket(), key);
        cosClient.shutdown();
    }
}
