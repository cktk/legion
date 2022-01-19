package com.esmooc.legion.open.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程文件附件业务对象 edu_course_file
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFile extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 文件本地名称
     */
    @ExcelProperty("文件本地名称")
    private String fileName;

    /**
     * 文件类型(1课件 2视屏)
     */
    @ExcelProperty("文件类型(1课件 2视屏)")
    private Long fileType;

    /**
     * 文件名称
     */
    @ExcelProperty("文件名称")
    private String name;

    /**
     * 课程ID
     */
    @ExcelProperty("课程ID")
    private String courseId;

    /**
     * 序号
     */
    @ExcelProperty("序号")
    private String number;


}
