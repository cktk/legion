package com.esmooc.legion.core.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019-05-14
 * <p>S
 * 参考DruidDataSourceWrapper
 */
@Data
@Primary
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {

	/**
	 * 数据源用户名
	 */
	private String username;

	/**
	 * 数据源密码
	 */
	private String password;

	/**
	 * jdbc url
	 */
	private String url;

	/**
	 * 数据源驱动
	 */
	private String driverClassName;

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from t_datasource_conf where del_flag = '0'";

}
