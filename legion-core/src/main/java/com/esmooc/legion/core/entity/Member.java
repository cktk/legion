package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.MemberConstant;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("app_member")
@ApiModel(value = "会员（注册用户）")

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {


    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "invite_code")
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "nickname")
    @ApiModelProperty(value = "昵称")
    @Size(max = 20, message = "昵称长度不能超过20")
    private String nickname;

    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机")
    @Pattern(regexp = NameUtil.regMobile, message = "11位手机号格式不正确")
    private String mobile;

    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    @TableField(value = "birth")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private Date birth;

    @TableField(value = "grade")
    @ApiModelProperty(value = "积分 默认0")
    private Integer grade = 0;

    @TableField(value = "`position`")
    @ApiModelProperty(value = "定位")
    private String position;

    @TableField(value = "address")
    @ApiModelProperty(value = "地区")
    private String address;

    @TableField(value = "description")
    @ApiModelProperty(value = "简介")
    private String description;

    @TableField(value = "invite_by")
    @ApiModelProperty(value = "邀请人")
    private String inviteBy;


    @TableField(value = "avatar")
    @ApiModelProperty(value = "会员头像")
    private String avatar = MemberConstant.MEMBER_DEFAULT_AVATAR;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "会员类型 默认0普通用户 1会员")
    private Integer type = MemberConstant.MEMBER_TYPE_NORMAL;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态 默认0正常 -1拉黑禁用")
    private Integer status = MemberConstant.MEMBER_STATUS_NORMAL;

    @TableField(value = "platform")
    @ApiModelProperty(value = "注册平台来源 -1未知 0PC/H5 1安卓 2苹果 3微信 4支付宝 5QQ 6字节 7百度")
    private Integer platform;

    @TableField(value = "permissions")
    @ApiModelProperty(value = "拥有权限信息 多个逗号分隔 默认MEMBER")
    private String permissions = MemberConstant.MEMBER_PERMISSION;

    @TableField(value = "vip_status")
    @ApiModelProperty(value = "VIP状态 默认0未开通 1已开通 2已过期")
    private Integer vipStatus = MemberConstant.MEMBER_VIP_NONE;

    @TableField(value = "vip_start_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会员开通时间")
    private Date vipStartTime;

    @TableField(value = "vip_end_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "会员到期时间")
    private Date vipEndTime;
}
