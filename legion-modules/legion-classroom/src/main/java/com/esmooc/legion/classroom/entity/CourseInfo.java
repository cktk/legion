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
* @ClassName: CourseInfo
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月08日21点57分
*
**/

/**
 * 课程信息
 */
@ApiModel(value = "课程信息")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_course_info")
public class CourseInfo extends BaseEntity {
    /**
     * 课程名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "课程名称")
    private String name;

    /**
     * 学期
     */
    @TableField(value = "term")
    @ApiModelProperty(value = "学期")
    private String term;

    /**
     * 任课老师
     */
    @TableField(value = "teacher_id")
    @ApiModelProperty(value = "任课老师")
    private String teacherId;

    /**
     * 老师名称
     */
    @TableField(value = "teacher_name")
    @ApiModelProperty(value = "老师名称")
    private String teacherName;

    /**
     * 班级id
     */
    @TableField(value = "class_id")
    @ApiModelProperty(value = "班级id")
    private String classId;

    /**
     * 班级名称
     */
    @TableField(value = "class_name")
    @ApiModelProperty(value = "班级名称")
    private String className;

    /**
     * 课程编号
     */
    @TableField(value = "`no`")
    @ApiModelProperty(value = "课程编号,")
    private String no;

    /**
     * 学时
     */
    @TableField(value = "`time`")
    @ApiModelProperty(value = "学时")
    private String time;

    /**
     * 学分
     */
    @TableField(value = "mark")
    @ApiModelProperty(value = "学分")
    private String mark;
}