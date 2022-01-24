package com.esmooc.legion.edu.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 课程业务对象 edu_course
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String courseTitle;

    private static final long serialVersionUID = 1L;

    /**
     * 课程主键
     */
    private String id;

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
    private Float period;

    /** 学习范围 */
    @ExcelProperty("学习范围")
    private String learningScope;

    /**
     * 课程类别
     */
    @ExcelProperty("课程类别")
    private int courseType;

    /**
     * 课程类别
     */
    @ExcelProperty("题库")
    private Integer questionBank;

    /**
     * 课程类别
     */
    @ExcelProperty("试题")
    private Integer questions;

    /**
     * 删除标志（1代表存在 0代表删除）
     */
    private Integer delFlag;

    /** 是否考试（1 是 0 否） */
    private String exam;
    private String createBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updateBy;
}
