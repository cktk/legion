package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.edu.entity.ExamPaperRules;
import com.esmooc.legion.edu.entity.vo.ExamPaperRulesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperRulesMapper extends BaseMapper<ExamPaperRules> {
    List<ExamPaperRulesVo> paperRulesList(ExamPaperRulesVo examPaperRulesVo);

    Integer getQuestionCountByClazzIdType(@Param("id") String id, @Param("type") Integer type);

    void deleteRulesById(String id);

    void deleteRulesByClazzId(String clazzId);



    String getRulesById(String id);

    String getRulesByClazzId(String clazzId);

    String getClazzIdByPaperId(String paperId);

    String getClazzMajorById(String clazzId);

    String getClazzNameById(String clazzId);

    String getBankIdByClazzId(String id);

    String getExamClazzId(String id);
}
