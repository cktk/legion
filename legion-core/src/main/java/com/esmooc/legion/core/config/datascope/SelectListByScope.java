package com.esmooc.legion.core.config.datascope;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
public class SelectListByScope extends AbstractMethod {

	public SelectListByScope() {
		super("selectListByScope");
	}

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlMethod sqlMethod = SqlMethod.SELECT_LIST;
		String sql = String.format(sqlMethod.getSql(), this.sqlFirst(), this.sqlSelectColumns(tableInfo, true),
				tableInfo.getTableName(), this.sqlWhereEntityWrapper(true, tableInfo), this.sqlOrderBy(tableInfo),
				this.sqlComment());
		SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
		return this.addSelectMappedStatementForTable(mapperClass, sqlSource, tableInfo);
	}

}
