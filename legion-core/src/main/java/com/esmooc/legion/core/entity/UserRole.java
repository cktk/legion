package com.esmooc.legion.core.entity;

import com.esmooc.legion.core.base.LegionBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_user_role")
@TableName("t_user_role")
@ApiModel(value = "用户角色")
public class UserRole extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "角色唯一id")
    @TableField(value = "role_id")
    private String roleId;

    @Transient
    @TableField(exist = false,value = "role_name")
    @ApiModelProperty(value = "角色名")
    private String roleName;
}
