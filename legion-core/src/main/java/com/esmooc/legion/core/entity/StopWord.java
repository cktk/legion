package com.esmooc.legion.core.entity;

import com.esmooc.legion.core.base.LegionBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "t_stop_word")
@TableName("t_stop_word")
@ApiModel(value = "禁用词管理")
public class StopWord extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String title;
}