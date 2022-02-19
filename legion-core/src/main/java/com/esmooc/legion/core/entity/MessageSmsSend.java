package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.common.constant.SystemConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 呆猫
 * @Date: 2022/02/17/ 23:37
 * @Description:
 */

/**
 * 短信发送记录
 */
@ApiModel(value = "短信发送记录")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_message_sms_send")
public class MessageSmsSend extends LegionBaseEntity {
    /**
     * 模板类型
     */
    @TableField(value = "template_type")
    @ApiModelProperty(value = "模板类型")
    private String templateType;

    /**
     * 模板id
     */
    @TableField(value = "template_id")
    @ApiModelProperty(value = "模板id")
    private String templateId;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 发送短信配置id
     */
    @TableField(value = "setting_id")
    @ApiModelProperty(value = "发送短信配置id")
    private String settingId;

    /**
     * 短信内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "短信内容")
    private String content;

    /**
     * 接收人手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "接收人手机号")
    private String phone;

    /**
     * 接收人手机号
     */
    @TableField(value = "send_time")
    @ApiModelProperty(value = "接收人手机号")
    private Date sendTime;

    /**
     * 送人id
     */
    @TableField(value = "send_user_id")
    @ApiModelProperty(value = "送人id")
    private String sendUserId;

    /**
     * 送人名字
     */
    @TableField(value = "send_user_name")
    @ApiModelProperty(value = "送人名字")
    private String sendUserName;


    /**
     * 引起发送短信业务ID
     */
    @TableField(value = "work_id")
    @ApiModelProperty(value = "引起发送短信业务ID")
    private String workId;

    /**
     * 是否重发
     */
    @TableField(value = "retry_send")
    @ApiModelProperty(value = "是否重发")
    private String retrySend;

    /**
     * 重发记录id
     */
    @TableField(value = "retry_id")
    @ApiModelProperty(value = "重发记录id")
    private Long retryId;


    /**
     * 运营商回执ID
     */
    @TableField(value = "biz_id")
    @ApiModelProperty(value = "运营商回执ID")
    private String bizId;


    /**
     * 业务类型
     */
    @TableField(value = "work_type")
    @ApiModelProperty(value = "业务类型")
    private String workType;
    /**
     * 发送状态
     */
    @TableField(value = "send_status")
    @ApiModelProperty(value = "发送状态")
    private String sendStatus;


    @TableField(value = "code")
    @ApiModelProperty(value = "运营商返回的状态码")
    private String code = SystemConstant.FLAG_N;


    /**
     * 运营商返回的原始数据
     */
    @TableField(value = "send_res")
    @ApiModelProperty(value = "运营商返回的原始数据")
    private String sendRes;

    //
    @TableField(value = "status")
    @ApiModelProperty(value = "系统判断是否成功的状态")
    private String status = SystemConstant.FLAG_N;
    //
    @TableField(value = "err_code")
    @ApiModelProperty(value = "错误代码")
    private String errCode;

    @TableField(value = "err_msg")
    @ApiModelProperty(value = "错误信息")
    private String errMsg;


    @TableField(value = "err_type")
    @ApiModelProperty(value = "错误类型 网络超时 什么的错误类型")
    private String errType;


}