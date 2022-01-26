package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import com.esmooc.legion.edu.entity.CourseFile;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/26/ 14:42
 * @Description:
 */
@Mapper
public interface CourseFileMapper extends BaseMapper<CourseFile> {

    /**
     * 查询课程文件附件业务列表
     *
     * @param courseFile 课程文件附件业务
     * @param page
     * @return 课程文件附件业务集合
     */
    IPage<CourseFile> selectBizCourseFileList(@Param("courseFile") CourseFile courseFile, Page page);

    List<CourseFile> selectBizCourseFile(String courseId);





}
