package com.esmooc.legion.classroom.service.impl;

import com.esmooc.legion.classroom.mapper.ClassInfoMapper;
import com.esmooc.legion.classroom.entity.ClassInfo;
import com.esmooc.legion.classroom.service.IClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级信息接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements IClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;
}
