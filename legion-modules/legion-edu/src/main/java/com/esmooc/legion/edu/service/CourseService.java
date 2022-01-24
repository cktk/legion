package com.esmooc.legion.edu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.CourseVO;
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
public interface CourseService {
    /**
     * 查询课程业务
     *
     * @param id 课程业务ID
     * @return 课程业务
     */
    Course selectBizCourseById(String id);

    /**
     * 查询课程业务列表
     *
     * @param course 课程业务
     * @return 课程业务集合
     */
    List<Course> selectBizCourseList(Course course);

    IPage<CourseVO> selectBizCourseVOList(CourseVO bizCourse, Page page);

    IPage<CourseVO> selectBizAuditCourseVOList(CourseVO bizCourse, Page page);

    int selectHomeStatistics(int roleId, String learningScope, int courseType);

    int selectcourseSize(int courseType);

    List<HomePageVO> selectcourse(int courseType);

    List<HomePageVO> selectcourse1(int courseType, String learningScope);

    String selectcourseVideoPeriod(String learningScope, long userId);

    String selectcoursePdfPeriod(String learningScope, Long userId);

    /**
     * 新增课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    int insertBizCourse(Course course);

    /**
     * 修改课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    int updateBizCourse(Course course);

    int updateBizAuditCourse(String id, int courseType);

    /**
     * 批量删除课程业务
     *
     * @param ids 需要删除的课程业务ID
     * @return 结果
     */
    int deleteBizCourseByIds(String[] ids);

    /**
     * 删除课程业务信息
     *
     * @param id 课程业务ID
     * @return 结果
     */
    int deleteBizCourseById(String id);

    int deleteBizAuditCourseById(String id, String prompt);

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
