package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/24/ 10:21
 * @Description:
 */
/**
    * 题目表
    */
@ApiModel(value="题目表")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_exam_question")
public class ExamQuestion extends BaseEntity {
    @TableField(value = "clazz_id")
    @ApiModelProperty(value="")
    private String clazzId;

    /**
     * 题目类型： 0单选   1多选 2判断
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="题目类型： 0单选   1多选 2判断")
    private String type;

    /**
     * 是否删除  1：删除 0：未删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value="是否删除  1：删除 0：未删除")
    private Integer isDelete;

    /**
     * 创建用户id
     */
    @TableField(value = "create_user")
    @ApiModelProperty(value="创建用户id")
    private String createUser;

    /**
     * 专业id
     */
    @TableField(value = "major_id")
    @ApiModelProperty(value="专业id")
    private String majorId;

    /**
     * 题库主表id
     */
    @TableField(value = "bank_id")
    @ApiModelProperty(value="题库主表id")
    private String bankId;

    /**
     * 问题
     */
    @TableField(value = "title")
    @ApiModelProperty(value="问题")
    private String title;

    /**
     * 选项
     */
    @TableField(value = "`options`")
    @ApiModelProperty(value="选项")
    private String options;

    /**
     * 答案
     */
    @TableField(value = "answers")
    @ApiModelProperty(value="答案")
    private String answers;

    /**
     * 解析
     */
    @TableField(value = "analysis")
    @ApiModelProperty(value="解析")
    private String analysis;

    /**
     *  答案是否正确
     */
    @TableField(exist = false)
    private String isItRightOrNot;
    @TableField(exist = false)
    private String  yourAnswers;
}
