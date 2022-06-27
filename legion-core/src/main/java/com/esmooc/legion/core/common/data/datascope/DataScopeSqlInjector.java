package com.esmooc.legion.core.common.data.datascope;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.esmooc.legion.core.common.data.datascope.impl.SelectCountByScope;
import com.esmooc.legion.core.common.data.datascope.impl.SelectListByScope;
import com.esmooc.legion.core.common.data.datascope.impl.SelectPageByScope;
import java.util.List;

/**
 * @title: 支持自定义数据权限方法注入
 * @Author: jack Mao
 * @Date: 2021/2/4 9:40 上午
 * @Version: 1.0
 */
public class DataScopeSqlInjector extends DefaultSqlInjector {

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
		List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
		methodList.add(new SelectListByScope());
		methodList.add(new SelectPageByScope());
		methodList.add(new SelectCountByScope());
		methodList.add(new InsertBatchSomeColumn());
		return methodList;
	}

}
