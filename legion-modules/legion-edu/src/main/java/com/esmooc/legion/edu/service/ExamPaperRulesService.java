package com.esmooc.legion.edu.service;

import com.esmooc.legion.edu.entity.ExamPaperRules;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 20:47
 * @Description:
 */
public interface ExamPaperRulesService extends IService<ExamPaperRules>{


        ExamPaperRules getByClazzId(String id);
    }
