package com.esmooc.legion.your.entity.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqj
 * @createDate: 2022/2/14
 * @version: 1.0
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDto {
    private String startTime;
    private String endTime;
    private String areaCode;
    private String townCode;
}
