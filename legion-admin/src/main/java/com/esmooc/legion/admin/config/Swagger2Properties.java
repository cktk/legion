package com.esmooc.legion.admin.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: swagger 配置信息类
 * @Description: TODO
 * @author kite: jack Mao
 * @Date: 2020/4/26 5:46 下午
 * @Version: 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "kite.swagger")
public class Swagger2Properties {

	/**
	 * 是否开启swagger
	 */
	private Boolean enabled;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 简介
	 */
	private String description;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 许可证
	 **/
	private String license = "";

	/**
	 * 许可证URL
	 **/
	private String licenseUrl = "";

	/**
	 * host信息
	 **/
	private String host = "";

	/**
	 * 服务条款URL
	 */
	private String termsOfServiceUrl;

	/**
	 * swagger会解析的包路径
	 **/
	private String basePackage = "";

	/**
	 * swagger会解析的url规则
	 **/
	private List<String> basePath = new ArrayList<>();

	/**
	 * 在basePath基础上需要排除的url规则
	 **/
	private List<String> excludePath = new ArrayList<>();

	/**
	 * 联系人信息
	 */
	private Contact contact = new Contact();

	/**
	 * 全局统一鉴权配置
	 **/
	private Authorization authorization = new Authorization();

	@Data
	@NoArgsConstructor
	public static class Contact {

		/**
		 * 联系人
		 **/
		private String name = "";

		/**
		 * 联系人url
		 **/
		private String url = "";

		/**
		 * 联系人email
		 **/
		private String email = "";

	}

	@Data
	@NoArgsConstructor
	public static class Authorization {

		/**
		 * 鉴权策略ID，需要和SecurityReferences ID保持一致
		 */
		private String name = "";

		/**
		 * 需要开启鉴权URL的正则
		 */
		private String authRegex = "^.*$";

		/**
		 * 鉴权作用域列表
		 */
		private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();

		private List<String> tokenUrlList = new ArrayList<>();

	}

	@Data
	@NoArgsConstructor
	public static class AuthorizationScope {

		/**
		 * 作用域名称
		 */
		private String scope = "";

		/**
		 * 作用域描述
		 */
		private String description = "";

	}

}
