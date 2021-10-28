package com.esmooc.legion.config.swagger;

import com.esmooc.legion.core.config.properties.IgnoredUrlsProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Daimao
 */
@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2Config {



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
                .groupName("1.基础接口")
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .paths(regex(".*/legion/.*"))
                .build()
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts());
    }





    @Bean
    public Docket createAppRestApi() {

        List<SecurityScheme> securitySchemes = Collections.singletonList(
                new ApiKey("Authorization", "appToken", "header"));

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("2业务接口")
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(regex(".*/legion/.*").negate())
                .build()
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Legion API接口文档")
                .description("Legion Api Documentation")
                .termsOfServiceUrl("http://legion.esmooc.com")
                .contact(new Contact("Daimao", "http://legion.esmooc.com", "cktk@qq.com"))
                .version("1.0")
                .build();
    }
}
