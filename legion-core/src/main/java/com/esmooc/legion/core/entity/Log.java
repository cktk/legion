package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.utils.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Daimao
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_log")
@ApiModel(value = "日志")
public class Log extends BaseEntity {



    @TableField(value = "`name`")
    @ApiModelProperty(value = "方法操作名称")
    private String name;

    @TableField(value = "log_type")
    @ApiModelProperty(value = "日志类型 0登陆日志 1操作日志")
    private String logType;

    @TableField(value = "request_url")
    @ApiModelProperty(value = "请求路径")
    private String requestUrl;

    @TableField(value = "request_type")
    @ApiModelProperty(value = "请求类型")
    private String requestType;

    @TableField(value = "request_param")
    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @TableField(value = "username")
    @ApiModelProperty(value = "请求用户")
    private String username;

    @TableField(value = "ip")
    @ApiModelProperty(value = "ip")
    private String ip;

    @TableField(value = "ip_info")
    @ApiModelProperty(value = "ip信息")
    private String ipInfo;

    @TableField(value = "device")
    @ApiModelProperty(value = "设备信息")
    private String device;

    @TableField(value = "cost_time")
    @ApiModelProperty(value = "花费时间")
    private long costTime;

    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {

        this.requestParam = ObjectUtil.mapToString(paramMap);
    }

}
