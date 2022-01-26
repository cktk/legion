package com.esmooc.legion.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Author 呆猫
 * @Date: 2022/01/26/ 19:19
 * @Description:
 */
@Data
@ApiModel(value = "考谁啊" +
        "")
public class Issue {
    String id;
    @ApiModelProperty(value = "用户id")
    ArrayList<String> userIds;
    @ApiModelProperty(value = "部门id")
    java.util.ArrayList<String> deptIds;
    @ApiModelProperty(value = "角色id")
    ArrayList<String> roleIds;
}
