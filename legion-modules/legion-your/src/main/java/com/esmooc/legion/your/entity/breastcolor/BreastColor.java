package com.esmooc.legion.your.entity.breastcolor;

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

@ApiModel(value="breast_color")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "breast_color")
public class BreastColor {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "context")
    @ApiModelProperty(value="")
    private String context;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addTime;
}