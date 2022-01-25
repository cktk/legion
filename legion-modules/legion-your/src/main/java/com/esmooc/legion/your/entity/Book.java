package com.esmooc.legion.your.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/25/ 00:44
 * @Description:
 */
@ApiModel(value="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu.book")
public class Book  {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private  Integer id ;

    @TableField(value = "title")
    @ApiModelProperty(value="")
    private String title;

    @TableField(value = "pid")
    @ApiModelProperty(value="")
    private Integer pid;

    @TableField(value = "url")
    @ApiModelProperty(value="")
    private String url;

    @TableField(value = "`data`")
    @ApiModelProperty(value="")
    private String data;

    @TableField(value = "type")
    private String type ;
}
