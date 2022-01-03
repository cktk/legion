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
* @ClassName: DicomScp
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 配置
    */
@ApiModel(value="配置")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_dicom_scp")
public class DicomScp extends BaseEntity {
    /**
     * 地址
     */
    @TableField(value = "`host`")
    @ApiModelProperty(value="地址")
    private String host;

    /**
     * 端口
     */
    @TableField(value = "port")
    @ApiModelProperty(value="端口")
    private Integer port;

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