package com.esmooc.legion.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.file.entity.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理接口
 * @author Daimao
 */
public interface FileService extends IService<File> {

    File upload(MultipartFile file, String base64);
    File getFile(String id);
}
