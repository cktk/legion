package com.esmooc.legion.edu.entity;


import lombok.Data;

/**
 * @ClassName
 * @Author Administrator
 * @Date 2021-1-11 10:00
 **/
@Data
public class SubmitPaper {
    /**
     * 试卷id
     */
    private String paperId;
    /**
     * 试卷答案
     */
    private String answers;

}
