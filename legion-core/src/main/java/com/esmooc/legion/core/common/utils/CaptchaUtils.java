package com.esmooc.legion.core.common.utils;

import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月16日 08:59
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
public class CaptchaUtils {


    public static void inti(HttpServletRequest request, RedisTemplateHelper redisTemplate, String code,String captchaId ) {

        try {
            redisTemplate.set(captchaId, code, 2L, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("初始化 -- 通过redis获取验证码失败从session获取");
        }
        HttpSession session = request.getSession();
        session.setAttribute(SystemConstant.KAPTCHA_SESSION_KEY+captchaId, code);
    }

    public static String getCode(HttpServletRequest request, RedisTemplateHelper redisTemplate, String captchaId,Boolean isDel) {
        String code = null;
        try {
            code = redisTemplate.get(captchaId);
            if (isDel){
                redisTemplate.delete(captchaId);
            }
           return code;
        } catch (Exception e) {
            System.out.println("通过redis获取验证码失败从session获取");
        }
        Object obj = request.getSession().getAttribute(SystemConstant.KAPTCHA_SESSION_KEY+captchaId);
        code = String.valueOf(obj != null ? obj : "");
        if (isDel){
            request.getSession().removeAttribute(SystemConstant.KAPTCHA_SESSION_KEY+captchaId );
        }
        return code;
    }



}
