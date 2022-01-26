package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.Exam;
import com.esmooc.legion.edu.entity.vo.ExamVO;

import java.util.ArrayList;
import java.util.Map;

public interface ExamService extends IService<Exam> {
    IPage<ExamVO> examList(ExamVO examVO, Page page);

    Map saveExam(ExamVO examVO);

    Map getExamById(String id);

    boolean issueExam(String id, ArrayList<String> userIds, ArrayList<String> deptIds, ArrayList<String> roleIds);

    void deleteExam(String id);

    void withdrawExam(String id);

    int isUpdateRules(String id);

    Map checkBankCreate(String id);
}

