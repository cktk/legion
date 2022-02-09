package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 21:01
 * @Description:
 */

/**
 * 管理端考试主表
 */
@ApiModel(value = "管理端考试主表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_exam")
public class Exam {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private String id;

    @TableField(value = "create_by")
    @ApiModelProperty(value = "")
    private String createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    private Date createTime;

    /**
     * 是否发布  0：未发布  1：发布
     */
    @TableField(value = "issue")
    @ApiModelProperty(value = "是否发布  0：未发布  1：发布")
    private Integer issue;

    @TableField(value = "is_delete")
    @ApiModelProperty(value = "")
    private Integer isDelete;

    /**
     * 是否题库创建考试
     */
    @TableField(value = "is_bank")
    @ApiModelProperty(value = "是否题库创建考试")
    private Integer isBank;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "")
    private String updateBy;

    @TableField(value = "del_flag")
    @ApiModelProperty(value = "")
    private String delFlag;

    @TableField(value = "title")
    @ApiModelProperty(value = "")
    private String title;

    @TableField(value = "bank_id")
    @ApiModelProperty(value = "")
    private String bankId;
}
