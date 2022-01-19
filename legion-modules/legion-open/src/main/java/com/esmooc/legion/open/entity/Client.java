package com.esmooc.legion.open.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Daimao
 */
@Data
@TableName("t_client")
@ApiModel(value = "第三方网站client信息")
public class Client extends BaseEntity {



    @ApiModelProperty(value = "网站名称")
    private String name;

    @ApiModelProperty(value = "网站Logo")
    private String logo;

    @ApiModelProperty(value = "网站主页")
    private String homeUri;

    @ApiModelProperty(value = "秘钥")
    private String clientSecret;

    @ApiModelProperty(value = "成功授权后的回调地址")
    private String redirectUri;

    @ApiModelProperty(value = "自动授权 默认false")
    private Boolean autoApprove = false;
}
