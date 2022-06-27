/*
 *
 *  *    Copyright (c) 2020-2049, jack Mao All rights reserved.
 *  *
 *  * The author reserves all rights. It is strictly prohibited to use, reprint and disseminate without authorization and all commercial activities.
 *  * All consequences shall be borne by the infringer.
 *  * The right of interpretation of the above statement belongs to "I or my company".
 *  * Author:  jack Mao （maojiajiajia@gmail.com）
 *
 */

package com.esmooc.legion.core.admin.api.base;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.esmooc.legion.core.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @title:
 * @author kite: jack Mao
 * @Date: 2020/8/13 11:47 上午
 * @Version: 1.0
 */
@Slf4j
@Component
public class VehicleMybatisMateHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
		if (null != SecurityUtils.getUser()) {
			this.setFieldValByName("createBy", SecurityUtils.getUser().getUsername(), metaObject);
			this.setFieldValByName("createUser", SecurityUtils.getUser().getId().toString(), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		if (null != SecurityUtils.getUser()) {
			this.setFieldValByName("updateBy", SecurityUtils.getUser().getUsername(), metaObject);
			this.setFieldValByName("updateUser", SecurityUtils.getUser().getId().toString(), metaObject);
		}
	}

}
