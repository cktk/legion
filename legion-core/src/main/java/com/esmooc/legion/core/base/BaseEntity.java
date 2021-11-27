package com.esmooc.legion.core.base;

import com.baomidou.mybatisplus.annotation.*;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level =  AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public abstract class BaseEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
    String id = SnowFlakeUtil.nextId().toString();

    @CreatedBy
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Date createTime;

    @ApiModelProperty(value = "更新者")
    @LastModifiedBy
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    String updateBy;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    Date updateTime;

    @ApiModelProperty(value = "删除标志 默认1")
    @TableField(value = "del_flag")
    @TableLogic()
    Integer delFlag =0;
}
