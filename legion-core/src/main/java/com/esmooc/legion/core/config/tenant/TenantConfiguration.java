package com.esmooc.legion.core.config.tenant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:11
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 *  租户信息拦截
 */
@Configuration
public class TenantConfiguration {


    @Bean
    public ClientHttpRequestInterceptor TenantRequestInterceptor() {
        return new TenantRequestInterceptor();
    }


}
