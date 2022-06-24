package com.esmooc.legion.core.config.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about : 数据权限查询参数
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {

	/**
	 * 限制范围的字段名称
	 */
	private String scopeName = "departmentId";

	/**
	 * 具体的数据范围
	 */
	private List<Long> deptIds = new ArrayList<>();

	/**
	 * 是否只查询本部门
	 */
	private Boolean isOnly = false;

	/**
	 * 函数名称，默认 SELECT * ;
	 *
	 * <ul>
	 * <li>COUNT(1)</li>
	 * </ul>
	 */
	private DataScopeFuncEnum func = DataScopeFuncEnum.ALL;

	/**
	 * of 获取实例
	 */
	public static DataScope of() {
		return new DataScope();
	}

	public DataScope scopeName(String scopeName) {
		this.scopeName = scopeName;
		return this;
	}

	public DataScope deptIds(List<Long> deptIds) {
		this.deptIds = deptIds;
		return this;
	}

	public DataScope only(boolean isOnly) {
		this.isOnly = isOnly;
		return this;
	}

	public DataScope func(DataScopeFuncEnum func) {
		this.func = func;
		return this;
	}

}
