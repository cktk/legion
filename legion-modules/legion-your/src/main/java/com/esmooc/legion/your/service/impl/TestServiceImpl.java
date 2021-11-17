package com.esmooc.legion.your.service.impl;

import com.esmooc.legion.your.mapper.TestMapper;
import com.esmooc.legion.your.entity.Test;
import com.esmooc.legion.your.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    @Autowired
    private TestMapper testMapper;
}
