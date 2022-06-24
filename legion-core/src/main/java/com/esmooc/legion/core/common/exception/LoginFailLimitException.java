package com.esmooc.legion.core.common.exception;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author DaiMao
 */
public class LoginFailLimitException extends InternalAuthenticationServiceException {

    private String msg;


    public LoginFailLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailLimitException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public LoginFailLimitException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public LoginFailLimitException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public LoginFailLimitException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
