package com.esmooc.legion.classroom.service.impl;

import com.esmooc.legion.classroom.mapper.TeacherInfoMapper;
import com.esmooc.legion.classroom.entity.TeacherInfo;
import com.esmooc.legion.classroom.service.ITeacherInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 老师信息接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements ITeacherInfoService {

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;
}
