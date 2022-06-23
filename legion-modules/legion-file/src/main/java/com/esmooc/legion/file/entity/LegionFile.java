package com.esmooc.legion.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
@TableName("t_file")
@ApiModel(value = "文件")
public class LegionFile extends LegionBaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "原文件名")
    private String name;

    @TableField(value = "f_key")
    @ApiModelProperty(value = "存储文件名")
    private String fKey;

    @TableField(value = "`size`")
    @ApiModelProperty(value = "大小")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;

    @TableField(value = "`type`")
    @ApiModelProperty(value = "文件类型")
    private String type;

    @TableField(value = "url")
    @ApiModelProperty(value = "路径")
    private String url;

    @TableField(value = "`location`")
    @ApiModelProperty(value = "存储位置 0本地 1七牛 2阿里 3腾讯 4MinIO")
    private Integer location;


    @TableField(exist = false)
    @ApiModelProperty(value = "上传者用户名")
    private String nickname;

    @TableField(value = "category_id")
    @ApiModelProperty(value = "类别id")
    private String categoryId;
}
