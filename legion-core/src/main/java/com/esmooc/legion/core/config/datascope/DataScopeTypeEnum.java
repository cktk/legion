package com.esmooc.legion.core.config.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about :数据权限类型
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {

	/**
	 * 查询全部数据
	 */
	ALL(0, "全部"),

	/**
	 * 自定义
	 */
	CUSTOM(1, "自定义"),

	/**
	 * 本级及子级
	 */
	OWN_CHILD_LEVEL(2, "本级及子级"),

	/**
	 * 本级
	 */
	OWN_LEVEL(3, "本级");


	/**
	 * 类型
	 */
	private final int type;

	/**
	 * 描述
	 */
	private final String description;

}
