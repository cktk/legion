package com.esmooc.legion.open.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.ExamQuestion;
import com.esmooc.legion.open.entity.ExamQuestionBank;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    IPage<ExamQuestion> questionListByClazzId(ExamQuestion examQuestion, Page page);

    void deleteQuestionById(String id);

    void saveQuestion(ExamQuestion examQuestion);

    Map getQuestionById(String id);

    void putQuestion(ExamQuestion examQuestion);

    Map createQuestionBank(ExamQuestionBank examQuestionBank);

    IPage<ExamQuestionBank> selectQuestionBank(ExamQuestionBank examQuestionBank, Page page);

    void deleteQuestionBank(String ids);

    String importUser(List<ExamQuestion> questions, String bankId, String clazzId);

    Map bankDetails(String id);

    Map paperDetails(String id);
}
