package com.esmooc.legion.core.config.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about : 数据权限函数类型
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Getter
@AllArgsConstructor
public enum DataScopeFuncEnum {

	/**
	 * 查询全部数据 SELECT * FROM (originSql) temp_data_scope WHERE temp_data_scope.dept_id IN
	 * (1)
	 */
	ALL("*", "全部"),

	/**
	 * 查询函数COUNT SELECT COUNT(1) FROM (originSql) temp_data_scope WHERE
	 * temp_data_scope.dept_id IN (1)
	 */
	COUNT("COUNT(1)", "自定义");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
