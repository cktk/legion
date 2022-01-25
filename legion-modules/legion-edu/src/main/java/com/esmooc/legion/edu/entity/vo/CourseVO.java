package com.esmooc.legion.edu.entity.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程业务对象 edu_course
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVO extends BaseEntity {


    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String courseTitle;

    /**
     * 科目
     */
    @ExcelProperty("科目")
    private String subject;

    /**
     * 专业类别
     */
    @ExcelProperty("专业类别")
    private String speciality;

    /**
     * 学时
     */
    @ExcelProperty("学时")
    private String period;

    /**
     * 学习范围
     */
    @ExcelProperty("学习范围")
    private String learningScope;

    /**
     * 课程类别
     */
    @ExcelProperty("课程类别")
    private String courseType;

    /**
     * 课程类别
     */
    @ExcelProperty("题库")
    private String questionBank;

    /**
     * 课程类别
     */
    @ExcelProperty("试题")
    private String questions;


    /**
     * 是否考试（1 是 0 否）
     */
    private String exam;

}