package com.esmooc.legion.config.swagger;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Wilson
 */
@Slf4j
@Component
@AllArgsConstructor
public class DatabaseInitializer {
    private final FlywayProperties flywayProperties;
//    private final DruidDataSource dataSourceProperties;
    private static final String IGNORE_EXCEPTION = "already exists";
    private static final String SCHEMA_PATTERN = "{schema}";

    @Qualifier("master")
    private DruidDataSource dataSourceProperties;
    @PostConstruct
    public void init() throws SQLException {
        if (flywayProperties.getInitSqls().isEmpty()) {
            return;
        }

        log.info("DatabaseInitializer uses flyway init-sqls to initiate database");
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        log.info("dataSourceProperties" ,dataSourceProperties.getDriverClassName());
        String url = dataSourceProperties.getUrl();
        dataSourceProperties.getConnection();
        // jdbc url最后一个 '/' 用于分割具体 schema?参数
        int lastSplitIndex = url.lastIndexOf('/') + 1;
        // 获取数据库名
        String schema = url.contains("?") ? url.substring(lastSplitIndex, url.indexOf("?")) : url.substring(lastSplitIndex);
        // 获取spring.datasource.url具体数据库schema前的jdbc url
        String addressUrl = url.substring(0, lastSplitIndex);
        // 直连数据库地址:jdbc:mysql://yourIp:port
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(addressUrl);
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        try {
            for (String sql : flywayProperties.getInitSqls()) {
                statement.executeUpdate(sql.contains(SCHEMA_PATTERN) ? sql.replace(SCHEMA_PATTERN, schema) : sql);
            }
        } catch (Exception e) {
            // 异常信息包含 'already exists' 则忽略，否则抛出
            if (!e.getLocalizedMessage().contains(IGNORE_EXCEPTION)) {
                throw e;
            }
        } finally {
            statement.close();
            connection.close();
            dataSource.close();
        }
        log.info("DatabaseInitializer initialize completed");
    }
}
