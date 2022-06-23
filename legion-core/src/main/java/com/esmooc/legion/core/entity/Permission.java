package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author DaiMao
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "菜单权限")
@TableName(value = "t_permission")
public class Permission extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "菜单/权限名称")
    private String name;

    @TableField(value = "show_always")
    @ApiModelProperty(value = "始终显示 默认是")
    private Boolean showAlways = true;

    @TableField(value = "`level`")
    @ApiModelProperty(value = "层级")
    private Integer level;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "类型 -1顶部菜单 0页面 1具体操作")
    private Integer type;

    @TableField(value = "title")
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @TableField(value = "`path`")
    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;

    @TableField(value = "component")
    @ApiModelProperty(value = "前端组件")
    private String component;

    @TableField(value = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @TableField(value = "button_type")
    @ApiModelProperty(value = "按钮权限类型")
    private String buttonType;

    @TableField(value = "is_menu")
    @ApiModelProperty(value = "是否为站内菜单 默认true")
    private Boolean isMenu = true;

    @TableField(value = "url")
    @ApiModelProperty(value = "网页链接")
    private String url;


    @TableField(value = "localize")
    @ApiModelProperty(value = "是否启用多语言 默认false")
    private Boolean localize = false;

    @TableField(value = "i18n")
    @ApiModelProperty(value = "i18n渲染key")
    private String i18n;

    @TableField(value = "description")
    @ApiModelProperty(value = "说明备注")
    private String description;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父id")
    private String parentId;

    @TableField(value = "is_parent")
    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    private Boolean isParent = false;

    @TableField(value = "sort_order")
    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private Integer status = CommonConstant.STATUS_NORMAL;

    @TableField(exist = false)
    @ApiModelProperty(value = "子菜单/权限")
    private List<Permission> children;

    @TableField(exist = false)
    @ApiModelProperty(value = "页面拥有的权限类型")
    private List<String> permTypes;

    @TableField(exist = false)
    @ApiModelProperty(value = "父节点名称")
    private String parentTitle;

    @TableField(exist = false)
    @ApiModelProperty(value = "节点展开 前端所需")
    private Boolean expand = true;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否勾选 前端所需")
    private Boolean checked = false;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否选中 前端所需")
    private Boolean selected = false;
}
