package com.esmooc.legion.edu.service;

import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.edu.entity.ExamPaperRules;
import com.esmooc.legion.edu.entity.vo.ExamPaperRulesVo;

import java.util.List;
import java.util.Map;

public interface PaperRulesService {
    List<ExamPaperRulesVo> paperRulesList(ExamPaperRulesVo examPaperRulesVo, PageVo pageVo);

    Map getClazzQuestion(String id);

    void saveRules(ExamPaperRules examPaperRules);

    void deleteRules(String id);

    Map getRulesById(String id);

    Map parsingRules(String rules);
}
