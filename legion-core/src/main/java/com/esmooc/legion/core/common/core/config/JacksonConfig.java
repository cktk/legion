package com.esmooc.legion.core.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.esmooc.legion.core.common.core.jackson.KiteJavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JacksonConfig 配置时间转换规则 {@link KiteJavaTimeModule}、默认时区等
 *
 * @author kite L.cm
 * @author kite kite
 * @author kite lishangbu
 * @date 2020-06-15
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JacksonConfig implements WebMvcConfigurer {

	private static final String ASIA_SHANGHAI = "Asia/Shanghai";

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.locale(Locale.CHINA);
			builder.timeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.modules(new KiteJavaTimeModule());
		};
	}

	/**
	 * 增加GET请求参数中时间类型转换
	 * {@link com.esmooc.legion.common.core.jackson.KiteJavaTimeModule}
	 * <ul>
	 * <li>HH:mm:ss -> LocalTime</li>
	 * <li>yyyy-MM-dd -> LocalDate</li>
	 * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
	 * </ul>
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
		registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
		registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
		registrar.registerFormatters(registry);
	}

}
