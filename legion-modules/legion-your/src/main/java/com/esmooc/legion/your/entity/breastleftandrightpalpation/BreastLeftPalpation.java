package com.esmooc.legion.your.entity.breastleftandrightpalpation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel(value="breast_left_palpation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "breast_left_palpation")
public class BreastLeftPalpation {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "user_id")
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "zhengzhuang")
    @ApiModelProperty(value="")
    private String zhengzhuang;

    @TableField(value = "tizheng")
    @ApiModelProperty(value="")
    private String tizheng;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addTime;
}