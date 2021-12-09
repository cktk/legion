package com.esmooc.legion.classroom.service.impl;

import com.esmooc.legion.classroom.mapper.StudentCourseMapper;
import com.esmooc.legion.classroom.entity.StudentCourse;
import com.esmooc.legion.classroom.service.IStudentCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 选课中间表接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements IStudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;
}
