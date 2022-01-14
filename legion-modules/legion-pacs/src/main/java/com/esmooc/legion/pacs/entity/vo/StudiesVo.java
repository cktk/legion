package com.esmooc.legion.pacs.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "筛选参数")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudiesVo {
    //病人姓名
    @ApiModelProperty(value = "姓名")
    String name;
    //病人姓名
    @ApiModelProperty(value = "姓名")
    String patientName;
    //病历号
    @ApiModelProperty(value = "病历号")
    String patientID;
    //开始时间
    @ApiModelProperty(value = "开始时间")
    Date startTime;
    //结束时间
    @ApiModelProperty(value = "结束时间")
    Date endTime;
    //类型
    @ApiModelProperty(value = "类型")
    String modality;
    //病人年龄
    @ApiModelProperty(value = "病人年龄")
    String patientAge;
    //病人性别
    @ApiModelProperty(value = "病人性别")
    String patientSex;


}
