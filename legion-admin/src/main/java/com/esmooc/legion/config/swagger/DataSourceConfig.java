//package com.esmooc.legion.config.swagger;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//	@Bean(name = "master")
//	@ConfigurationProperties(prefix = "spring.datasource.datasource.master")
//	public DataSource MasterDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//}
