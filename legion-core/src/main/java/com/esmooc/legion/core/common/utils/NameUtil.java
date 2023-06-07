package com.esmooc.legion.core.common.utils;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户名验证工具类
 *
 * @author DaiMao
 */
@Slf4j
public class NameUtil {

    private static UserService userService = SpringUtil.getBean(UserService.class);

    public static boolean username(String v) {

        return Validator.isGeneral(v);
    }

    public static boolean mobile(String v) {
        return Validator.isMobile(v);
    }

    public static boolean email(String v) {
        return Validator.isEmail(v);
    }

    /**
     * 校验
     *
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
