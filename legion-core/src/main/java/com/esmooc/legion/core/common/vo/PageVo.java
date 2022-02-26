package com.esmooc.legion.core.common.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author DaiMao
 */
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页号")
    private int pageNumber = 1;

    @ApiModelProperty(value = "页面大小")
    private int pageSize = 10;

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方式 asc/desc")
    private String order;

    public PageVo() {
    }


    public PageVo(int pageNumber, int pageSize, String sort, String order) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
        this.order = order;
    }

    public PageVo(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public PageVo setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageVo setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PageVo setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public PageVo setOrder(String order) {
        this.order = order;
        return this;
    }
}
