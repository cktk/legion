package com.esmooc.legion.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zqj
 * @Create: 2021-11-16 13:44
 * @Description: 数据字典的数字转汉字的自定义注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    /**
     * 数据dataSource
     *
     * @return
     */
    String dictDataSource();

    /**
     * 返回put到json中的文本key
     * @return
     */
    String dictText() default "";
}
