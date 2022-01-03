package com.esmooc.legion.pacs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/** 
* @ClassName: Series
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 序列
    */
@ApiModel(value="序列")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_series")
public class Series extends BaseEntity {
    /**
     * 地址
     */
    @TableField(value = "`host`")
    @ApiModelProperty(value="地址")
    private String host;

    /**
     * 名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 端口
     */
    @TableField(value = "port")
    @ApiModelProperty(value="端口")
    private Integer port;

    /**
     * 自增code
     */
    @TableField(value = "auto_code")
    @ApiModelProperty(value="自增code")
    private Integer autoCode;

    /**
     * 本地放射地址
     */
    @TableField(value = "local_AET")
    @ApiModelProperty(value="本地放射地址")
    private String localAet;

    /**
     * 远程放射地址
     */
    @TableField(value = "remote_AET")
    @ApiModelProperty(value="远程放射地址")
    private String remoteAet;

    /**
     * 状态码
     */
    @TableField(value = "status_code")
    @ApiModelProperty(value="状态码")
    private Integer statusCode;

    /**
     * 数据权限标识
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="数据权限标识")
    private String deptId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    @ApiModelProperty(value="租户id")
    private String tenantId;
}