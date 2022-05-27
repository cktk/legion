package com.esmooc.legion;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.esmooc.legion.core.datasource.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author DaiMao
 */

@EnableDynamicDataSource
@SpringBootApplication(proxyBeanMethods = false)
// 启用JPA审计
@EnableJpaAuditing
// 启用缓存
@EnableCaching
// 启用异步
@EnableAsync
// 启用自带定时任务
@EnableScheduling
//启用Swagger3
@EnableOpenApi
public class LegionApplication {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        SpringApplication.run(LegionApplication.class, args);
        System.err.println("启动时间 "+ timer.intervalPretty());
    }
}
