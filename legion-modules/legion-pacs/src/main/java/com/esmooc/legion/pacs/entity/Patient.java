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
* @ClassName: Patient
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 病人信息
    */
@ApiModel(value="病人信息")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_patient")
public class Patient extends BaseEntity {
    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 体重
     */
    @TableField(value = "weight")
    @ApiModelProperty(value="体重")
    private String weight;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    @ApiModelProperty(value="生日")
    private Date birthday;

    /**
     * 患者id
     */
    @TableField(value = "patient_id")
    @ApiModelProperty(value="患者id")
    private String patientId;

    /**
     * 怀孕状态
     */
    @TableField(value = "pregnancy_status")
    @ApiModelProperty(value="怀孕状态")
    private String pregnancyStatus;

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