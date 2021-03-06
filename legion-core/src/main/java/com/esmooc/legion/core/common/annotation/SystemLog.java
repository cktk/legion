package com.esmooc.legion.core.common.annotation;

import com.esmooc.legion.core.common.enums.LogType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志自定义注解
 *
 * @author DaiMao
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 日志名称
     *
     * @return
     */
    String description() default "";

    /**
     * 日志类型
     *
     * @return
     */
    LogType type() default LogType.OPERATION;
}
