package com.esmooc.legion.open.entity;

import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程审核记录对象 edu_prompt
 *
 * @author ruoyi
 * @date 2021-02-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prompt extends BaseEntity {
    private String courseId;

    private String prompt;

    private String courseTitle;

    private String userId;
}
