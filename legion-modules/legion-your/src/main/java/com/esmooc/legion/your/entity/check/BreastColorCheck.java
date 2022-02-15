package com.esmooc.legion.your.entity.check;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@ApiModel(value="breast_color_check")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "breast_color_check")
public class BreastColorCheck {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @TableField(value = "checkOrg")
    @NotBlank(message = "检查机构不能为空")
    @ApiModelProperty(value="")
    private String checkOrg;

    @TableField(value = "checkUser")
    @ApiModelProperty(value="")
    @NotBlank(message = "检查人不能为空")
    private String checkUser;

    @TableField(value = "checkDate")
    @ApiModelProperty(value="")
    @NotNull(message = "日期不能为空")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addTime;
}