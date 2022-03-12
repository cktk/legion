package com.esmooc.legion.config.swagger;

import com.esmooc.legion.core.config.properties.IgnoredUrlsProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author DaiMao
 */
@Slf4j
@Configuration
public class Swagger2Config {

    @Value("${swagger.title:Legion}")
    private String title;

    @Value("${swagger.description:Api Documentation}")
    private String description;

    @Value("${swagger.version:1}")
    private String version;

    @Value("${swagger.termsOfServiceUrl:http://www.esmooc.com}")
    private String termsOfServiceUrl;


    @Value("${swagger.contact.name:DaiMao}")
    private String name;

    @Value("${swagger.contact.url:http://esmooc.com}")
    private String url;

    @Value("${swagger.contact.email:911362130@qq.com}")
    private String email;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    public List<SecurityContext> securityContexts() {

        Predicate<String> paths = ant("");
        for (String url : ignoredUrlsProperties.getUrls()) {
            paths = paths.or(ant(url));
        }

        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(
                                new SecurityReference("Authorization",
                                        new AuthorizationScope[]{
                                                new AuthorizationScope("global", "")})))
                        .forPaths(paths.negate())
                        .build());
    }

    @Bean
    public Docket createRestApi() {

        List<SecurityScheme> securitySchemes = Collections.singletonList(
                new ApiKey("Authorization", "accessToken", "header"));

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("1. 脚手架默认接口")
                .apiInfo(apiInfo()).select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(regex(".*/app/.*").negate())
                .build()
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts());
    }


    @Bean
    public Docket createAppRestApi() {

        List<SecurityScheme> securitySchemes = Collections.singletonList(
                new ApiKey("Authorization", "appToken", "header"));

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("2 移动端接口")
                .apiInfo(apiInfo()).select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(regex(".*/app/.*"))
                .build()
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts());
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(name, url, email))
                .version(version)
                .build();
    }
}
