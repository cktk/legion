package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 呆猫
 *
 * @Date: 2022/01/26/ 18:58
 * @Description:
 */

/**
 * 试卷表
 */
@ApiModel(value = "试卷表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "edu_exam_paper")
public class ExamPaper {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "")
    private String id;

    @TableField(value = "clazz_id")
    @ApiModelProperty(value = "")
    private String clazzId;

    @TableField(value = "user_id")
    @ApiModelProperty(value = "")
    private String userId;

    /**
     * 分数
     */
    @TableField(value = "grade")
    @ApiModelProperty(value = "分数")
    private Double grade;

    /**
     * 是否通过 1：通过 0：不通过
     */
    @TableField(value = "is_pass")
    @ApiModelProperty(value = "是否通过 1：通过 0：不通过")
    private Integer isPass;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    private Date createTime;

    /**
     * 是否删除 1：删除 0：未删除
     */
    @TableField(value = "is_delete")
    @ApiModelProperty(value = "是否删除 1：删除 0：未删除")
    private Integer isDelete;

    /**
     * 考试/练习  练习：0  考试：1 biz_exam的考试：2
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "考试/练习  练习：0  考试：1 biz_exam的考试：2")
    private Integer type;

    @TableField(value = "major_id")
    @ApiModelProperty(value = "")
    private String majorId;

    @TableField(value = "clazz_name")
    @ApiModelProperty(value = "")
    private String clazzName;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "create_by")
    @ApiModelProperty(value = "")
    private String createBy;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "")
    private String updateBy;

    @TableField(value = "del_flag")
    @ApiModelProperty(value = "")
    private String delFlag;

    /**
     * 历史答题
     */
    @TableField(value = "history_question")
    @ApiModelProperty(value = "历史答题")
    private String historyQuestion;

    /**
     * 证书编号
     */
    @TableField(value = "certificate")
    @ApiModelProperty(value = "证书编号")
    private String certificate;


    @TableField(value = "user_type")
    @ApiModelProperty(value = "考试人员类型")
    private String userType;
}
