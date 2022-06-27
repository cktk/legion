package com.esmooc.legion.admin.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @title: 打印类
 * @Description: TODO
 * @author kite: jack Mao
 * @Date: 2020/4/26 10:00 下午
 * @Version: 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WhaleStartedUpRunner implements ApplicationRunner {

	private final ConfigurableApplicationContext context;

	@Value("${whale.swagger2.enabled:true}")
	private boolean swaggerEnable;

	@Value("${server.port:8080}")
	private String port;

	@Value("${server.servlet.context-path:/}")
	private String contextPath;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (context.isActive()) {
			InetAddress address = InetAddress.getLocalHost();
			String swaggerUrl = "";
			if (swaggerEnable) {
				swaggerUrl = String.format("http://%s:%s/api/doc.html#/plus", address.getHostAddress(), port);
				log.info("自动配置 swagger2 api 已开启：{}", swaggerUrl);
			}
		}
	}

}
