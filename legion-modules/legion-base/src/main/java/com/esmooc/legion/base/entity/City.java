package com.esmooc.legion.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 * @Date: 2022/02/16/ 15:06
 * @Description:
 */
@ApiModel(value = "t_city")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_city")
public class City {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "唯一标识")
    private String id;
    /**
     * 上一级 id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "上一级 id")
    private Long parentId;

    /**
     * 轻路径
     */
    @TableField(value = "light_path")
    @ApiModelProperty(value = "轻路径")
    private String lightPath;

    /**
     * ID
     */
    @TableField(value = "area_code")
    @ApiModelProperty(value = "ID")
    private String areaCode;

    @TableField(value = "parent_area_code")
    @ApiModelProperty(value = "轻路径")
    private String parentAreaCode;

    @TableField(value = "area_light_path")
    @ApiModelProperty(value = "轻路径行政编码")
    private String areaLightPath;

    /**
     * 地区名称
     */
    @TableField(value = "area_name")
    @ApiModelProperty(value = "地区名称")
    private String areaName;

    /**
     * 行政区划
     */
    @TableField(value = "area_name_division")
    @ApiModelProperty(value = "行政区划")
    private String areaNameDivision;

    @TableField(value = "all_name")
    @ApiModelProperty(value = "地址名称全程")
    private String allName;

    /**
     * 行政区划全称
     */
    @TableField(value = "all_name_division")
    @ApiModelProperty(value = "行政区划全称")
    private String allNameDivision;


    @TableField(value = "lat")
    @ApiModelProperty(value = "经度")
    private String lat;

    @TableField(value = "lng")
    @ApiModelProperty(value = "纬度")
    private String lng;

    /**
     * 0国 1省 2市 3县 4乡 5镇
     */
    @TableField(value = "`level`")
    @ApiModelProperty(value = "0国 1省 2市 3县 4乡 5镇")
    private Integer level;

    @TableField(value = "levelvalue")
    @ApiModelProperty(value = "层级")
    private Long levelvalue;

    /**
     * 区域排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "区域排序")
    private Integer sort;


    /**
     * 行政区划类别
     */
    @TableField(value = "xzqhlb")
    @ApiModelProperty(value = "行政区划类别")
    private Integer xzqhlb;
}
