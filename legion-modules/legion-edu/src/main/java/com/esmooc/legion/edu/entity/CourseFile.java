package com.esmooc.legion.edu.entity;

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

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 14:42
 * @Description:
 */

/**
 * 课程文件附件业务表
 */
@ApiModel(value = "课程文件附件业务表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_course_file")
public class CourseFile {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private String id;

    /**
     * 文件本地名称
     */
    @TableField(value = "file_name")
    @ApiModelProperty(value = "文件本地名称")
    private String fileName;

    @TableField(value = "url")
    @ApiModelProperty(value = "文件链接")
    private String url;

    /**
     * 文件类型(1课件 2视屏)
     */
    @TableField(value = "file_type")
    @ApiModelProperty(value = "文件类型(1课件 2视屏)")
    private Integer fileType;

    /**
     * 文件名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value = "文件名称")
    private String name;

    /**
     * 课程ID
     */
    @TableField(value = "course_id")
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    /**
     * 序号
     */
    @TableField(value = "`number`")
    @ApiModelProperty(value = "序号")
    private Integer number;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 删除标志（1代表存在 0代表删除）
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value = "删除标志（1代表存在 0代表删除）")
    private String delFlag;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "")
    private String updateBy;
}
