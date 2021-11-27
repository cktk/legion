package com.esmooc.legion.core.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: RoleDTO
 * @Description:
 * @date 2021年11月22日16点14分
 **/
@Data
@Accessors(chain = true)
public class RoleDTO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "备注")
    private String description;
}
