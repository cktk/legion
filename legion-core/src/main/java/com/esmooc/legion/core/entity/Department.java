package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.entity.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daimao
 */
@ApiModel(value = "t_department")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_department")
public class Department extends BaseEntity {


    @ApiModelProperty(value = "父id")
    @TableField(value = "parent_id")
    private String parentId;

    @TableField(value = "sort_order")
    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;


    @TableField(value = "title")
    @ApiModelProperty(value = "部门名称")
    private String title;

    @TableField(value = "is_parent")
    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    private Boolean isParent;

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
