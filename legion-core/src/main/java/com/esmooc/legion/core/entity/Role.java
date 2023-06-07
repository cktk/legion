package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.config.datascope.DataScopeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author DaiMao
 */
@Data
@TableName("t_role")
@ApiModel(value = "角色")
public class Role extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @TableField(value = "default_role")
    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole;

    @TableField(value = "data_type")
    @ApiModelProperty(value = "数据权限类型 0全部默认 1自定义 2本部门及以下 3本部门 4仅本人")
    private Integer dataType = DataScopeTypeEnum.ALL.getType();

    @TableField(value = "description")
    @ApiModelProperty(value = "备注")
    private String description;

    @TableField(exist = false)
    @ApiModelProperty(value = "拥有权限")
    private List<RolePermission> permissions;

    @TableField(exist = false)
    @ApiModelProperty(value = "拥有数据权限")
    private List<RoleDepartment> departments;
}
