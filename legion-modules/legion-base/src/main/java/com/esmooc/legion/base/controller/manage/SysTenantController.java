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

package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.entity.SysTenant;
import com.esmooc.legion.core.service.SysTenantService;
import com.esmooc.legion.core.common.core.constant.CacheConstants;
import com.esmooc.legion.core.common.core.util.R;
import com.esmooc.legion.core.common.log.annotation.SysLog;
import com.esmooc.legion.core.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租户管理
 *
 * @author kite kite
 * @date 2019-05-15 15:55:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Api(value = "tenant", tags = "租户管理")
public class SysTenantController {

	private final SysTenantService sysTenantService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysTenant 租户
	 * @return
	 */
	@GetMapping("/page")
	public R getSysTenantPage(Page page, SysTenant sysTenant) {
		return R.ok(sysTenantService.page(page, Wrappers.query(sysTenant)));
	}

	/**
	 * 通过id查询租户
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(sysTenantService.getById(id));
	}

	/**
	 * 新增租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@SysLog("新增租户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_add')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R save(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.saveTenant(sysTenant));
	}

	/**
	 * 修改租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@SysLog("修改租户")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_edit')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R updateById(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.updateById(sysTenant));
	}

	/**
	 * 通过id删除租户
	 * @param id id
	 * @return R
	 */
	@SysLog("删除租户")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_systenant_del')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R removeById(@PathVariable Integer id) {
		return R.ok(sysTenantService.removeById(id));
	}

	/**
	 * 查询全部有效的租户
	 * @return
	 */
	@Inner(value = false)
	@GetMapping("/list")
	public R list() {
		List<SysTenant> tenants = sysTenantService.getNormalTenant().stream()
				.filter(tenant -> tenant.getStartTime().isBefore(LocalDate.now()))
				.filter(tenant -> tenant.getEndTime().isAfter(LocalDate.now())).collect(Collectors.toList());
		return R.ok(tenants);
	}

}
