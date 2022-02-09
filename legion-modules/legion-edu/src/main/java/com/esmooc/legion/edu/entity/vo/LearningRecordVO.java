package com.esmooc.legion.edu.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 课程学习状态业务对象 edu_learning_record
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningRecordVO {

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
     * 课程ID
     */
    private String courseId;

    /**
     * 学员编号
     */
    private String userId;

    /**
     * 学习状态（0未学习 1学习中 2已学习）
     */
    @ExcelProperty("学习状态")
    private Integer studyType;

    /**
     * 学员名字
     */
    @ExcelProperty("学员名字")
    private String userName;

    /**
     * $column.columnComment
     */
    @ExcelProperty("学员名字")
    private String year;

    /**
     * pdf学习时间
     */
    private long pdfTime;

    /**
     * 学习时间
     */
    private String learningTime;

    /**
     * 成绩
     */
    private String grade;

    /**
     * 是否及格
     */
    private String isPass;

    private String examId;

    private int examIssue;

    /**
     * 是否考试（1 是 0 否）
     */
    private String exam;

    private int noPassCount;


    @TableId(value = "id",type = IdType.INPUT)
    @ApiModelProperty(value = "唯一标识")
    String id;

    @CreatedBy
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Date createTime;

    @ApiModelProperty(value = "更新者")
    @LastModifiedBy
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    String updateBy;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    Date updateTime;

    @ApiModelProperty(value = "删除标志 默认1")
    @TableField(value = "del_flag")
    @TableLogic()
    Integer delFlag ;

}
