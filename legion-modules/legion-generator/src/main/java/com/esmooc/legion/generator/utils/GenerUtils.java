package com.esmooc.legion.generator.utils;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.exception.LegionException;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码生成器 JPA
 * @author Daimao
 */
@Slf4j
public class GenerUtils {

    /**
     * 点转斜线
     * @param str
     * @return
     */
    public static String dotToLine(String str) {
        return str.replace(".", "/");
    }

    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {

        if (StrUtil.isBlank(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                sb.append("_" + Character.toLowerCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0) + sb.toString()).toLowerCase();
    }

    /**
     * 首字母是否大小写
     * @param name
     * @param isFirstUpper
     * @return
     */
    public static String name(String name, boolean isFirstUpper) {

        if (StrUtil.isBlank(name)) {
            throw new LegionException("name不能为空");
        }

        if (name.length() == 1) {
            if (isFirstUpper) {
                return name.toUpperCase();
            } else {
                return name.toLowerCase();
            }
        }

        StringBuilder sb = new StringBuilder();
        if (isFirstUpper) {
            sb.append(Character.toUpperCase(name.charAt(0)));
        } else {
            sb.append(Character.toLowerCase(name.charAt(0)));
        }
        sb.append(name.substring(1));
        return sb.toString();
    }
}
