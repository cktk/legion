package com.esmooc.legion.base.common.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 呆猫
 * @Date: 2022/02/16/ 17:02
 * @Description:
 */
@Data
@ToString
public class Address {

    private String province;
    private String city;
    private String county;
    private String town;
    private String village;

}
