package com.esmooc.legion.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAdminServer
@SpringBootApplication
public class LegionMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegionMonitorApplication.class, args);
    }

}
