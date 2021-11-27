package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daimao
 */
@Data
@TableName("t_setting")
@ApiModel(value = "配置")
@NoArgsConstructor
public class Setting extends BaseEntity {
    @ApiModelProperty(value = "配置值value")
    private String value;

    public Setting(String id) {
        super.setId(id);
    }
}
