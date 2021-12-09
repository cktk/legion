package com.esmooc.legion.classroom.entity;

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
* @ClassName: StudentInfo
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月08日21点57分
*
**/

/**
 * 学生信息
 */
@ApiModel(value = "学生信息")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_student_info")
public class StudentInfo extends BaseEntity {
    /**
     * 学生名字
     */
    @TableField(value = "s_name")
    @ApiModelProperty(value = "学生名字,")
    private String sName;

    /**
     * 学号
     */
    @TableField(value = "s_no")
    @ApiModelProperty(value = "学号")
    private String sNo;

    /**
     * 性别
     */
    @TableField(value = "s_sex")
    @ApiModelProperty(value = "性别")
    private String sSex;

    /**
     * 当前学期id
     */
    @TableField(value = "term_id")
    @ApiModelProperty(value = "当前学期id")
    private String termId;

    /**
     * 当前学期
     */
    @TableField(value = "term_name")
    @ApiModelProperty(value = "当前学期,")
    private String termName;

    /**
     * 当前班级id
     */
    @TableField(value = "class_id")
    @ApiModelProperty(value = "当前班级id")
    private String classId;

    /**
     * 班级名称
     */
    @TableField(value = "class_name")
    @ApiModelProperty(value = "班级名称")
    private String className;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱,")
    private String email;

    /**
     * 院系id
     */
    @TableField(value = "department_id")
    @ApiModelProperty(value = "院系id")
    private String departmentId;

    /**
     * 院系名称
     */
    @TableField(value = "department_name")
    @ApiModelProperty(value = "院系名称")
    private String departmentName;
}