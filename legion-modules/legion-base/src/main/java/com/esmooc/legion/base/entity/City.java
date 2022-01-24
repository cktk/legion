package com.esmooc.legion.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 * @Date: 2022/01/20/ 14:24
 * @Description:
 */
@ApiModel(value = "t_city")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_city")
public class City {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 上一级 id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "上一级 id")
    private String parentId;

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
    @ApiModelProperty(value = "")
    private String parentAreaCode;

    @TableField(value = "area_light_path")
    @ApiModelProperty(value = "")
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
    @ApiModelProperty(value = "")
    private String allName;

    /**
     * 行政区划全称
     */
    @TableField(value = "all_name_division")
    @ApiModelProperty(value = "行政区划全称")
    private String allNameDivision;

    @TableField(value = "short_name")
    @ApiModelProperty(value = "")
    private String shortName;

    @TableField(value = "lat")
    @ApiModelProperty(value = "")
    private String lat;

    @TableField(value = "lng")
    @ApiModelProperty(value = "")
    private String lng;

    /**
     * 0国 1省 2市 3县 4乡 5镇
     */
    @TableField(value = "`level`")
    @ApiModelProperty(value = "0国 1省 2市 3县 4乡 5镇")
    private Integer level;

    @TableField(value = "levelvalue")
    @ApiModelProperty(value = "")
    private String levelvalue;

    /**
     * 区域排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "区域排序")
    private String sort;

    /**
     * 是否是自定义单位 0否 1是
     */
    @TableField(value = "is_custom")
    @ApiModelProperty(value = "是否是自定义单位 0否 1是")
    private String isCustom;

    /**
     * 1 直属队
     */
    @TableField(value = "is_zsd")
    @ApiModelProperty(value = "1 直属队")
    private Byte isZsd;

    /**
     * 1删除    0未删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "1删除    0未删除")
    private Byte isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    @TableField(value = "xzqh_json")
    @ApiModelProperty(value = "")
    private String xzqhJson;

    /**
     * 版本
     */
    @TableField(value = "version")
    @ApiModelProperty(value = "版本")
    private Integer version;

    /**
     * 密钥
     */
    @TableField(value = "secret")
    @ApiModelProperty(value = "密钥")
    private String secret;

    /**
     * 单位类别
     */
    @TableField(value = "dwlb")
    @ApiModelProperty(value = "单位类别")
    private Integer dwlb;

    /**
     * 武装工作性质
     */
    @TableField(value = "wzgzxz")
    @ApiModelProperty(value = "武装工作性质")
    private Integer wzgzxz;

    /**
     * 行政区划类别
     */
    @TableField(value = "xzqhlb")
    @ApiModelProperty(value = "行政区划类别")
    private Integer xzqhlb;
}
