package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 14:40
 * @Description:
 */

/**
 * 课程业务表
 */
@ApiModel(value = "课程业务表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_course")
public class Course {
    /**
     * 课程名称
     */
    @TableField(value = "course_title")
    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    /**
     * 科目
     */
    @TableField(value = "subject")
    @ApiModelProperty(value = "科目")
    private String subject;

    /**
     * 专业类别
     */
    @TableField(value = "speciality")
    @ApiModelProperty(value = "专业类别")
    private String speciality;

    /**
     * 学时
     */
    @TableField(value = "period")
    @ApiModelProperty(value = "学时")
    private Double period;

    /**
     * 学习范围
     */
    @TableField(value = "learning_scope")
    @ApiModelProperty(value = "学习范围")
    private String learningScope;

    /**
     * 课程类别
     */
    @TableField(value = "course_type")
    @ApiModelProperty(value = "课程类别")
    private Integer courseType;

    /**
     * 是否发布  0：未发布  1：发布
     */
    @TableField(value = "exam_issue")
    @ApiModelProperty(value = "是否发布  0：未发布  1：发布")
    private Integer examIssue;

    /**
     * 是否考试  0：否  1：是
     */
    @TableField(value = "exam")
    @ApiModelProperty(value = "是否考试  0：否  1：是")
    private Integer exam;

    /**
     * 审核状态
     */
    @TableField(value = "`audit`")
    @ApiModelProperty(value = "审核状态")
    private Integer audit;

    @TableField(exist = false)
    @ApiModelProperty(value = "学习状态")
    private Integer questionBank;
    /**
     * 审核状态
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "试题状态")
    private Integer questions;



    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private String id ;

    @CreatedBy
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    @LastModifiedBy
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private  Date updateTime;

    @ApiModelProperty(value = "删除标志 默认1")
    @TableField(value = "del_flag")
    @TableLogic()
    private  Integer delFlag ;
}
