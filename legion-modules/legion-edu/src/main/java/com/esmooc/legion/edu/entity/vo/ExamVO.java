package com.esmooc.legion.edu.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.esmooc.legion.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ExamVO
 * @Author Administrator
 * @Date 2021-1-7 9:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamVO {

    private String  id;
    /**
     * 试卷名称
     */
    private String title;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 总题数
     */
    private String questionCount;
    /**
     * 单选题数
     */
    private String radioCount;
    /**
     * 多选题数
     */
    private String checkboxCount;
    /**
     * 判断题数
     */
    private String judgmentCount;
    /**
     * 总分
     */
    private String gradeCount;
    /**
     * 及格分数
     */
    private String gradePass;
    /**
     * 规则
     */
    private String rules;
    /**
     * 规则id
     */
    private String rulesId;
    /**
     * 类别id
     */
    private String majorId;
    /**
     * 类别名称
     */
    private String majorName;
    /**
     * 是否发布  1：发布 0：未发布
     */
    private String issur;

    private String backId;


    @CreatedBy
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Date createTime;

    @ApiModelProperty(value = "更新者")
    @LastModifiedBy
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    String updateBy;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    Date updateTime;

    @ApiModelProperty(value = "删除标志 默认1")
    @TableField(value = "del_flag")
    @TableLogic()
    Integer delFlag =0;

}
