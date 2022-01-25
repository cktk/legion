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
    /**
     * 查询课程文件附件业务
     *
     * @param id 课程文件附件业务ID
     * @return 课程文件附件业务
     */
    CourseFile selectCourseFileById(String id);

    /**
     * 查询课程文件附件业务列表
     *
     * @param courseFile 课程文件附件业务
     * @param page
     * @return 课程文件附件业务集合
     */
    IPage<CourseFile> selectCourseFileList(CourseFile courseFile, Page page);

    /**
     * 新增课程文件附件业务
     *
     * @param bizCourseFile 课程文件附件业务
     * @return 结果
     */
    String insertCourseFile(String courseId, long fileType, MultipartFile file, User user);


    int insertCourseFiles(String courseId, long fileType, MultipartFile[] files, User user);

    /**
     * 修改课程文件附件业务
     *
     * @param courseFile 课程文件附件业务
     * @return 结果
     */
    int updateBizCourseFile(CourseFile courseFile);

    int updateBizCourseFiles(List<CourseFile> courseFileList);

    /**
     * 批量删除课程文件附件业务
     *
     * @param ids 需要删除的课程文件附件业务ID
     * @return 结果
     */
    int deleteBizCourseFileByIds(String[] ids);

    /**
     * 删除课程文件附件业务信息
     *
     * @param id 课程文件附件业务ID
     * @return 结果
     */
    int deleteBizCourseFileById(String id);

    int deleteBizCourseFileByCourseIdANDDelFlag(String courseId, Long fileType);

    int updateBizCourseFilesNumber(List<CourseFile> courseFileList);
}