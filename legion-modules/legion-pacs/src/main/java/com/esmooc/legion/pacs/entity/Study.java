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

import java.util.Date;


/**
 * @ClassName: Study
 * @version 1.0
 * @author Daimao
 * @Description:
 * @date 2021年12月21日15点54分
 **/
/**
    * 研究
    */
@ApiModel(value="研究")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_study")
public class Study extends BaseEntity {
    /**
     * 形态
     */
    @TableField(value = "modality")
    @ApiModelProperty(value="形态")
    private String modality;

    /**
     * 日期
     */
    @TableField(value = "study_date_time")
    @ApiModelProperty(value="日期")
    private Date studyDateTime;

    /**
     * 学习ID
     */
    @TableField(value = "study_id")
    @ApiModelProperty(value="学习ID")
    private String studyId;

    /**
     * 患者id
     */
    @TableField(value = "patient_id")
    @ApiModelProperty(value="患者id")
    private String patientId;

    /**
     * 患者年龄
     */
    @TableField(value = "patient_age")
    @ApiModelProperty(value="患者年龄")
    private Integer patientAge;

    /**
     * 数量
     */
    @TableField(value = "accession_number")
    @ApiModelProperty(value="数量")
    private String accessionNumber;

    /**
     * 描述
     */
    @TableField(value = "study_description")
    @ApiModelProperty(value="描述")
    private String studyDescription;

    /**
     * 实例ID
     */
    @TableField(value = "study_instance_UID")
    @ApiModelProperty(value="实例ID")
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
