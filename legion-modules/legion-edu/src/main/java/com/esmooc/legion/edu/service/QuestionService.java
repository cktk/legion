package com.esmooc.legion.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.edu.entity.ExamQuestion;
import com.esmooc.legion.edu.entity.ExamQuestionBank;

import java.util.List;
import java.util.Map;

public interface QuestionService   extends IService<ExamQuestion>  {
    IPage<ExamQuestion> questionListByClazzId(ExamQuestion examQuestion, Page page);

    void deleteQuestionById(String id);

    void saveQuestion(ExamQuestion examQuestion);

    ExamQuestion getQuestionById(String id);

    void putQuestion(ExamQuestion examQuestion);

    ExamQuestionBank createQuestionBank(ExamQuestionBank examQuestionBank);

    IPage<ExamQuestionBank> selectQuestionBank(ExamQuestionBank examQuestionBank, Page page);

    void deleteQuestionBank(String ids);

    String importUser(List<ExamQuestion> questions, String bankId, String clazzId);

    Map bankDetails(String id);

    Map paperDetails(String id);
}
