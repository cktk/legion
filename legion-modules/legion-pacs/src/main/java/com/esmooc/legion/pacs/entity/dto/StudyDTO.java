package com.esmooc.legion.pacs.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: StudyDTO
 * @Description:
 * @date 2021年12月21日17点48分
 **/
@Data
public class StudyDTO {
    private String id;
    private String sex;
    private String name;
    private String weight;
    @TableField(value = "study_id")
    private String studyID;
    private Date   birthday;
    private String modality;
    @TableField(value = "patient_id")
    private String patientID;
    @TableField(value = "patient_age")
    private String patientAge;
    @TableField(value = "study_date_time")
    private Date studyDateTime;
    @TableField(value = "accession_number")
    private String accessionNumber;
    @TableField(value = "pregnancy_status")
    private String pregnancyStatus;
    @TableField(value = "study_description")
    private String studyDescription;
    @TableField(value = "study_instance_UID")
    private String studyInstanceUID;
}
