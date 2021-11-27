package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("t_role_department")
@ApiModel(value = "角色部门")
public class RoleDepartment extends BaseEntity {


    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @TableField(value = "department_id")
    @ApiModelProperty(value = "部门id")
    private String departmentId;
}
