package com.esmooc.legion.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DaiMao
 */
@Data
public abstract class LegionBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @CreatedBy
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "更新者")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "创建者ID")
    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private Long createId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "更新者ID")
    @TableField(value = "update_id", fill = FieldFill.UPDATE)
    private Long updateId;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    @TableLogic()
    @ApiModelProperty(value = "删除标志 默认0")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag ;
}
