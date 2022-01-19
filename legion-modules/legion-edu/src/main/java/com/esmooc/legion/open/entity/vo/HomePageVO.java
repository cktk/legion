package com.esmooc.legion.open.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageVO {

    private String courseTitle;
    private int num;
    private Date updateTime;

}
