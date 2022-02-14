package com.esmooc.legion.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DaiMao
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrlsProperties {

    private List<String> urls = new ArrayList<String>();
//    {{
//
//        add("/druid/**");
//        add("/doc.html");
//        add("/swagger-resources/**");
//        add("/v2/api-docs");
//        add("/**/*.js");
//        add("/**/*.css");
//        add("/**/*.png");
//        add("/**/*.ico");

//    }}

    private List<String> limitUrls = new ArrayList<String>()
//    {
//        {
//
//            add("/**/*.js");
//            add("/**/*.css");
//            add("/**/*.png");
//            add("/**/*.ico");
//        }
//
//    }
;
}
