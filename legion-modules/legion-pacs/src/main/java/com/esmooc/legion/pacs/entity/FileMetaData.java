package com.esmooc.legion.pacs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @ClassName: FileMetaData
 * @version 1.0
 * @author Daimao
 * @Description:
 * @date 2021年12月21日15点54分
 **/
/**
    * 文件元数据
    */
@ApiModel(value="文件元数据")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_file_meta_data")
public class FileMetaData extends BaseEntity {
    /**
     * 路径
     */
    @TableField(value = "`path`")
    @ApiModelProperty(value="路径")
    private String path;

    /**
     * 大小
     */
    @TableField(value = "`size`")
    @ApiModelProperty(value="大小")
    private Long size;

    /**
     * 设备ID
     */
    @TableField(value = "device_id")
    @ApiModelProperty(value="设备ID")
    private String deviceId;

    /**
     * 序列ID
     */
    @TableField(value = "series_id")
    @ApiModelProperty(value="序列ID")
    private String seriesId;

    /**
     * 患者id
     */
    @TableField(value = "patient_id")
    @ApiModelProperty(value="患者id")
    private String patientId;

    /**
     * 实例ID
     */
    @TableField(value = "instance_id")
    @ApiModelProperty(value="实例ID")
    private String instanceId;

    /**
     * x实例UID
     */
    @TableField(value = "study_instance_UID")
    @ApiModelProperty(value="x实例UID")
    private String studyInstanceUid;

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
