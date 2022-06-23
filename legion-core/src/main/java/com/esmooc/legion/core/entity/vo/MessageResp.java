package com.esmooc.legion.core.entity.vo;

import com.esmooc.legion.core.common.constant.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResp {
    private String rspcod = SystemConstant.SMS_ERROR_ERR;
    private Boolean success = false;
    private String msgGroup = "";
}
