package com.esmooc.legion.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Daimao
 */
@Data
@TableName("t_quartz_job")
@ApiModel(value = "定时任务")
public class QuartzJob extends BaseEntity {

    @TableField(value = "job_class_name")
    @ApiModelProperty(value = "任务类名")
    private String jobClassName;

    @TableField(value = "cron_expression")
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @TableField(value = "parameter")
    @ApiModelProperty(value = "参数")
    private String parameter;

    @TableField(value = "description")
    @ApiModelProperty(value = "备注")
    private String description;


    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态 0正常 -1停止")
    private Integer status = CommonConstant.STATUS_NORMAL;
}
