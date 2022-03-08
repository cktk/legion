package com.esmooc.legion.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 * @Date: 2022/03/02/ 10:47
 * @Description:
 */

/**
 * 数据源表
 */
@ApiModel(value = "数据源表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_datasource_conf")
public class DatasourceConf extends LegionBaseEntity {


    /**
     * 别名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "别名")
    private String name;

    /**
     * jdbcurl
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "jdbcurl")
    private String url;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;


    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    /**
     * 数据库类型
     */
    @TableField(value = "ds_type")
    @ApiModelProperty(value = "数据库类型")
    private String dsType;

    /**
     * 配置类型
     */
    @TableField(value = "conf_type")
    @ApiModelProperty(value = "配置类型")
    private String confType;

    /**
     * 数据库名称
     */
    @TableField(value = "ds_name")
    @ApiModelProperty(value = "数据库名称")
    private String dsName;

    /**
     * 实例
     */
    @TableField(value = "`instance`")
    @ApiModelProperty(value = "实例")
    private String instance;

    /**
     * 端口
     */
    @TableField(value = "port")
    @ApiModelProperty(value = "端口")
    private Integer port;

    /**
     * 主机
     */
    @TableField(value = "`host`")
    @ApiModelProperty(value = "主机")
    private String host;
}