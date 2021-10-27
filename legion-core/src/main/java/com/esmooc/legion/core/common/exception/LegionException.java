package com.esmooc.legion.core.common.exception;

import lombok.Data;

/**
 * @author Daimao
 */
@Data
public class LegionException extends RuntimeException {

    private String msg;

    public LegionException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
