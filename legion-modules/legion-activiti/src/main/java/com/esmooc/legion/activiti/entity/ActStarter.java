package com.esmooc.legion.activiti.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
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
@Table(name = "t_act_starter")
@TableName("t_act_starter")
@ApiModel(value = "流程可发起人")
@Accessors(chain = true)
public class ActStarter extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "关联用户ID")
    private String userId;
}
