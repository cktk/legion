package com.esmooc.legion.open.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.esmooc.legion.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 课程附件学习状态业务对象 edu_learning_record_file
 *
 * @author sun
 * @date 2020-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearningRecordFile extends BaseEntity {
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
     * 课程附件ID
     */
    private String fileId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty("结束时间")
    private Date endTime;

    /**
     * 学习时长
     */
    @ExcelProperty("学习时长")
    private Long time;

    private int type;


}
