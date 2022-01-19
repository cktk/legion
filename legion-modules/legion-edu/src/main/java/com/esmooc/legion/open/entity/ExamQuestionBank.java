package com.esmooc.legion.open.entity;

import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题库主表
 *
 * @ClassName ExamQuestionBank
 * @Author Administrator
 * @Date 2021-1-6 9:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestionBank extends BaseEntity {
    private String title;
    private String majorId;
    private String clazzId;
    private String majorName;

}
