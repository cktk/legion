package com.esmooc.legion.pacs.entity.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: studies
 * @Description:
 * @date 2021年12月19日20点49分
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudiesVo {
    //病人姓名
    String name;
    //病历号
    String patientID;
    //开始时间
    Date startTime;
    //结束时间
    Date endTime;
    //类型
    String modality;
    //病人年龄
    String patientAge;
    //病人性别
    String patientSex;
    //病人姓名
    String patientName;

}
