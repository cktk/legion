package com.esmooc.legion.your.entity.breastleftandrightcolor;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "breast_left_color")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "breast_left_color")
public class BreastLeftColor {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @TableField(value = "ev1")
    @ApiModelProperty(value="")
    private String ev1;

    @TableField(value = "ev2")
    @ApiModelProperty(value="")
    private String ev2;

    @TableField(value = "ev3")
    @ApiModelProperty(value="")
    private String ev3;

    @TableField(value = "ev4")
    @ApiModelProperty(value="")
    private String ev4;

    @TableField(value = "`level`")
    @ApiModelProperty(value="")
    private String level;

    @TableField(value = "nang")
    @ApiModelProperty(value="")
    private String nang;

    @TableField(value = "shixing")
    @ApiModelProperty(value="")
    private String shixing;

    @TableField(value = "xingtai")
    @ApiModelProperty(value="")
    private String xingtai;

    @TableField(value = "fangxiang")
    @ApiModelProperty(value="")
    private String fangxiang;

    @TableField(value = "bianjie")
    @ApiModelProperty(value="")
    private String bianjie;

    @TableField(value = "bianyuan")
    @ApiModelProperty(value="")
    private String bianyuan;

    @TableField(value = "neihui")
    @ApiModelProperty(value="")
    private String neihui;

    @TableField(value = "houhui")
    @ApiModelProperty(value="")
    private String houhui;

    @TableField(value = "gaihua")
    @ApiModelProperty(value="")
    private String gaihua;

    @TableField(value = "xueliu")
    @ApiModelProperty(value="")
    private String xueliu;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addTime;
}