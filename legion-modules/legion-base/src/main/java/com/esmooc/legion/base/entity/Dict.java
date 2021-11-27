package com.esmooc.legion.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.esmooc.legion.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @author Daimao
 */
@Data
@TableName("t_dict")
@ApiModel(value = "字典")
public class Dict extends BaseEntity {



    @ApiModelProperty(value = "字典名称")
    private String title;

    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "备注")
    private String description;

    @TableField(value = "sort_order")
    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;
}
