package com.esmooc.legion.core.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Daimao
 */
@Data
public class EmailValidate implements Serializable {

    private String username;

    private String email;

    private String validateUrl;

    private String code;

    private String fullUrl;

    private String operation;
}
