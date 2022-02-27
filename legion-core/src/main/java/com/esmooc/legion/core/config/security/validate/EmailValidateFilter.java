package com.esmooc.legion.core.config.security.validate;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResponseUtil;
import com.esmooc.legion.core.common.vo.EmailValidate;
import com.esmooc.legion.core.config.properties.CaptchaProperties;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码过滤器
 *
 * @author DaiMao
 */
@Configuration
public class EmailValidateFilter extends OncePerRequestFilter {

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @Autowired
    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 判断URL是否需要验证
        Boolean flag = false;
        String requestUrl = request.getRequestURI();
        for (String url : captchaProperties.getEmail()) {
            if (pathMatcher.match(url, requestUrl)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String email = StrUtil.removeAllLineBreaks(request.getParameter("email"));
            String code = StrUtil.removeAllLineBreaks(request.getParameter("code"));
            if (StrUtil.isBlank(email) || StrUtil.isBlank(code)) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "请传入邮件验证码所需参数email或code"));
                return;
            }
            String v = redisTemplate.get(CommonConstant.PRE_EMAIL + email);
            if (StrUtil.isBlank(v)) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "验证码已过期，请重新获取"));
                return;
            }
            EmailValidate e = new Gson().fromJson(v, EmailValidate.class);
            if (!code.equals(e.getCode())) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "邮件验证码输入错误"));
                return;
            }
            // 已验证清除key
            redisTemplate.delete(CommonConstant.PRE_EMAIL + email);
            // 验证成功 放行
            chain.doFilter(request, response);
            return;
        }
        // 无需验证 放行
        chain.doFilter(request, response);
    }
}
