package com.esmooc.legion.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DaiMao
 */
@Data
@AllArgsConstructor
public class TokenMember implements Serializable {

    private String username;

    private Integer platform;
}
