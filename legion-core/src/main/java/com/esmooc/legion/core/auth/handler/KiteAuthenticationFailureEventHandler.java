
package com.esmooc.legion.core.auth.handler;

import com.esmooc.legion.core.admin.api.entity.SysLog;


import com.esmooc.legion.core.common.core.constant.CommonConstants;
import com.esmooc.legion.core.admin.service.SysLogService;
import com.esmooc.legion.core.common.core.util.WebUtils;
import com.esmooc.legion.core.common.log.util.SysLogUtils;
import com.esmooc.legion.core.common.security.handler.AuthenticationFailureHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kite kite
 * @date 2018/10/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class KiteAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

	private final SysLogService logService;

	/**
	 * 异步处理，登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 * @param request 请求
	 * @param response 响应
	 */
	@Async
	@Override
	@SneakyThrows
	public void handle(AuthenticationException authenticationException, Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		SysLog sysLog = SysLogUtils.getSysLog(request, username);
		sysLog.setTitle(username + "用户登录");
		sysLog.setType(CommonConstants.STATUS_LOCK);
		sysLog.setParams(username);
		sysLog.setException(authenticationException.getLocalizedMessage());
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));

		logService.save(sysLog);

		log.info("用户：{} 登录失败，异常：{}", username, authenticationException.getLocalizedMessage());
	}

}
