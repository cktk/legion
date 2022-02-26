package com.esmooc.legion.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author DaiMao
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "legion.app-token")
public class LegionAppTokenProperties {
    /**
     * 使用redis存储token
     */
    private Boolean redis = true;
    /**
     * 单平台登陆
     */
    private Boolean spl = true;

    /**
     * token过期时间（天）
     */
    private Integer tokenExpireTime = 30;
}
