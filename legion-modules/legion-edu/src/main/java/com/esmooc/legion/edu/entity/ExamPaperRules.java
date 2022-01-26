package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 20:47
 * @Description:
 */

/**
 * 试卷规则表
 */
@ApiModel(value = "试卷规则表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_exam_paper_rules")
public class ExamPaperRules {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 课程id
     */
    @TableField(value = "clazz_id")
    @ApiModelProperty(value = "课程id")
    private String clazzId;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    private Date createTime;

    @TableField(value = "create_by")
    @ApiModelProperty(value = "")
    private String createBy;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "")
    private String updateBy;

    @TableField(value = "del_flag")
    @ApiModelProperty(value = "")
    private String delFlag;

    /**
     * 规则
     */
    @TableField(value = "rules")
    @ApiModelProperty(value = "规则")
    private String rules;

    /**
     * 题库id
     */
    @TableField(value = "bank_id")
    @ApiModelProperty(value = "题库id")
    private String bankId;
}
