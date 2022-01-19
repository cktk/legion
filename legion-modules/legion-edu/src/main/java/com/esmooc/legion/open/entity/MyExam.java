package com.esmooc.legion.open.entity;

import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MyExam
 * @Author Administrator
 * @Date 2021-1-12 13:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExam extends BaseEntity {
    /**
     * 名称
     */
    private String clazzName;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 类别id
     */
    private String majorId;
    /**
     * 类别名称
     */
    private String majorName;
    /**
     * 学时
     */
    private String period;
    /**
     * 登录人id
     */
    private String userId;
    /**
     * 是否通过
     */
    private Integer isPass;
    /**
     * 分数
     */
    private String grade;
    /**
     * 类型
     */
    private String type;
    /**
     * 历史
     */
    private String historyQuestion;

}
