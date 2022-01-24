package com.esmooc.legion.core.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Daimao
 */
@Data
@Accessors(chain = true)
public class UserVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;


    @ApiModelProperty(value = "密码")
    @NotNull(message = "不能为空")
    private String password;


    @ApiModelProperty(value = "手机")
    @Pattern(regexp = NameUtil.regMobile, message = "11位手机号格式不正确")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = NameUtil.regEmail, message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "省市县地址")
    private String address;

    @ApiModelProperty(value = "街道地址")
    private String street;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "密码强度")
    private String passStrength;

    @ApiModelProperty(value = "用户头像")
    private String avatar ;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    private Integer type;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer status;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @ApiModelProperty(value = "所属部门id")
    private String departmentId;

    @ApiModelProperty(value = "所属部门名称")
    private String departmentTitle;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private Date birth;

}
