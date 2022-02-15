package com.esmooc.legion.your.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author 呆猫
 * @Date: 2022/02/10/ 15:38
 * @Description:
 */
@ApiModel(value = "screening.user_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_user")
public class UserUser {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    @TableField(value = "card")
    @ApiModelProperty(value = "编号")
    private String card;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "姓名")
    private String name;

    @TableField(value = "gender")
    @ApiModelProperty(value = "性别")
    private String gender;

    @TableField(value = "nation")
    @ApiModelProperty(value = "国家")
    private String nation;


    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "idcard")
    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @TableField(value = "education")
    @ApiModelProperty(value = "学历")
    private String education;

    @TableField(value = "upload")
    @ApiModelProperty(value = "")
    private String upload;

    @TableField(value = "imgurl")
    @ApiModelProperty(value = "")
    private String imgurl;

    @TableField(value = "ADDTIME")
    @ApiModelProperty(value = "")
    private Date addtime;

    @TableField(value = "upload_flag")
    @ApiModelProperty(value = "")
    private String uploadFlag;

    @TableField(value = "download_flag")
    @ApiModelProperty(value = "")
    private String downloadFlag;

    @TableField(value = "area_code")
    @ApiModelProperty(value = "")
    private String areaCode;

    @TableField(value = "town_code")
    @ApiModelProperty(value = "")
    private String townCode;

    @TableField(value = "village_code")
    @ApiModelProperty(value = "")
    private String villageCode;

    @TableField(value = "address")
    @ApiModelProperty(value = "")
    private String address;

    @TableField(value = "district")
    @ApiModelProperty(value = "")
    private String district;

    @TableField(value = "town")
    @ApiModelProperty(value = "")
    private String town;

    @TableField(value = "community")
    @ApiModelProperty(value = "")
    private String community;

    @TableField(value = "addr")
    @ApiModelProperty(value = "")
    private String addr;

    /**
     * 登记次数
     */
    @TableField(value = "`number`")
    @ApiModelProperty(value = "登记次数")
    private Integer number;

    /**
     * 患者所在部门
     */
    @TableField(value = "department_id")
    @ApiModelProperty(value = "患者所在部门")
    private String departmentId;


}
