package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
@TableName("t_message_send")
@ApiModel(value = "消息发送详情")
public class MessageSend extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "message_id")
    @ApiModelProperty(value = "关联消息id")
    private String messageId;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "关联用户id")
    private String userId;

    @TableField(value = "params")
    @ApiModelProperty(value = "消息参数")
    private String params;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态 0默认未读 1已读 2回收站")
    private Integer status = CommonConstant.MESSAGE_STATUS_UNREAD;

    @TableField(exist = false)
    @ApiModelProperty(value = "发送登录名")
    private String username;

    @TableField(exist = false)
    @ApiModelProperty(value = "发送用户名")
    private String nickname;

    @TableField(exist = false)
    @ApiModelProperty(value = "消息标题")
    private String title;

    @TableField(exist = false)
    @ApiModelProperty(value = "消息内容")
    private String content;

    @TableField(exist = false)
    @ApiModelProperty(value = "消息类型")
    private String type;
}
