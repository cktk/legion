package com.esmooc.legion.classroom.service.impl;

import com.esmooc.legion.classroom.mapper.StudentInfoMapper;
import com.esmooc.legion.classroom.entity.StudentInfo;
import com.esmooc.legion.classroom.service.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;
}
