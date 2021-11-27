package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("t_department_header")
@ApiModel(value = "部门负责人")
public class DepartmentHeader extends BaseEntity {


    @TableField(value = "department_id")
    @ApiModelProperty(value = "关联部门id")
    private String departmentId;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "关联部门负责人")
    private String userId;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "负责人类型 默认0主要 1副职")
    private Integer type = CommonConstant.HEADER_TYPE_MAIN;

}
