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
* @ClassName: Storage
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 存储
    */
@ApiModel(value="存储")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "p_storage")
public class Storage extends BaseEntity {
    /**
     * 名字
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 使用
     */
    @TableField(value = "used")
    @ApiModelProperty(value="使用")
    private Integer used;

    /**
     * 总计
     */
    @TableField(value = "total")
    @ApiModelProperty(value="总计")
    private Integer total;

    /**
     * 类型
     */
    @TableField(value = "type_code")
    @ApiModelProperty(value="类型")
    private Integer typeCode;

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