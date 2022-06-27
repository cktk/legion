package com.esmooc.legion.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @title: Swagger 配置
 * @Description: TODO
 * @author kite: jack Mao
 * @Date: 2020/4/26 7:46 下午
 * @Version: 1.0
 */
@Slf4j
@EnableKnife4j
@EnableSwagger2WebMvc
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(Swagger2Properties.class)
@ConditionalOnProperty(name = "kite.swagger.enabled")
public class Swagger2Config {

	/**
	 * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
	 */
	private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

	private static final String BASE_PATH = "/**";

	@Bean
	@SneakyThrows
	public Docket customDocket(Swagger2Properties swagger2Properties) {
		// base-path处理
		if (swagger2Properties.getBasePath().isEmpty()) {
			swagger2Properties.getBasePath().add(BASE_PATH);
		}
		List<Predicate<String>> basePath = new ArrayList();
		swagger2Properties.getBasePath().forEach(path -> basePath.add(PathSelectors.ant(path)));

		// exclude-path处理
		if (swagger2Properties.getExcludePath().isEmpty()) {
			swagger2Properties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
		}
		List<Predicate<String>> excludePath = new ArrayList<>();
		swagger2Properties.getExcludePath().forEach(path -> excludePath.add(PathSelectors.ant(path)));

		ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2).host(swagger2Properties.getHost())
				.apiInfo(apiInfo(swagger2Properties)).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class));

		swagger2Properties.getBasePath().forEach(p -> builder.paths(PathSelectors.ant(p)));
		swagger2Properties.getExcludePath().forEach(p -> builder.paths(PathSelectors.ant(p).negate()));

		return builder.build().securitySchemes(Collections.singletonList(securitySchema(swagger2Properties)))
				.securityContexts(Collections.singletonList(securityContext(swagger2Properties))).pathMapping("/");

	}

	/**
	 * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
	 * @return
	 */
	private SecurityContext securityContext(Swagger2Properties swaggerProperties) {
		return SecurityContext.builder().securityReferences(defaultAuth(swaggerProperties))
				.forPaths(PathSelectors.regex(swaggerProperties.getAuthorization().getAuthRegex())).build();

	}

	/**
	 * 默认的全局鉴权策略
	 * @return
	 */
	private List<SecurityReference> defaultAuth(Swagger2Properties swaggerProperties) {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties.getAuthorization().getAuthorizationScopeList()
				.forEach(authorizationScope -> authorizationScopeList.add(
						new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
		return Collections
				.singletonList(SecurityReference.builder().reference(swaggerProperties.getAuthorization().getName())
						.scopes(authorizationScopeList.toArray(authorizationScopes)).build());
	}

	private OAuth securitySchema(Swagger2Properties swaggerProperties) {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties.getAuthorization().getAuthorizationScopeList()
				.forEach(authorizationScope -> authorizationScopeList.add(
						new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
		ArrayList<GrantType> grantTypes = new ArrayList<>();
		swaggerProperties.getAuthorization().getTokenUrlList()
				.forEach(tokenUrl -> grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl)));
		new OAuth(swaggerProperties.getAuthorization().getName(), authorizationScopeList, grantTypes);
		return new OAuthBuilder().name(swaggerProperties.getAuthorization().getName()).scopes(authorizationScopeList)
				.grantTypes(grantTypes).build();
	}

	private ApiInfo apiInfo(Swagger2Properties swaggerProperties) {
		return new ApiInfoBuilder().title(swaggerProperties.getTitle()).description(swaggerProperties.getDescription())
				.license(swaggerProperties.getLicense()).licenseUrl(swaggerProperties.getLicenseUrl())
				.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
				.contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(),
						swaggerProperties.getContact().getEmail()))
				.version(swaggerProperties.getVersion()).build();
	}

}
