package com.esmooc.legion.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daimao
 */
@Data
@AllArgsConstructor
public class TokenUser implements Serializable {

    private String username;

    private List<String> permissions;

    private Boolean saveLogin;
}
