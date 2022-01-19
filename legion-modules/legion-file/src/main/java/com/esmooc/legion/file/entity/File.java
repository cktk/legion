package com.esmooc.legion.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
@TableName("t_file")
@ApiModel(value = "文件")
public class File extends BaseEntity {

    @ApiModelProperty(value = "原文件名")
    private String name;

    @TableField(value = "f_key")
    @ApiModelProperty(value = "存储文件名")
    private String fKey;

    @ApiModelProperty(value = "大小")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long size;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "路径")
    private String url;

    @ApiModelProperty(value = "存储位置 0本地 1七牛 2阿里 3腾讯 4MinIO")
    private Integer location;

    @TableField(exist = false)
    @ApiModelProperty(value = "上传者用户名")
    private String nickname;
}
