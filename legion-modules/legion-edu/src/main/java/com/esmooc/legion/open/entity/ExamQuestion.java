package com.esmooc.legion.open.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExamQuestion
 * @Author Administrator
 * @Date 2020-12-29 16:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestion extends BaseEntity {

    /**
     * 题目
     */
    @ExcelProperty("题目")
    private String title;
    /**
     * 选项
     */
    @ExcelProperty("选项")
    private String options;
    /**
     * 答案
     */
    @ExcelProperty("答案")
    private String answers;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 类型
     */
    @ExcelProperty("类型")
    private String type;
    /**
     * 是否删除
     */
    private String isDelete;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 解析
     */
    @ExcelProperty("解析")
    private String analysis;
    /**
     * 类别id
     */
    @ExcelProperty("类别")
    private String majorId;
    /**
     * 题库id
     */
    private String bankId;
    /**
     * 你的答案
     */
    private String yourAnswers;
    /**
     * 答案是否正确
     */
    private String isItRightOrNot;

}
