package com.esmooc.legion.activiti.entity;

import com.esmooc.legion.core.base.LegionBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author DaiMao
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_act_model")
@TableName("t_act_model")
@ApiModel(value = "模型")
public class ActModel extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模型名称")
    private String name;

    @ApiModelProperty(value = "标识")
    private String modelKey;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "描述/备注")
    private String description;
}
