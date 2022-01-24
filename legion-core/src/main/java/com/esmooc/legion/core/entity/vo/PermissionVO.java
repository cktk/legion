package com.esmooc.legion.core.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.entity.Permission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/24/ 09:50
 * @Description:
 */
@Data
public class PermissionVO extends BaseEntity {

    @ApiModelProperty(value = "说明备注")
    private String description;


    @ApiModelProperty(value = "菜单/权限名称")
    private String name;


    @ApiModelProperty(value = "类型 -1顶部菜单 0页面 1具体操作")
    private Integer type;


    @ApiModelProperty(value = "前端组件")
    private String component;

    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "层级")
    private Integer level;


    @ApiModelProperty(value = "按钮权限类型")
    private String buttonType;


    @ApiModelProperty(value = "网页链接")
    private String url;


    @ApiModelProperty(value = "始终显示 默认是")
    private Boolean showAlways = true;


    @ApiModelProperty(value = "是否为站内菜单 默认true")
    private Boolean isMenu = true;


    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    private Boolean isParent = false;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private Integer status = CommonConstant.STATUS_NORMAL;



}
