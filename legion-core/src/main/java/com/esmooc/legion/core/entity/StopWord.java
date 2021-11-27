package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("t_stop_word")
@ApiModel(value = "禁用词管理")
public class StopWord extends BaseEntity {
    @ApiModelProperty(value = "名称")
    private String title;
}
