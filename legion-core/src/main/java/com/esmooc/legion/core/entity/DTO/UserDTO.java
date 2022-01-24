package com.esmooc.legion.core.entity.DTO;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author 呆猫
 * @Date: 2022/01/20/ 14:53
 * @Description:
 */
@Data
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "用户")
public class UserDTO {

    private String username;

    private String nickname;

    private String mobile;

    private String email;

    private String address;

    private String street;
    private String sex;
    private String passStrength;

    private Integer type;

    private Integer status;

    private String description;

    private String departmentId;

    private String departmentTitle;

}
