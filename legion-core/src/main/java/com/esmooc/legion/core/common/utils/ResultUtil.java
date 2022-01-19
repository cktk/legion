package com.esmooc.legion.core.common.utils;


import com.esmooc.legion.core.common.vo.Result;

/**
 * @author Daimao
 */
public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
    }

    public static <T> Result<T> data(T t) {
        return new ResultUtil<T>().setData(t);
    }

    public static <T> Result<T> data(T t, String msg) {
        return new ResultUtil<T>().setData(t, msg);
    }


    public static <T> Result<T> success() {
        return new ResultUtil<T>().setSuccessMsg();
    }

    public static <T> Result<T> success(String msg) {
        return new ResultUtil<T>().setSuccessMsg(msg);
    }

    public static <T> Result<T> success(Integer code,String msg) {
        return new ResultUtil<T>().setSuccessMsg(code,msg);
    }

    public static <T> Result<T> success(String msg,T data) {
        return new ResultUtil<T>().setSuccessMsg(msg,data);
    }

    public static <T> Result<T> success(Integer code,String msg,T data) {
        return new ResultUtil<T>().setSuccessMsg(code,msg,data);
    }


    public static <T> Result<T> error(String msg) {
        return new ResultUtil<T>().setErrorMsg(msg);
    }


    public static <T> Result<T> error(Integer code, String msg) {
        return new ResultUtil<T>().setErrorMsg(code, msg);
    }

    public Result<T> setData(T t) {
        this.result.setResult(t);
        this.result.setCode(200);
        return this.result;
    }

    public Result<T> setSuccessMsg() {
        this.result.setSuccess(true);
        this.result.setMessage("成功");
        this.result.setCode(200);
        this.result.setResult(null);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setResult(null);
        return this.result;
    }

    public Result<T> setSuccessMsg(Integer code,String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(code);
        this.result.setResult(null);
        return this.result;
    }


    public Result<T> setSuccessMsg(String msg,T t) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setResult(t);
        return this.result;
    }


    public Result<T> setSuccessMsg(Integer code,String msg,T t) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(code);
        this.result.setResult(t);
        return this.result;
    }

    public Result<T> setData(T t, String msg) {
        this.result.setResult(t);
        this.result.setCode(200);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(500);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }
}
