package com.esmooc.legion.edu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayVO {

    private String id;
    private String fileName;
    private String playType;
    private String playName;

}
