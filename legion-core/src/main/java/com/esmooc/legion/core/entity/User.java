package com.esmooc.legion.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.esmooc.legion.core.entity.vo.PermissionDTO;
import com.esmooc.legion.core.entity.vo.RoleDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
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
@TableName("t_user")
@ApiModel(value = "用户")
public class User extends BaseEntity {



    @ApiModelProperty(value = "登录名")
    @Pattern(regexp = NameUtil.regUsername, message = "登录账号不能包含特殊字符且长度不能>16")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "不能为空")
    private String password;

    @ApiModelProperty(value = "用户名/昵称/姓名")
    @NotBlank(message = "不能为空")
    @Size(max = 20, message = "昵称长度不能超过20")
    private String nickname;

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

    @TableField(value = "pass_strength")
    @ApiModelProperty(value = "密码强度")
    private String passStrength;

    @ApiModelProperty(value = "用户头像")
    private String avatar = CommonConstant.USER_DEFAULT_AVATAR;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    private Integer type = CommonConstant.USER_TYPE_NORMAL;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer status = CommonConstant.USER_STATUS_NORMAL;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @TableField(value = "department_id")
    @ApiModelProperty(value = "所属部门id")
    private String departmentId;

    @TableField(value = "department_title")
    @ApiModelProperty(value = "所属部门名称")
    private String departmentTitle;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private Date birth;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户拥有角色")
    private List<RoleDTO> roles;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户拥有的权限")
    private List<PermissionDTO> permissions;

    @TableField(exist = false)
    @ApiModelProperty(value = "导入数据时使用")
    private Integer defaultRole;
}
