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
 * @Date: 2022/01/26/ 20:57
 * @Description: 
 */
/**
    * 试卷-类别关系表
    */
@ApiModel(value="试卷-类别关系表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_exam_major")
public class ExamMajor {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private String id;

    @TableField(value = "exam_id")
    @ApiModelProperty(value="")
    private String examId;

    @TableField(value = "major_id")
    @ApiModelProperty(value="")
    private String majorId;

    @TableField(value = "update_time")
    @ApiModelProperty(value="")
    private Date updateTime;

    @TableField(value = "create_time")
    @ApiModelProperty(value="")
    private Date createTime;

    @TableField(value = "create_by")
    @ApiModelProperty(value="")
    private String createBy;

    @TableField(value = "update_by")
    @ApiModelProperty(value="")
    private String updateBy;

    @TableField(value = "del_flag")
    @ApiModelProperty(value="")
    private String delFlag;
}