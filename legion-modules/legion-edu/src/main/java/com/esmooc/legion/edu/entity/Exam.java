package com.esmooc.legion.edu.entity;


import com.esmooc.legion.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 考试
 *
 * @author Daimao
 * @date 2022年01月19日 15点01分47秒
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseEntity {
    private String title;
}
