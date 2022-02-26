package com.esmooc.legion.base.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author DaiMao
 */
@Data
@Accessors(chain = true)
public class QRStatusVo {

    private String status;

    private String accessToken;
}
