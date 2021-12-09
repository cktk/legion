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
* @ClassName: TeacherInfo
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月08日21点57分
*
**/

/**
 * 老师信息
 */
@ApiModel(value = "老师信息")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_teacher_info")
public class TeacherInfo extends BaseEntity {
    /**
     * 老师姓名
     */
    @TableField(value = "t_name")
    @ApiModelProperty(value = "老师姓名")
    private String tName;

    /**
     * 性别
     */
    @TableField(value = "t_sex")
    @ApiModelProperty(value = "性别")
    private String tSex;

    /**
     * 工号
     */
    @TableField(value = "job_number")
    @ApiModelProperty(value = "工号")
    private String jobNumber;

    /**
     * 职称
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "职称,")
    private String title;
}