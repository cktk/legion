package com.esmooc.legion.autochat.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("t_auto_chat")
@ApiModel(value = "问答助手客服")
public class AutoChat extends BaseEntity {



    @ApiModelProperty(value = "问题标题")
    private String title;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    @ApiModelProperty(value = "回答")
    private String content;

    @ApiModelProperty(value = "热门消息")
    private Boolean hot = false;

    @ApiModelProperty(value = "开启反馈（赞踩）")
    private Boolean evaluable = true;

    @ApiModelProperty(value = "点赞数")
    private Integer good = 0;

    @ApiModelProperty(value = "踩数")
    private Integer bad = 0;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

    @TableField(exist = false)
    @ApiModelProperty(value = "回答纯文本")
    private String contentText;
}
