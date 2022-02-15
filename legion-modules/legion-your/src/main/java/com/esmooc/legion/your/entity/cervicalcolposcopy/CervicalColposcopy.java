package com.esmooc.legion.your.entity.cervicalcolposcopy;

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

/**
    * 阴道镜检查
    */
@ApiModel(value="阴道镜检查")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cervical_colposcopy")
public class CervicalColposcopy {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "`status`")
    @ApiModelProperty(value="")
    private String status;

    @TableField(value = "user_id")
    @ApiModelProperty(value="")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @TableField(value = "accept")
    @ApiModelProperty(value="")
    private String accept;

    @TableField(value = "reason")
    @ApiModelProperty(value="")
    private String reason;

    @TableField(value = "evaluate")
    @ApiModelProperty(value="")
    private String evaluate;

    @TableField(value = "preliminary")
    @ApiModelProperty(value="")
    private String preliminary;

    @TableField(value = "pathology")
    @ApiModelProperty(value="")
    private String pathology;

    @TableField(value = "checkOrg")
    @ApiModelProperty(value="")
    @NotBlank(message = "检查机构不能为空")
    private String checkOrg;

    @TableField(value = "checkUser")
    @ApiModelProperty(value="")
    @NotBlank(message = "检查人不能为空")
    private String checkUser;

    @TableField(value = "checkDate")
    @ApiModelProperty(value="")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "日期不能为空")
    private Date checkDate;

    @TableField(value = "preliminary2")
    @ApiModelProperty(value="")
    private String preliminary2;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value="")
    private Date addTime;
}