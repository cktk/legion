package com.esmooc.legion.core.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author DaiMao
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
}
