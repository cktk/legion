package com.esmooc.legion.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


import org.springframework.data.annotation.Transient;import java.math.BigDecimal;

/**
 * @author Exrick
 */
@Data
@Accessors(chain = true)
@TableName("t_file_category")
@ApiModel(value = "文件分类")
public class FileCategory extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    private Boolean isParent = false;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;


    @TableField(exist = false)
    @ApiModelProperty(value = "父节点名称")
    private String parentTitle;
}
