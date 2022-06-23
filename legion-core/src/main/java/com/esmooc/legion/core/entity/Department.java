package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author DaiMao
 */
@Data
@TableName("t_department")
@ApiModel(value = "部门")
public class Department extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "title")
    @ApiModelProperty(value = "部门名称")
    private String title;

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
    @ApiModelProperty(value = "父节点名称")
    private String parentTitle;

    @TableField(exist = false)
    @ApiModelProperty(value = "主负责人")
    private List<String> mainHeader;

    @TableField(exist = false)
    @ApiModelProperty(value = "副负责人")
    private List<String> viceHeader;

    @TableField(exist = false)
    @ApiModelProperty(value = "主负责人")
    private List<UserVo> mainHeaders;

    @TableField(exist = false)
    @ApiModelProperty(value = "副负责人")
    private List<UserVo> viceHeaders;
}
