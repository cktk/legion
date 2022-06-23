package com.esmooc.legion.open.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DaiMao
 */
@Data
@TableName("t_client")
@ApiModel(value = "第三方网站client信息")
public class Client extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "网站名称")
    private String name;

    @TableField(value = "logo")
    @ApiModelProperty(value = "网站Logo")
    private String logo;

    @TableField(value = "home_uri")
    @ApiModelProperty(value = "网站主页")
    private String homeUri;

    @TableField(value = "client_secret")
    @ApiModelProperty(value = "秘钥")
    private String clientSecret;

    @TableField(value = "redirect_uri")
    @ApiModelProperty(value = "成功授权后的回调地址")
    private String redirectUri;

    @TableField(value = "auto_approve")
    @ApiModelProperty(value = "自动授权 默认false")
    private Boolean autoApprove = false;
}
