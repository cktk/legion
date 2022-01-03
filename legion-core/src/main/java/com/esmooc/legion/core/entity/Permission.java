package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daimao
 */
@Data
@TableName("t_permission")
@ApiModel(value = "菜单权限")
public class Permission extends BaseEntity {

    @TableField(value = "description")
    @ApiModelProperty(value = "说明备注")
    private String description;


    @ApiModelProperty(value = "菜单/权限名称")
    @TableField(value = "`name`")
    private String name;


    @TableField(value = "`type`")
    @ApiModelProperty(value = "类型 -1顶部菜单 0页面 1具体操作")
    private Integer type;


    @TableField(value = "component")
    @ApiModelProperty(value = "前端组件")
    private String component;

    @TableField(value = "`path`")
    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;

    @TableField(value = "title")
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @TableField(value = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "层级")
    @TableField(value = "`level`")
    private Integer level;


    @TableField(value = "button_type")
    @ApiModelProperty(value = "按钮权限类型")
    private String buttonType;


    @TableField(value = "url")
    @ApiModelProperty(value = "网页链接")
    private String url;


    @ApiModelProperty(value = "始终显示 默认是")
    @TableField(value = "show_always")
    private Boolean showAlways = true;


    @TableField(value = "is_menu")
    @ApiModelProperty(value = "是否为站内菜单 默认true")
    private Boolean isMenu = true;


    @ApiModelProperty(value = "父id")
    @TableField(value = "parent_id")
    private String parentId;

    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    @TableField(value = "is_parent")
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
