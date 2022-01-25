package com.esmooc.legion.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.esmooc.legion.core.base.BaseEntity;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
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
 * @ClassName MyExam
 * @Author Administrator
 * @Date 2021-1-12 13:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExam {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
    private String id ;

    @CreatedBy
    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private  Date createTime;

    @ApiModelProperty(value = "更新者")
    @LastModifiedBy
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "删除标志 默认1")
    @TableField(value = "del_flag")
    @TableLogic()
    private Integer delFlag ;

    /**
     * 名称
     */
    private String clazzName;
    /**
     * 课程id
     */
    private String clazzId;
    /**
     * 类别id
     */
    private String majorId;
    /**
     * 类别名称
     */
    private String majorName;
    /**
     * 学时
     */
    private String period;
    /**
     * 登录人id
     */
    private String userId;
    /**
     * 是否通过
     */
    private Integer isPass;
    /**
     * 分数
     */
    private String grade;
    /**
     * 类型
     */
    private String type;
    /**
     * 历史
     */
    private String historyQuestion;

}
