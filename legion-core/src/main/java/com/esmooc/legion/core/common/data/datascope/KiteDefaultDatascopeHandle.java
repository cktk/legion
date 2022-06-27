/*
 *    Copyright (c) 2018-2025, ccvda All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: ccvda
 */

package com.esmooc.legion.core.common.data.datascope;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.esmooc.legion.core.admin.api.entity.SysDeptRelation;
import com.esmooc.legion.core.admin.api.entity.SysRole;
import com.esmooc.legion.core.admin.service.SysDeptRelationService;
import com.esmooc.legion.core.admin.service.SysRoleService;
import com.esmooc.legion.core.common.core.constant.SecurityConstants;
import com.esmooc.legion.core.common.core.util.SpringContextHolder;
import com.esmooc.legion.core.common.security.service.KiteUser;
import com.esmooc.legion.core.common.security.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kite kite
 * @date 2019-09-07
 * <p>
 * 默认data scope 判断处理器
 */
public class KiteDefaultDatascopeHandle implements DataScopeHandle {

	/**
	 * 计算用户数据权限
	 * @param deptList
	 * @return
	 */
	@Override
	public Boolean calcScope(List<Integer> deptList) {
		SysRoleService sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
		SysDeptRelationService relationService = SpringContextHolder.getBean(SysDeptRelationService.class);
		KiteUser user = SecurityUtils.getUser();
		List<String> roleIdList = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.filter(authority -> authority.startsWith(SecurityConstants.ROLE))
				.map(authority -> authority.split(StrUtil.UNDERLINE)[1]).collect(Collectors.toList());

		// 当前用户的角色为空
		if (CollectionUtil.isEmpty(roleIdList)) {
			return false;
		}

		SysRole role = sysRoleService.listByIds(roleIdList).stream().min(Comparator.comparingInt(SysRole::getDsType))
				.get();

		// 角色有可能已经删除了
		if (ObjectUtil.isNull(role)) {
			return false;
		}

		Integer dsType = role.getDsType();
		// 查询全部
		if (DataScopeTypeEnum.ALL.getType() == dsType) {
			return true;
		}
		// 自定义
		if (DataScopeTypeEnum.CUSTOM.getType() == dsType) {
			String dsScope = role.getDsScope();
			deptList.addAll(
					Arrays.stream(dsScope.split(StrUtil.COMMA)).map(Integer::parseInt).collect(Collectors.toList()));
		}
		// 查询本级及其下级
		if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
			List<Integer> deptIdList = relationService
					.list(new LambdaQueryWrapper<SysDeptRelation>().eq(SysDeptRelation::getAncestor, user.getDeptId()))
					.stream().map(SysDeptRelation::getDescendant).collect(Collectors.toList());
			deptList.addAll(deptIdList);
		}
		// 只查询本级
		if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
			deptList.add(user.getDeptId());
		}
		return false;
	}

}
