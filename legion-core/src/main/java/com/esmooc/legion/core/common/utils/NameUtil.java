package com.esmooc.legion.core.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户名验证工具类
 * @author DaiMao
 */
@Slf4j
public class NameUtil {

    private static UserService userService = SpringUtil.getBean(UserService.class);

    public static final String regUsername = "^[a-zA-Z0-9_]{1,16}$";

    public static final String regMobile = "^[1][3,4,5,6,7,8,9][0-9]{9}$";

    public static final String regEmail = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    /**
     * 由字母、数字、下划线、中文组成，不能超过16位
     */
    private static final Pattern pUsername = Pattern.compile(regUsername);

    /**
     * 普通用户11位手机号
     * 10、11、12开头的号码已分配给特定机构
     */
    private static final Pattern pMobile = Pattern.compile(regMobile);

    /**
     * 邮箱
     * http://emailregex.com/
     */
    private static final Pattern pEmail = Pattern.compile(regEmail);

    public static boolean username(String v) {

        if (StrUtil.isBlank(v)) {
            return false;
        }
        Matcher m = pUsername.matcher(v);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public static boolean mobile(String v) {

        if (StrUtil.isBlank(v)) {
            return false;
        }
        Matcher m = pMobile.matcher(v);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public static boolean email(String v) {

        if (StrUtil.isBlank(v)) {
            return false;
        }
        Matcher m = pEmail.matcher(v);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 校验
     * @param username 用户名 不校验传空字符或null 下同
     * @param mobile   手机号
     * @param email    邮箱
     */
    public static void checkUserInfo(String username, String mobile, String email) {

        // 由于支持手机密码登录 登录账号不能为他人手机号
        if (NameUtil.mobile(username) && !username.equals(mobile)) {
            throw new LegionException("登录账号不能为他人手机号");
}

        if (StrUtil.isNotBlank(username) && userService.findByUsername(username) != null) {
            throw new LegionException("该登录账号已被注册");
        }
        if (StrUtil.isNotBlank(email) && userService.findByEmail(email) != null) {
            throw new LegionException("该邮箱已被注册");
        }
        if (StrUtil.isNotBlank(mobile) && userService.findByMobile(mobile) != null) {
            throw new LegionException("该手机号已被注册");
        }
    }
}