package com.esmooc.legion.edu.entity.vo;


import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StuPdfCourseVO extends BaseEntity {

    /**
     * 身份证号
     */
    private String idCard;


    /**
     * 课程名称
     */
    private String courseTitle;

    /**
     * 学员编号
     */
    private String userId;

    /**
     * 用户类型（00非学员 01系统学员 02注册学员）
     */
    private String userType;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 联系方式
     */
    private String phonenumber;

    /**
     * 所属单位
     */
    private String deptName;

    /**
     * 视频学习时间
     */
    private String time;

    /**
     * pdf学习时间
     */
    private long pdfTime;

    /**
     * 学习时间
     */
    private String learningTime;

    /**
     * 学习状态
     */
    private String studyType;

    /**
     * 行政区划
     */
    private String province;
    private String au;
    private String flagCounty;
    private String division;

    private String courseType;

    /**
     * 性别
     */
    private String sex;

}
