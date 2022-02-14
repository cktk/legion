package com.esmooc.legion.core.common.exception;

import lombok.Data;

/**
 * @author DaiMao
 */
@Data
public class CaptchaException extends RuntimeException {

    private String msg;

    public CaptchaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
