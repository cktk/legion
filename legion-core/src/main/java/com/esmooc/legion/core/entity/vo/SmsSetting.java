package com.esmooc.legion.core.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
public class SmsSetting implements Serializable {


    @ApiModelProperty(value = "服务商")
    private String serviceName;

    @ApiModelProperty(value = "ak")
    private String accessKey;

    @ApiModelProperty(value = "sk")
    private String secretKey;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "签名")
    private String signName;

    @ApiModelProperty(value = "使用场景类型")
    private Integer type;

    @ApiModelProperty(value = "模版code")
    private String templateCode;

    @ApiModelProperty(value = "是否改变secrectKey")
    private Boolean changed;

    @ApiModelProperty(value = "服务商请求地址")
    private String url;

    @ApiModelProperty(value = "服务商请求地址")
    private String content;

    @ApiModelProperty(value = "用户名称")
    private String useName;


}
