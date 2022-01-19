package com.esmooc.legion.open.entity.vo;


import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuVideoCourseVO extends BaseEntity {

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
     * 性别
     */
    private String sex;

    /**
     * 联系方式
     */
    private String phonenumber;

    /**
     * 所属单位
     */
    private String deptName;

    /**
     * 学习时间
     */
    private String time;

    /**
     * 学习状态
     */
    private String studyType;

    /**
     * 所属单位
     */
    private String deptId;

    /**
     * 是否考试
     */
    private String test;

    /**
     * 考试成绩
     */
    private String grade;

    /**
     * 是否及格
     */
    private String isPass;

    /**
     * 行政区划
     */
    private String province;
    private String au;
    private String flagCounty;
    private String division;

    private String examId;

    private String courseType;

}
