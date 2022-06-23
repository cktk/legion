package com.esmooc.legion.core.entity;

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
@TableName("t_message")
@ApiModel(value = "消息")
public class Message extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "title")
    @ApiModelProperty(value = "标题")
    private String title;

    @TableField(value = "content")
    @ApiModelProperty(value = "内容")
    private String content;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "消息类型")
    private String type;

    @TableField(value = "create_send")
    @ApiModelProperty(value = "新创建账号也推送")
    private Boolean createSend;

    @TableField(value = "is_template")
    @ApiModelProperty(value = "是否为模版消息")
    private Boolean isTemplate;

    @TableField(exist = false)
    @ApiModelProperty(value = "纯文本内容")
    private String contentText;


    @TableField(exist = false)
    @ApiModelProperty(value = "发送范围")
    private Integer range;


    @TableField(exist = false)
    @ApiModelProperty(value = "发送指定用户id")
    private String[] userIds;
}
