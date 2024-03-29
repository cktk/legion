package com.esmooc.legion.file.manage.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.OssSetting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.file.manage.FileManage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author DaiMao
 */
@Slf4j
@Component
public class LocalFileManage implements FileManage {

    @Autowired
    private SettingService settingService;

    /**
     * 读取文件
     *
     * @param url
     * @param response
     */
    public static void view(String url, HttpServletResponse response) {

        File file = new File(url);
        if (!file.exists()) {
            throw new LegionException("文件不存在");
        }

        try (FileInputStream is = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(is)) {

            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = bis.read(buf)) > 0) {
                out.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error(e.toString());
            throw new LegionException("读取/下载文件出错");
        }
    }

    @Override
    public OssSetting getOssSetting() {

        Setting setting = settingService.get(SettingConstant.LOCAL_OSS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置本地存储");
        }
        return new Gson().fromJson(setting.getValue(), OssSetting.class);
    }

    /**
     * 上传
     *
     * @param inputStream
     * @param key
     * @param file
     * @return
     */
    @Override
    public String inputStreamUpload(InputStream inputStream, String key, MultipartFile file) {

        OssSetting os = getOssSetting();
        DateTime date = DateUtil.date();
        String path = os.getFilePath() + "/" + date.year() + "/" + date.monthBaseOne() + "/" + date.dayOfMonth();
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(path + "/" + key);
        if (f.exists()) {
            throw new LegionException("文件名已存在");
        }
        try {
            file.transferTo(f);
            return path + "/" + key;
        } catch (IOException e) {
            log.error(e.toString());
            throw new LegionException("上传文件出错");
        }
    }

    /**
     * 注意此处需传入url
     *
     * @param url
     * @param toKey
     * @return
     */
    @Override
    public String renameFile(String url, String toKey) {
        if (!StrUtil.startWith(url, getOssSetting().getFilePath(), true)) {
            log.error("文件配置的存储路径{} 用户想要重命名文件的路径 {} ", getOssSetting().getFilePath(), url);
            throw new LegionException("文件存储路径异常");
        }
        File old = new File(url);
        FileUtil.rename(old, toKey, false, true);
        return old.getParentFile() + "/" + toKey;
    }

    /**
     * 注意此处需传入url
     *
     * @param url
     * @param toKey
     * @return
     */
    @Override
    public String copyFile(String url, String toKey) {

        if (!StrUtil.startWith(url, getOssSetting().getFilePath(), true)) {
            log.error("文件配置的存储路径{} 用户想要吧文件拷贝到文件夹 {} ", getOssSetting().getFilePath(), url);
            throw new LegionException("文件存储路径异常");
        }

        File file = new File(url);
        String newUrl = file.getParentFile() + "/" + toKey;
        FileUtil.copy(file, new File(newUrl), true);
        return newUrl;
    }

    /**
     * 注意此处需传入url
     *
     * @param url
     */
    @Override
    public void deleteFile(String url) {

        if (!StrUtil.startWith(url, getOssSetting().getFilePath(), true)) {
            log.error("文件配置的存储路径{} 用户想要删除文件的路径 {} ", getOssSetting().getFilePath(), url);
            throw new LegionException("文件存储路径异常");
        }
        FileUtil.del(new File(url));
    }
}
