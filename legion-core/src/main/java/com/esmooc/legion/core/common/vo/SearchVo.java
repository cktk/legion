package com.esmooc.legion.core.common.vo;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DaiMao
 */
@Data
public class SearchVo implements Serializable {

    @ApiModelProperty(value = "起始日期")
    private String startDate = DateUtil.formatDate(DateUtil.yesterday());

    @ApiModelProperty(value = "结束日期")
    private String endDate =DateUtil.formatDateTime(new Date()) ;



    @ApiModelProperty(value = "模糊查询条件")
    private String condition;
}
