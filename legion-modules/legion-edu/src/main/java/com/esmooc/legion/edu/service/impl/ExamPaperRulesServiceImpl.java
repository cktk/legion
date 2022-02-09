package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.edu.service.ExamPaperRulesService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.edu.mapper.ExamPaperRulesMapper;
import com.esmooc.legion.edu.entity.ExamPaperRules;
/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 20:47
 * @Description:
 */
@Service
public class ExamPaperRulesServiceImpl extends ServiceImpl<ExamPaperRulesMapper, ExamPaperRules> implements ExamPaperRulesService {

    @Override
    public ExamPaperRules getByClazzId(String id) {

        QueryWrapper<ExamPaperRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ExamPaperRules::getClazzId, id);
        ExamPaperRules examPaperRules = new ExamPaperRules();
        try {
            examPaperRules = this.list(queryWrapper).get(0);
        } catch (Exception e) {

        }

        return examPaperRules ;
    }
}
