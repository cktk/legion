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
* @ClassName: Device
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 设备
    */
@ApiModel(value="设备")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_device")
public class Device extends BaseEntity {
    /**
     * 设备类型
     */
    @TableField(value = "modality")
    @ApiModelProperty(value="设备类型")
    private String modality;

    /**
     * 站点名称
     */
    @TableField(value = "station_name")
    @ApiModelProperty(value="站点名称")
    private String stationName;

    /**
     * 制造商
     */
    @TableField(value = "manufacturer")
    @ApiModelProperty(value="制造商")
    private String manufacturer;

    /**
     * 序列编号
     */
    @TableField(value = "serial_number")
    @ApiModelProperty(value="序列编号")
    private String serialNumber;

    /**
     * 院区名称
     */
    @TableField(value = "institution_name")
    @ApiModelProperty(value="院区名称")
    private String institutionName;

    /**
     * 软件版本
     */
    @TableField(value = "software_version")
    @ApiModelProperty(value="软件版本")
    private String softwareVersion;

    /**
     * 实例UID
     */
    @TableField(value = "series_instance_UID")
    @ApiModelProperty(value="实例UID")
    private String seriesInstanceUid;

    /**
     * 院区地址
     */
    @TableField(value = "institution_address")
    @ApiModelProperty(value="院区地址")
    private String institutionAddress;

    /**
     * 厂家名称
     */
    @TableField(value = "manufacturer_model_name")
    @ApiModelProperty(value="厂家名称")
    private String manufacturerModelName;

    /**
     * 机构部门名称
     */
    @TableField(value = "institutional_department_name")
    @ApiModelProperty(value="机构部门名称")
    private String institutionalDepartmentName;

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