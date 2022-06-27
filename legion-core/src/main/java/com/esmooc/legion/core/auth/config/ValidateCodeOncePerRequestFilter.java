package com.esmooc.legion.core.auth.config;

import cn.hutool.core.util.StrUtil;

import com.esmooc.legion.core.common.core.constant.CacheConstants;
import com.esmooc.legion.core.common.core.constant.SecurityConstants;
import com.esmooc.legion.core.common.core.constant.enums.LoginTypeEnum;
import com.esmooc.legion.core.common.core.exception.ValidateCodeException;
import com.esmooc.legion.core.common.core.util.R;
import com.esmooc.legion.core.common.core.util.WebUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@link org.springframework.security.oauth2.provider.endpoint.TokenEndpoint}
 * @author kite lengleng
 * @date 2020/4/3
 * <p>
 * 拦截 TokenEndpoint 进行验证码校验
 */
@Component
@AllArgsConstructor
public class ValidateCodeOncePerRequestFilter extends OncePerRequestFilter {

	private final RedisTemplate redisTemplate;

	private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!SecurityConstants.OAUTH_TOKEN_URL.equals(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}
		// 刷新token，直接向下执行
		String grantType = request.getParameter("grant_type");
		if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
			filterChain.doFilter(request, response);
			return;
		}

		// 终端设置不校验， 直接向下执行
		try {
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);
			String[] clientInfos = WebUtils.getClientIdArray(header);
			if (filterIgnorePropertiesConfig.getClients().contains(clientInfos[0])) {
				filterChain.doFilter(request, response);
				return;
			}

			// 如果是社交登录，判断是否包含SMS
			if (StrUtil.containsAnyIgnoreCase(request.getRequestURI(), SecurityConstants.SOCIAL_TOKEN_URL)) {
				String mobile = request.getParameter("mobile");
				if (StrUtil.containsAny(mobile, LoginTypeEnum.SMS.getType())) {
					throw new ValidateCodeException("验证码不合法");
				}
				else {
					filterChain.doFilter(request, response);
					return;
				}
			}

			// 校验验证码
			checkCode(request);
			filterChain.doFilter(request, response);
		}
		catch (Exception e) {
			response.setStatus(HttpStatus.PRECONDITION_REQUIRED.value());
			WebUtils.renderJson(response, R.failed(e.getMessage()), MediaType.APPLICATION_JSON_VALUE);
		}
	}

	/**
	 * 检查code
	 * @param request
	 */
	@SneakyThrows
	private void checkCode(HttpServletRequest request) {
		String code = request.getParameter("code");

		if (StrUtil.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}

		String randomStr = request.getParameter("randomStr");

		// https://gitee.com/log4j/pig/issues/IWA0D
		String mobile = request.getParameter("mobile");
		if (StrUtil.isNotBlank(mobile)) {
			randomStr = mobile;
		}

		String key = CacheConstants.DEFAULT_CODE_KEY + randomStr;
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		if (!redisTemplate.hasKey(key)) {
			throw new ValidateCodeException("验证码不合法");
		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			throw new ValidateCodeException("验证码不合法");
		}

		String saveCode = codeObj.toString();
		if (StrUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		if (!StrUtil.equals(saveCode, code)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		redisTemplate.delete(key);
	}

}
