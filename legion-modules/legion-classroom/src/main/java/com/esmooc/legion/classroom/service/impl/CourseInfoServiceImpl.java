package com.esmooc.legion.classroom.service.impl;

import com.esmooc.legion.classroom.mapper.CourseInfoMapper;
import com.esmooc.legion.classroom.entity.CourseInfo;
import com.esmooc.legion.classroom.service.ICourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程信息接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;
}
