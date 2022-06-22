package com.esmooc.legion.core.config.security.validate;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.CaptchaUtils;
import com.esmooc.legion.core.common.utils.ResponseUtil;
import com.esmooc.legion.core.config.properties.CaptchaProperties;
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
@Slf4j
@Configuration
public class ImageValidateFilter extends OncePerRequestFilter {

    @Autowired
    private CaptchaProperties captchaProperties;

@Autowired(required=false)
    private RedisTemplateHelper redisTemplate;

    @Autowired
    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 判断URL是否需要验证
        Boolean flag = false;
        String requestUrl = request.getRequestURI();
        for (String url : captchaProperties.getImage()) {
            if (pathMatcher.match(url, requestUrl)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String captchaId = StrUtil.removeAllLineBreaks(request.getParameter("captchaId"));
            String code = StrUtil.removeAllLineBreaks(request.getParameter("code"));
            if (StrUtil.isBlank(captchaId) || StrUtil.isBlank(code)) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "请传入图形验证码所需参数captchaId或code"));
                return;
            }
            String redisCode = CaptchaUtils.getCode(request,redisTemplate,captchaId,true);
            if (StrUtil.isBlank(redisCode)) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "验证码已过期，请重新获取"));
                return;
            }

            if (!redisCode.toLowerCase().equals(code.toLowerCase())) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "图形验证码输入错误"));
                return;
            }

            // 验证成功 放行
            chain.doFilter(request, response);
            return;
        }
        // 无需验证 放行
        chain.doFilter(request, response);
    }
}
