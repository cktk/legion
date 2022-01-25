package com.esmooc.legion.edu.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.ExamPaperRules;
import com.esmooc.legion.edu.entity.vo.ExamPaperRulesVo;
import com.esmooc.legion.edu.mapper.PaperRulesMapper;
import com.esmooc.legion.edu.service.PaperRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PaperRulesServiceImpl
 * @Author Administrator
 * @Date 2020-12-29 14:21
 **/
@Service
public class PaperRulesServiceImpl extends ServiceImpl<PaperRulesMapper, ExamPaperRules> implements PaperRulesService {

    @Autowired
    private PaperRulesMapper paperRulesMapper;

    @Override
    public List<ExamPaperRulesVo> paperRulesList(ExamPaperRulesVo examPaperRulesVo, PageVo pageVo) {
        List<ExamPaperRulesVo> list = paperRulesMapper.paperRulesList(examPaperRulesVo);
        // 解析规则
        for (ExamPaperRulesVo data : list) {
            if (null != data.getRules() || !"".equals(data.getRules())) {
                // 解析规则
                Map ruleMap = parsingRules(data.getRules());
                data.setRadioCount(ruleMap.get("radio").toString());
                data.setCheckboxCount(ruleMap.get("checkbox").toString());
                data.setGradeCount(ruleMap.get("gradeCount").toString());
                data.setGradePass(ruleMap.get("gradePass").toString());
                data.setQuestionCount(String.valueOf(Integer.valueOf(data.getRadioCount()) + Integer.valueOf(data.getCheckboxCount())));
            }
        }
        return list;
    }

    @Override
    public Map getClazzQuestion(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return null;
        }
        Map m = new HashMap();
        // 单选题
        Integer radioCount = paperRulesMapper.getQuestionCountByClazzIdType(id, Constants.RADIO);
        // 多选题
        Integer checkboxCount = paperRulesMapper.getQuestionCountByClazzIdType(id, Constants.CHECKBOX);
        m.put("radioCount", radioCount);
        m.put("checkboxCount", checkboxCount);
        return m;
    }

    @Override
    public void saveRules(ExamPaperRules examPaperRules) {
        /**
         *  保存之前，先删除课程相关的规则，确保规则的唯一性
         */
        paperRulesMapper.deleteRulesByClazzId(examPaperRules.getClazzId());
        // 执行添加
        paperRulesMapper.saveRules(examPaperRules);
    }

    @Override
    public void deleteRules(String id) {
        String[] ids = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            paperRulesMapper.deleteRulesById(ids[i]);
        }
    }

    @Override
    public Map getRulesById(String id) {
        Map m = new HashMap<>();
        ExamPaperRulesVo data = new ExamPaperRulesVo();
        // 根据id 查询规则
        String rules = paperRulesMapper.getRulesById(id);
        if (null == rules || "".equals(rules)) {
            return m;
        }
        Map ruleMap = parsingRules(rules);
        data.setRadioCount(ruleMap.get("radio").toString());
        data.setCheckboxCount(ruleMap.get("checkbox").toString());
        data.setGradeCount(ruleMap.get("gradeCount").toString());
        data.setGradePass(ruleMap.get("gradePass").toString());
        data.setQuestionCount(String.valueOf(Integer.valueOf(data.getRadioCount()) + Integer.valueOf(data.getCheckboxCount())));
        m.put("data", rules);
        m.put("dataFormat", data);
        return m;
    }

    @Override
    public Map parsingRules(String rules) {
        Map m = new HashMap<>();
        JSONObject object = JSONUtil.parseObj(rules);
        if (null != object) {
            m.put("radio", object.get("radio") == null ? 0 : object.get("radio").toString());
            m.put("radioGrade", object.get("radioGrade") == null ? 0 : object.get("radioGrade").toString());
            m.put("checkbox", object.get("checkbox") == null ? 0 : object.get("checkbox").toString());
            m.put("checkboxGrade", object.get("checkboxGrade") == null ? 0 : object.get("checkboxGrade").toString());
            m.put("judgment", object.get("judgment") == null ? 0 : object.get("judgment").toString());
            m.put("judgmentGrade", object.get("judgmentGrade") == null ? 0 : object.get("judgmentGrade").toString());
            m.put("gradeCount", object.get("gradeCount") == null ? 0 : object.get("gradeCount").toString());
            m.put("gradePass", object.get("gradePass") == null ? 0 : object.get("gradePass").toString());
        }
        return m;
    }

}
