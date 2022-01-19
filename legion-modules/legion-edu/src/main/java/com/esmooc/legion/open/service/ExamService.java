package com.esmooc.legion.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.vo.ExamVO;

import java.util.Map;

public interface ExamService {
    IPage<ExamVO> examList(ExamVO examVO, Page page);

    Map saveExam(ExamVO examVO);

    Map getExamById(String id);

    void issueExam(String id);

    void deleteExam(String id);

    void withdrawExam(String id);

    int isUpdateRules(String id);

    Map checkBankCreate(String id);
}
