package com.esmooc.legion.open.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ExamPaper
 * @Author Administrator
 * @Date 2020-12-30 9:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaper {

    private String id;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 考试用户Id
     */
    private String userId;
    /**
     * 分数
     */
    private String grade;
    /**
     * 历史问题json
     */
    private String historyHquestion;
    /**
     * 是否及格
     */
    private String isPass;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 是否删除
     */
    private String isDelete;
    /**
     * 类型  练习：0
     * 考试：1
     */
    private String type;
    /**
     * 课程名称
     */
    private String clazzName;

    /**
     * 证书编号
     */
    private String certificate;

}
