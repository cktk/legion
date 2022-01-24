package com.esmooc.legion.edu.entity.vo;

import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExamVO
 * @Author Administrator
 * @Date 2021-1-7 9:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamVO extends BaseEntity {

    /**
     * 试卷名称
     */
    private String title;
    /**
     * 课程id
     */
    private String clazzId;
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
     * 判断题数
     */
    private String judgmentCount;
    /**
     * 总分
     */
    private String gradeCount;
    /**
     * 及格分数
     */
    private String gradePass;
    /**
     * 规则
     */
    private String rules;
    /**
     * 规则id
     */
    private String rulesId;
    /**
     * 类别id
     */
    private String majorId;
    /**
     * 类别名称
     */
    private String majorName;
    /**
     * 是否发布  1：发布 0：未发布
     */
    private String issur;

    private String backId;

}
