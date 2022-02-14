package com.esmooc.legion.activiti.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DaiMao
 */
@Data
@ConfigurationProperties(prefix = "spring.activiti.legion")
public class ActivitiExtendProperties {

    /**
     * 流程图字体配置
     */
    private String activityFontName = "宋体";

    /**
     * 流程图字体配置
     */
    private String labelFontName = "宋体";
}
