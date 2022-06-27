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

package com.esmooc.legion.core.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.admin.api.entity.SysRole;
import com.esmooc.legion.core.admin.api.entity.SysRoleMenu;
import com.esmooc.legion.core.admin.api.entity.SysUser;
import com.esmooc.legion.core.admin.mapper.SysRoleMapper;
import com.esmooc.legion.core.admin.mapper.SysRoleMenuMapper;
import com.esmooc.legion.core.admin.mapper.SysUserMapper;
import com.esmooc.legion.core.admin.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kite kite
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	private SysRoleMenuMapper sysRoleMenuMapper;

	private final SysUserMapper userMapper;

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 * @return
	 */
	@Override
	public List findRolesByUserId(Integer userId) {
		return baseMapper.listRolesByUserId(userId);
	}

	/**
	 * 通过角色ID，删除角色,并清空角色菜单缓存
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeRoleById(Integer id) {
		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>update().lambda().eq(SysRoleMenu::getRoleId, id));
		return this.removeById(id);
	}

	@Override
	public List<String> findRoleCodeByUserId(String userId) {
		return baseMapper.listRolesByUserId(Integer.valueOf(userId)).stream().map(i -> i.getRoleCode())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> findRoleCodeByUsername(String username) {
		SysUser user = userMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
		return baseMapper.listRolesByUserId(Integer.valueOf(user.getUserId())).stream().map(i -> i.getRoleCode())
				.collect(Collectors.toList());
	}

}
