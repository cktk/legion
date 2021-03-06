package com.esmooc.legion.core.common.exception;

import lombok.Data;

/**
 * @author DaiMao
 */
@Data
public class LimitException extends RuntimeException {

    private String msg;

    public LimitException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
