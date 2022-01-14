package com.esmooc.legion.core.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Daimao
 */
@Slf4j
public class SnowFlakeUtil {

    /**
     * 派号器workid：0~31
     * 机房datacenterid：0~31
     */
    private static Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    public static Long nextId() {
        return snowflake.nextId();
    }
}
