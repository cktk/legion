package com.esmooc.legion.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExamPaperRules
 * @Author Administrator
 * @Date 2020-12-29 14:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperRules {
    private String id;
    private String clazzId;
    private String rules;
    private String createTime;
    private String createBy;
    private String bankId;

}
