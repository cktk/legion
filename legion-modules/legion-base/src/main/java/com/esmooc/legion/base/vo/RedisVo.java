package com.esmooc.legion.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Daimao
 */
@Data
@AllArgsConstructor
public class RedisVo {

    private String key;

    private String value;

    private Long expireTime;
}
