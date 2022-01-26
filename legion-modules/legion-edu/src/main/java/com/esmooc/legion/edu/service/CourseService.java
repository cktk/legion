package com.esmooc.legion.edu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.HomePageVO;
import com.esmooc.legion.edu.entity.vo.StuPdfCourseVO;
import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;

import java.util.List;

/**
 * 课程业务Service接口
 *
 * @author sun
 * @date 2020-12-28
 */
public interface CourseService  extends IService<Course> {


    IPage<Course> selectCoursePage(Course course, Page page);

    int selectHomeStatistics(int roleId, String learningScope, int courseType);

    int selectcourseSize(int courseType);

    List<HomePageVO> selectcourse(int courseType);


    /**
     * 查询学员课程业务列表
     *
     * @param stuVideoCourse
     * @param page
     * @return
     */
    IPage<StuVideoCourseVO> stuVideoCourseList(StuVideoCourseVO stuVideoCourse, Page page);

    /**
     * 查询学员课程业务列表
     *
     * @param
     * @param page
     * @return
     */
    IPage<StuPdfCourseVO> stuPdfCourseList(StuPdfCourseVO stuPdfCourse, Page page);

}
