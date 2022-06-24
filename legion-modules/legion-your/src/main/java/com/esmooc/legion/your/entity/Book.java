package com.esmooc.legion.your.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/03/11/ 13:27
 * @Description:
 */
@ApiModel(value="book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "book")
public class Book  extends LegionBaseEntity {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private String id;

    @TableField(value = "title")
    @ApiModelProperty(value="")
    private String title;

    @TableField(value = "url")
    @ApiModelProperty(value="")
    private String url;

    @TableField(value = "pid")
    @ApiModelProperty(value="")
    private Long pid;

    @TableField(value = "`type`")
    @ApiModelProperty(value="")
    private String type;

    @TableField(value = "`data`")
    @ApiModelProperty(value="")
    private String data;
}
