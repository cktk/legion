package com.esmooc.legion.open.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程学习状态业务对象 edu_learning_record
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 学员编号
     */
    private String userId;

    /**
     * 学习状态（0未学习 1学习中 2已学习）
     */
    @ExcelProperty("学习状态")
    private int studyType;

    /**
     * 学员名字
     */
    @ExcelProperty("学员名字")
    private String userName;

    /**
     * $column.columnComment
     */
    @ExcelProperty("学员名字")
    private String year;

    private int noPassCount;

}
