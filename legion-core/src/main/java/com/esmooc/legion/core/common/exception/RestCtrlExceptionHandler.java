package com.esmooc.legion.core.common.exception;

import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author DaiMao
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {


    @ExceptionHandler(value = LegionException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleLegionException(LegionException e) {

        String errorMsg = "Legion exception";
        if (e != null) {
            errorMsg = e.getMsg();
            log.error(errorMsg);
        }
        return ResultUtil.error(500, errorMsg);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleBindException(BindException e) {

        StringBuilder sb = new StringBuilder();
        e.getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            sb.append(fieldName + "-" + message + "；");
        });
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return ResultUtil.error(500, result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            sb.append(fieldName + "-" + message + "；");
        });
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return ResultUtil.error(500, result);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleConstraintViolationException(ConstraintViolationException e) {

        StringBuilder sb = new StringBuilder();
        e.getConstraintViolations().forEach(error -> {
            String fieldName = error.getPropertyPath().toString();
            String message = error.getMessageTemplate();
            sb.append(fieldName + "-" + message + "；");
        });
        String result = sb.toString();
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return ResultUtil.error(500, result);
    }

    /**
     * 处理限制异常
     * 限流异常
     *
     * @param e e
     * @return {@link Result}<{@link Object}>
     */
    @ExceptionHandler(LimitException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleLimitException(LimitException e) {

        String errorMsg = "Limit exception";
        if (e != null) {
            errorMsg = e.getMsg();
            log.error(errorMsg);
        }
        return ResultUtil.error(500, errorMsg);
    }

    @ExceptionHandler(CaptchaException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleCaptchaException(CaptchaException e) {

        String errorMsg = "CaptchaException exception";
        if (e != null) {
            errorMsg = e.getMsg();
            log.error(errorMsg);
        }

        return ResultUtil.error(500, errorMsg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleCaptchaException(HttpMessageNotReadableException e) {

        String errorMsg = "请求参数不正确   ";
        if (e != null) {
            errorMsg = errorMsg + e.getMessage();
            log.error(errorMsg);
        }

        return ResultUtil.error(415, errorMsg);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleAccessDeniedException(AccessDeniedException e) {

        String errorMsg = "AccessDeniedException exception";
        if (e != null) {
            errorMsg = e.getMessage();
            log.error(errorMsg);
        }
        return ResultUtil.error(500, errorMsg);
    }


}
