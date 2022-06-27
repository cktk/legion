/*
 *
 *      Copyright (c) 2018-2025, ccvda All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: ccvda
 *
 */

package com.esmooc.legion.core.admin.mapper;

import com.esmooc.legion.core.admin.api.entity.SysDept;
import com.esmooc.legion.core.common.data.datascope.DataScope;
import com.esmooc.legion.core.common.data.datascope.KiteBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author kite kite
 * @since 2018-01-20
 */
@Mapper
public interface SysDeptMapper extends KiteBaseMapper<SysDept> {

	/**
	 * 根据数据权限查询部门
	 * @param dataScope
	 * @return
	 */
	List<SysDept> listDepts(DataScope dataScope);

}
