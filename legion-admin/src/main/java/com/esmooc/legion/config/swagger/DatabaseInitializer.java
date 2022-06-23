package com.esmooc.legion.config.swagger;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.ds.ItemDataSource;
import com.esmooc.legion.core.config.flyway.MyFlywayProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * @author Wilson
 */
@Slf4j
@Component
@AllArgsConstructor
public class DatabaseInitializer {
    @Autowired
    private MyFlywayProperties flywayProperties;
    private static final String IGNORE_EXCEPTION = "already exists";
    private static final String SCHEMA_PATTERN = "{schema}";
    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        Map<String, DataSource> dataSources = ds.getDataSources();
        dataSources.forEach((k, v) -> {
            try {
                init((ItemDataSource) v);
            } catch (SQLException e) {
                System.out.println("数据库初始化失败");
            }
        });
    }

    public void init(ItemDataSource ds) throws SQLException {
        DruidDataSource realDataSource = (DruidDataSource) ds.getRealDataSource();
        String url = realDataSource.getRawJdbcUrl();
        String username = realDataSource.getUsername();
        String password = realDataSource.getPassword();
        String driverClassName = realDataSource.getDriverClassName();

        if (flywayProperties.getInitSqls().isEmpty()) {
            return;
        }
        int lastSplitIndex = url.lastIndexOf('/') + 1;
        String schema = url.contains("?") ? url.substring(lastSplitIndex, url.indexOf("?")) : url.substring(lastSplitIndex);
        String addressUrl = url.substring(0, lastSplitIndex);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(addressUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
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
        log.info(schema, "数据库{}初始化完成 ");
    }
}

