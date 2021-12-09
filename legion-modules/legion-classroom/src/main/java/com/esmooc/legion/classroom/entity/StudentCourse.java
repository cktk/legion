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
* @ClassName: StudentCourse
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月08日21点57分
*
**/

/**
 * 选课中间表
 */
@ApiModel(value = "选课中间表")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_student_course")
public class StudentCourse extends BaseEntity {
    /**
     * 课程id
     */
    @TableField(value = "c_id")
    @ApiModelProperty(value = "课程id")
    private String cId;

    /**
     * 学生id
     */
    @TableField(value = "s_id")
    @ApiModelProperty(value = "学生id")
    private String sId;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态")
    private Integer status;
}