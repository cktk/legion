package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.edu.entity.CourseFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程文件附件业务Service接口
 *
 * @author sun
 * @date 2020-12-28
 */

public interface CourseFileService  extends IService<CourseFile> {

}
