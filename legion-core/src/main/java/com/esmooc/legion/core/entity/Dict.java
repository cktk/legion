package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.security.acl.Group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月23日 11:27
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */

/**
    * 系统字典表
    */
@ApiModel(value="系统字典表")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_dict")
public class Dict extends LegionBaseEntity implements Serializable {
    /**
     * 字典类型
     */
    @TableField(value = "type")
    @ApiModelProperty(value="字典类型")
    @NotEmpty(message = "字典type不能为空")
    private String type;

    /**
     * 字典值唯一类型
     */
    @TableField(value = "code")
    @ApiModelProperty(value="字典值")
    private String code;

    /**
     * 是否是父节点
     */
    @TableField(value = "is_parent")
    @ApiModelProperty(value = "是否为父项")
    private boolean parent;


    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="描述")
    @NotEmpty(message = "描述不能为空")
    private String description;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 标签名
     */
    @TableField(value = "label")
    @ApiModelProperty(value="标签名")
    private String label;

    /**
     * 字典项
     */
    @TableField(value = "value")
    @ApiModelProperty(value="字典项 全局唯一 如果添加的时候为空的话则用type_code组成")
    private String value;

    /**
     * 是否是系统内置
     */
    @TableField(value = "system_flag")
    @ApiModelProperty(value="字典类型")
    @NotEmpty(message = "字典类型不能为空")
    private String systemFlag;

    /**
     * 是否禁用
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="是否禁用")
    private Boolean status;

    /**
     * 拼音
     */
    @TableField(value = "pinyin")
    @ApiModelProperty(value="拼音 ")
    private String pinyin;
    /**
     * 排序
     */
    @TableField(value = "sort_order")
    @ApiModelProperty(value="排序")
    private int sortOrder;

}
