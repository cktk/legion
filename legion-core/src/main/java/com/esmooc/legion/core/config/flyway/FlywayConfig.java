package com.esmooc.legion.core.config.flyway;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;



@Configuration
@EnableTransactionManagement
public class FlywayConfig {
    @Autowired
    private DataSource dataSource;

    private static final String SQL_LOCATION = "classpath:db/";

    @Bean
    public void migrate() {
        System.out.println("调用数据库生成工具");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        Map<String, DataSource> dataSources = ds.getDataSources();
        dataSources.forEach((k, v) -> {
            System.out.println("正在执行多数据源生成数据库文件 " + k);
            Flyway flyway = Flyway.configure()
                    .dataSource(v)
                    .locations(SQL_LOCATION+k)
                    .baselineOnMigrate(true)
                    .load();
            flyway.migrate();
        });
    }

}
