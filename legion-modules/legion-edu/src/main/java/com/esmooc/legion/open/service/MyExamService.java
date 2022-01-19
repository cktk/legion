package com.esmooc.legion.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.MyExam;

import java.util.Map;

public interface MyExamService {
    IPage<MyExam> list(MyExam data, Page page);

    Map homeMyExam();
}
