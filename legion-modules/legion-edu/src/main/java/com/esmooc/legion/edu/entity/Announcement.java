package com.esmooc.legion.edu.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Announcement
 * @Author Administrator
 * @Date 2021-1-5 9:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement extends BaseEntity {


    /**
     * 标题
     */
    @ExcelProperty("标题")
    private String title;

    /**
     * 内容
     */
    @ExcelProperty("内容")
    private String content;

    /**
     * 发布范围
     */
    private String userIds;

}
