package com.esmooc.legion.core.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daimao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页参数")
public class PageVo{

    @ApiModelProperty(value = "页号")
    private Integer pageNumber =1;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize =10;

    @ApiModelProperty(value = "排序字段")
    private String sort ;

    @ApiModelProperty(value = "排序方式 asc/desc")
    private String order;

}
