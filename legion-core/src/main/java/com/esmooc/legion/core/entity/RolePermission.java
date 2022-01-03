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
@TableName("t_role_permission")
@ApiModel(value = "角色权限")
public class RolePermission extends BaseEntity {


    @TableField(value = "role_id")
    @ApiModelProperty(value = "角色id")
    private String roleId;

    @TableField(value = "permission_id")
    @ApiModelProperty(value = "权限id")
    private String permissionId;

}
