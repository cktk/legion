package com.esmooc.legion.open.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingVO {

    private Long userId;
    private String nickName;
    private String unitName;
    private String period;
}
