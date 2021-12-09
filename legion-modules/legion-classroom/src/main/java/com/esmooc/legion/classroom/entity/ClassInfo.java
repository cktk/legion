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
* @ClassName: ClassInfo
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月08日21点57分
*
**/

/**
 * 班级信息
 */
@ApiModel(value = "班级信息")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "s_class_info")
public class ClassInfo extends BaseEntity {
    /**
     * 班级名称
     */
    @TableField(value = "class_name")
    @ApiModelProperty(value = "班级名称,")
    private String className;

    /**
     * 辅导员id
     */
    @TableField(value = "class_teacher_id")
    @ApiModelProperty(value = "辅导员id,")
    private String classTeacherId;

    /**
     * 辅导员名字
     */
    @TableField(value = "class_teacher_name")
    @ApiModelProperty(value = "辅导员名字")
    private String classTeacherName;

    /**
     * 专业id
     */
    @TableField(value = "major_id")
    @ApiModelProperty(value = "专业id")
    private String majorId;

    /**
     * 专业名称
     */
    @TableField(value = "major_name")
    @ApiModelProperty(value = "专业名称,")
    private String majorName;

    /**
     * 院系id
     */
    @TableField(value = "grade_id")
    @ApiModelProperty(value = "院系id,")
    private String gradeId;

    /**
     * 院系名称
     */
    @TableField(value = "grade_name")
    @ApiModelProperty(value = "院系名称")
    private String gradeName;

    /**
     * 人数
     */
    @TableField(value = "p_num")
    @ApiModelProperty(value = "人数")
    private Integer pNum;
}
