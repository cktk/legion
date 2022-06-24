package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:43
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */

@ApiModel(value="t_test")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_test")
public class Test extends LegionBaseEntity {
    @TableField(value = "`name`")
    @ApiModelProperty(value="")
    private String name;

    @TableField(value = "tenant_id")
    @ApiModelProperty(value="租户id")
    private Long tenantId;

    @TableField(value = "age")
    @ApiModelProperty(value="")
    private Integer age;


    @TableField(value = "department_id", fill = FieldFill.INSERT)
//    @TableField(value = "department_id")
    @ApiModelProperty(value="")
    private String departmentId;
}
