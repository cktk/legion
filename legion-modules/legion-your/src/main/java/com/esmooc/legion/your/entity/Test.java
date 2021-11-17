package com.esmooc.legion.your.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.LegionBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/** 
* @ClassName: Test
* @version 1.0 
* @author Daimao
* @Description:
* @date 2021年11月12日18点04分
*
**/
@ApiModel(value="t_test")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_test")
public class Test extends LegionBaseEntity {
    /**
     * 姓名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="姓名")
    private String name;

    /**
     * 1男2女

     */
    @TableField(value = "sex")
    @ApiModelProperty(value="1男2女,")
    private String sex;

    @TableField(value = "test")
    @ApiModelProperty(value="")
    private String test;

    /**
     * 身份证
     */
    @TableField(value = "car_id")
    @ApiModelProperty(value="身份证")
    private String carId;

    /**
     * 手机好
     */
    @TableField(value = "phone")
    @ApiModelProperty(value="手机好")
    private String phone;
}