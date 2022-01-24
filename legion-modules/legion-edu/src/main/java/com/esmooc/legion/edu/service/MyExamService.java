package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.MyExam;

import java.util.Map;

public interface MyExamService  extends IService<MyExam> {
    IPage<MyExam> list(MyExam data, Page page);

    Map homeMyExam();
}
