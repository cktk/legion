package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.Exam;
import com.esmooc.legion.edu.entity.vo.ExamVO;

import java.util.Map;

public interface ExamService  extends IService<Exam> {
    IPage<ExamVO> examList(ExamVO examVO, Page page);

    Map saveExam(ExamVO examVO);

    Map getExamById(String id);

    void issueExam(String id);

    void deleteExam(String id);

    void withdrawExam(String id);

    int isUpdateRules(String id);

    Map checkBankCreate(String id);
}
