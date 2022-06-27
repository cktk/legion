/*
 *    Copyright (c) 2018-2025, ccvda All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: ccvda
 */

package com.esmooc.legion.core.common.security.component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author kite kite
 * @date 2018/6/22
 * <p>
 * 1. 支持remoteTokenServices 负载均衡 2. 支持 获取用户全部信息
 */
@Slf4j
public class KiteResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

	@Autowired
	protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

	@Autowired
	private PermitAllUrlProperties permitAllUrlProperties;

	/**
	 * 默认的配置，对外暴露
	 * @param http
	 */
	@Override
	@SneakyThrows
	public void configure(HttpSecurity http) {
		// 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
		http.formLogin().loginPage("/token/login").loginProcessingUrl("/token/form");
		http.headers().frameOptions().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.authorizeRequests();
		permitAllUrlProperties.getIgnoreUrls().forEach(url -> registry.antMatchers(url).permitAll());
		registry.anyRequest().authenticated().and().csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint);
	}

}
