package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.common.utils.DateUtils;
import com.esmooc.legion.edu.common.utils.FileUtils;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.entity.Prompt;
import com.esmooc.legion.edu.entity.vo.HomePageVO;
import com.esmooc.legion.edu.entity.vo.StuPdfCourseVO;
import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;
import com.esmooc.legion.edu.mapper.CourseFileMapper;
import com.esmooc.legion.edu.mapper.CourseMapper;
import com.esmooc.legion.edu.mapper.PaperMapper;
import com.esmooc.legion.edu.mapper.PromptMapper;
import com.esmooc.legion.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程业务Service业务层处理
 *
 * @author sun
 * @date 2020-12-28
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Course> selectCoursePage(Course course, Page page) {
        course.setAudit(Constants.UNREVISED_OK);
        return this.page(page, Wrappers.query(course));
    }


    @Override
    public int selectHomeStatistics(int roleId, String learningScope, int courseType) {
        return courseMapper.selectHomeStatistics(roleId, learningScope, courseType);
    }

    @Override
    public int selectcourseSize(int courseType) {
        return courseMapper.selectcourseSize(courseType);
    }

    @Override
    public List<HomePageVO> selectcourse(int courseType) {
        return courseMapper.selectcourse(courseType);
    }

    public List<HomePageVO> selectcourse1(int courseType, String learningScope) {
        return courseMapper.selectcourse1(courseType, learningScope);
    }




    /**
     * 查询学员课程业务列表
     *
     * @param stuVideoCourse
     * @param page
     * @return
     */
    @Override
    public IPage<StuVideoCourseVO> stuVideoCourseList(StuVideoCourseVO stuVideoCourse, Page page) {
        return courseMapper.stuVideoCourseList(stuVideoCourse, page);
    }

    /**
     * 查询学员课程业务列表
     *
     * @param stuPdfCourse
     * @param page
     * @return
     */
    @Override
    public IPage<StuPdfCourseVO> stuPdfCourseList(StuPdfCourseVO stuPdfCourse, Page page) {
        IPage<StuPdfCourseVO> list = courseMapper.stuPdfCourseList(stuPdfCourse, page);
        for (StuPdfCourseVO vo : list.getRecords()) {
            String learningTime = "";
            if (vo != null) {
                learningTime = BaseUtils.getTime(vo.getPdfTime());
            }
            vo.setLearningTime(learningTime);
        }
        return list;
    }


}

