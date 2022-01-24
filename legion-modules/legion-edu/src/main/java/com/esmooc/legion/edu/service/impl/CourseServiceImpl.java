package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.common.utils.DateUtils;
import com.esmooc.legion.edu.common.utils.FileUtils;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.entity.Prompt;
import com.esmooc.legion.edu.entity.vo.CourseVO;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper,Course >  implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseFileMapper courseFileMapper;

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private PromptMapper promptMapper;

    /**
     * 查询课程业务
     *
     * @param id 课程业务ID
     * @return 课程业务
     */
    @Override
    public Course selectBizCourseById(String id) {
        return courseMapper.selectBizCourseById(id);
    }

    /**
     * 查询课程业务列表
     *
     * @param course 课程业务
     * @return 课程业务
     */
    @Override
    public List<Course> selectBizCourseList(Course course) {
        course.setDelFlag(Integer.valueOf(Constants.ISNOTDELETE));
        return courseMapper.selectBizCourseList(course);
    }


    @Override
    public IPage<CourseVO> selectBizCourseVOList(CourseVO bizCourse, Page page) {
        bizCourse.setDelFlag(Constants.ISNOTDELETE);
        return courseMapper.selectBizCourseVOList(bizCourse, page);
    }

    @Override
    public IPage<CourseVO> selectBizAuditCourseVOList(CourseVO bizCourse, Page page) {
        bizCourse.setDelFlag(Constants.UNREVISED);
        return courseMapper.selectBizCourseVOList(bizCourse, page);
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

    @Override
    public List<HomePageVO> selectcourse1(int courseType, String learningScope) {
        return courseMapper.selectcourse1(courseType, learningScope);
    }

    @Override
    public String selectcourseVideoPeriod(String learningScope, long userId) {
        return courseMapper.selectcourseVideoPeriod(learningScope, userId);
    }

    @Override
    public String selectcoursePdfPeriod(String learningScope, Long userId) {
        StuPdfCourseVO vo = courseMapper.selectcoursePdfPeriod(learningScope, userId);
        String learningTime = "";
        if (vo != null) {
            learningTime = BaseUtils.getTime(vo.getPdfTime());
        }
        return learningTime;
    }

    /**
     * 新增课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    @Override
    public int insertCourse(Course course) {
        course.setCreateTime(DateUtils.getNowDate());
        course.setUpdateBy(course.getCreateBy());
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程业务
     *
     * @param course 课程业务
     * @return 结果
     */
    @Override
    public int updateBizCourse(Course course) {
        course.setUpdateTime(DateUtils.getNowDate());
        course.setDelFlag(Constants.ISNOTDELETE);
        // 修改题库类型
        paperMapper.updateBankMajor(course.getId(), course.getSubject());
        return courseMapper.updateBizCourse(course);
    }

    @Override
    @Transactional
    public int updateBizAuditCourse(String id, int courseType) {
        Course course = new Course();
        course.setId(id);
        course.setCourseType(courseType);
        course.setUpdateTime(DateUtils.getNowDate());
        course.setDelFlag(Constants.ISNOTDELETE);
        Prompt prompt = new Prompt();
        prompt.setCourseId(id);
        prompt.setPrompt("通过");
        promptMapper.insertBizPrompt(prompt);
        return courseMapper.updateBizCourse(course);
    }

    /**
     * 批量删除课程业务
     *
     * @param ids 需要删除的课程业务ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBizCourseByIds(String[] ids) {
        Course course = null;
        for (String id :
                ids) {
            deleteBizCourseById(id);
            // 删除题库
            paperMapper.deleteExamBankByClazzId(id);
            // 删除试题
            paperMapper.deleteQuestionByClazzId(id);
        }
        return ids.length;
        //return bizCourseMapper.deleteBizCourseByIds(ids);
    }

    /**
     * 删除课程业务信息
     *
     * @param id 课程业务ID
     * @return 结果
     */
    @Override
    public int deleteBizCourseById(String id) {
        List<CourseFile> files = courseFileMapper.selectBizCourseFile(id);
        for (CourseFile file :
                files) {
            FileUtils.deleteFile(file.getFileName());
            courseFileMapper.deleteBizCourseFileById(file.getId());
        }
        Course course = new Course();
        course.setId(id);
        course.setDelFlag(Constants.ISDELETE);
        return courseMapper.deleteBizCourse(course);
        //return bizCourseMapper.deleteBizCourseById(id);
    }

    @Override
    @Transactional
    public int deleteBizAuditCourseById(String id, String prompt) {
        List<CourseFile> files = courseFileMapper.selectBizCourseFile(id);
        for (CourseFile file :
                files) {
            FileUtils.deleteFile(file.getFileName());
            courseFileMapper.deleteBizCourseFileById(file.getId());
        }
        Course course = new Course();
        course.setId(id);
        course.setDelFlag(Constants.ISDELETE);
        Prompt bizPrompt = new Prompt();
        bizPrompt.setCourseId(id);
        bizPrompt.setPrompt(prompt);
        promptMapper.insertBizPrompt(bizPrompt);
        return courseMapper.deleteBizCourse(course);
        //return bizCourseMapper.deleteBizCourseById(id);
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
        return courseMapper.stuVideoCourseList(stuVideoCourse,page);
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
        IPage<StuPdfCourseVO> list = courseMapper.stuPdfCourseList(stuPdfCourse,page);
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
