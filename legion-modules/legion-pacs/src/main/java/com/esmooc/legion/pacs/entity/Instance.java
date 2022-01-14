package com.esmooc.legion.pacs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
* @ClassName: Instance
* @version 1.0
* @author Daimao
* @Description:
* @date 2021年12月21日15点54分
*
**/
/**
    * 文件元数据
    */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="文件元数据")
@TableName(value = "p_instance")
@EqualsAndHashCode(callSuper=true)
public class Instance extends BaseEntity {
    /**
     * kvp
     */
    @TableField(value = "kvp")
    @ApiModelProperty(value="kvp")
    private String kvp;

    /**
     * 行
     */
    @TableField(value = "`rows`")
    @ApiModelProperty(value="行")
    private Integer rows;

    /**
     * 列
     */
    @TableField(value = "`columns`")
    @ApiModelProperty(value="列")
    private Integer columns;

    /**
     * 图像类型
     */
    @TableField(value = "image_type")
    @ApiModelProperty(value="图像类型")
    private String imageType;

    /**
     * 帧索引
     */
    @TableField(value = "frame_index")
    @ApiModelProperty(value="帧索引")
    private String frameIndex;

    /**
     * 状态码
     */
    @TableField(value = "status_code")
    @ApiModelProperty(value="状态码")
    private Integer statusCode;

    /**
     * sop类UID
     */
    @TableField(value = "sop_class_UID")
    @ApiModelProperty(value="sop类UID")
    private String sopClassUid;

    /**
     * 存储路径
     */
    @TableField(value = "storage_path")
    @ApiModelProperty(value="存储路径")
    private String storagePath;

    /**
     * 窗口尺寸
     */
    @TableField(value = "window_width")
    @ApiModelProperty(value="窗口尺寸")
    private String windowWidth;

    /**
     * 曝光时间
     */
    @TableField(value = "exposure_time")
    @ApiModelProperty(value="曝光时间")
    private Integer exposureTime;

    /**
     * 像素间距
     */
    @TableField(value = "pixel_spacing")
    @ApiModelProperty(value="像素间距")
    private Double pixelSpacing;

    /**
     * 窗口中心
     */
    @TableField(value = "window_center")
    @ApiModelProperty(value="窗口中心")
    private String windowCenter;

    /**
     * 片位置
     */
    @TableField(value = "slice_location")
    @ApiModelProperty(value="片位置")
    private Double sliceLocation;

    /**
     * 缩略图路径
     */
    @TableField(value = "thumbnail_path")
    @ApiModelProperty(value="缩略图路径")
    private String thumbnailPath;

    /**
     * 实例数量
     */
    @TableField(value = "instance_number")
    @ApiModelProperty(value="实例数量")
    private Integer instanceNumber;

    /**
     * 片厚度
     */
    @TableField(value = "slice_thickness")
    @ApiModelProperty(value="片厚度")
    private Double sliceThickness;

    /**
     * sop实例ID
     */
    @TableField(value = "sop_instance_UID")
    @ApiModelProperty(value="sop实例ID")
    private String sopInstanceUid;

    /**
     * 内容时间
     */
    @TableField(value = "content_date_time")
    @ApiModelProperty(value="内容时间")
    private Date contentDateTime;

    /**
     * x光电流
     */
    @TableField(value = "xray_tube_current")
    @ApiModelProperty(value="x光电流")
    private Integer xrayTubeCurrent;

    /**
     * 系列实例ID
     */
    @TableField(value = "series_instance_UID")
    @ApiModelProperty(value="系列实例ID")
    private String seriesInstanceUid;

    /**
     * 传输ID
     */
    @TableField(value = "transfer_syntax_UID")
    @ApiModelProperty(value="传输ID")
    private String transferSyntaxUid;

    /**
     * 拍摄日期
     */
    @TableField(value = "acquisition_date_time")
    @ApiModelProperty(value="拍摄日期")
    private Date acquisitionDateTime;

    /**
     * 媒体存储实例ID
     */
    @TableField(value = "media_storage_sop_instance_UID")
    @ApiModelProperty(value="媒体存储实例ID")
    private Date mediaStorageSopInstanceUid;

    /**
     * 数据权限标识
     */
    @TableField(value = "dept_id")
    @ApiModelProperty(value="数据权限标识")
    private String deptId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    @ApiModelProperty(value="租户id")
    private String tenantId;
}
