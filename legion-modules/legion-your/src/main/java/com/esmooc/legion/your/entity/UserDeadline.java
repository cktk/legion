package com.esmooc.legion.your.entity;

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
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @Author 呆猫
 *
 * @Date: 2022/02/10/ 16:25
 * @Description:
 */
@ApiModel(value="screening.user_deadline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "screening.user_deadline")
@Accessors(chain = true)
public class UserDeadline {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "`status`")
    @ApiModelProperty(value="")
    private String status;

    @TableField(value = "upTime")
    @ApiModelProperty(value="")
    private Date uptime;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    private Integer userId;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addtime;
}
