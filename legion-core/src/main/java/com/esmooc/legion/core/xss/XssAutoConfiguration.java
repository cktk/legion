/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.esmooc.legion.core.xss;

import com.esmooc.legion.core.xss.config.XssProperties;
import com.esmooc.legion.core.xss.core.DefaultXssCleaner;
import com.esmooc.legion.core.xss.core.JacksonXssClean;
import com.esmooc.legion.core.xss.core.XssCleanInterceptor;
import com.esmooc.legion.core.xss.core.XssCleaner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * jackson xss 配置
 *
 * @author L.cm
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(prefix = XssProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class XssAutoConfiguration implements WebMvcConfigurer {

	private final XssProperties xssProperties;

	@Bean
	@ConditionalOnMissingBean
	public XssCleaner xssCleaner(XssProperties properties) {
		return new DefaultXssCleaner(properties);
	}

//	@Bean
//	public FormXssClean formXssClean(XssProperties properties, XssCleaner xssCleaner) {
//		return new FormXssClean(properties, xssCleaner);
//	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(XssProperties properties,
			XssCleaner xssCleaner) {
		JacksonXssClean xssClean = new JacksonXssClean(properties, xssCleaner);
		return builder -> builder.deserializerByType(String.class, xssClean);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = xssProperties.getPathPatterns();
		if (patterns.isEmpty()) {
			patterns.add("/**");
		}
		XssCleanInterceptor interceptor = new XssCleanInterceptor(xssProperties);
		registry.addInterceptor(interceptor).addPathPatterns(patterns)
				.excludePathPatterns(xssProperties.getPathExcludePatterns()).order(Ordered.LOWEST_PRECEDENCE);
	}

}
