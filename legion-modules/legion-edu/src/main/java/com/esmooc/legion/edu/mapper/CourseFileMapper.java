package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.CourseFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程文件附件业务Mapper接口
 *
 * @author sun
 * @date 2020-12-28
 */
@Mapper
public interface CourseFileMapper {
    /**
     * 查询课程文件附件业务
     *
     * @param id 课程文件附件业务ID
     * @return 课程文件附件业务
     */
    CourseFile selectBizCourseFileById(String id);

    /**
     * 查询课程文件附件业务列表
     *
     * @param courseFile 课程文件附件业务
     * @param page
     * @return 课程文件附件业务集合
     */
    IPage<CourseFile> selectBizCourseFileList(@Param("courseFile") CourseFile courseFile, Page page);

    /**
     * 新增课程文件附件业务
     *
     * @param courseFile 课程文件附件业务
     * @return 结果
     */
    int insertBizCourseFile(CourseFile courseFile);

    /**
     * 修改课程文件附件业务
     *
     * @param courseFile 课程文件附件业务
     * @return 结果
     */
    int updateBizCourseFile(CourseFile courseFile);

    int updateBizCourseFilesNumber(CourseFile courseFile);

    /**
     * 删除课程文件附件业务
     *
     * @param id 课程文件附件业务ID
     * @return 结果
     */
    int deleteBizCourseFileById(String id);

    /**
     * 批量删除课程文件附件业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizCourseFileByIds(String[] ids);

    int deleteBizCourseFileByCourseIdANDDelFlag(@Param("courseId") String courseId, @Param("fileType") Long fileType, @Param("delFlag") Integer delFlag);

    List<String> selectBizCourseFileNameById(String courseId);

    List<CourseFile> selectBizCourseFile(String courseId);

}
