package com.esmooc.legion.edu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExamPaperRulesVo
 * @Author Administrator
 * @Date 2020-12-29 14:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperRulesVo {
    private String id;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 课程名称 = 试卷名称
     */
    private String clazzName;
    /**
     * 总题数
     */
    private String questionCount;
    /**
     * 单选题数
     */
    private String radioCount;
    /**
     * 多选题数
     */
    private String checkboxCount;
    /**
     * 总分
     */
    private String gradeCount;
    /**
     * 及格分数
     */
    private String gradePass;
    /**
     * 创建人id
     */
    private String createBy;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 规则
     */
    private String rules;

}
