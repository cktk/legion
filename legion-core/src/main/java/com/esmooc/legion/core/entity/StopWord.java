package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
@TableName("t_stop_word")
@ApiModel(value = "禁用词管理")
public class StopWord extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "title")
    @ApiModelProperty(value = "名称")
    private String title;
}
