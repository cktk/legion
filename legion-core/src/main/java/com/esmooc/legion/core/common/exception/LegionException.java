package com.esmooc.legion.core.common.exception;

import lombok.Data;

/**
 * @author Daimao
 */
@Data
public class LegionException extends RuntimeException {

    private String msg;
    private int code;

    public LegionException(String msg,int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LegionException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = 500;
    }
}
