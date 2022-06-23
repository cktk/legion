package com.esmooc.legion.social.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
@TableName("t_social")
@ApiModel(value = "文件")
public class Social extends LegionBaseEntity {

    @TableField(value = "open_id")
    @ApiModelProperty(value = "社交账号唯一id")
    private String openId;

    @TableField(value = "platform")
    @ApiModelProperty(value = "社交账号平台 0Github 1QQ 2微信 3微博")
    private Integer platform;

    @TableField(value = "username")
    @ApiModelProperty(value = "社交账号用户名")
    private String username;

    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    @TableField(value = "relate_username")
    @ApiModelProperty(value = "绑定用户账号")
    private String relateUsername;


    @TableField(exist = false)
    @ApiModelProperty(value = "是否绑定")
    private Boolean isRelated;


    @TableField(exist = false)
    @ApiModelProperty(value = "绑定用户名")
    private String nickname;
}
