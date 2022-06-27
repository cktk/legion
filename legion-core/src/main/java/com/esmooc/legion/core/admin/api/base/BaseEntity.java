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

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @title:
 * @author kite: jack Mao
 * @Date: 2020/8/13 11:54 上午
 * @Version: 1.0
 */
@Data
@JsonIgnoreProperties(value = { "handler", "fieldHandler" })
public class BaseEntity<T extends Model> extends Model implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 创建时间 **/
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** 创建人姓名 **/
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

	/** 创建人ID **/
	@TableField(fill = FieldFill.INSERT)
	private String createUser;

	/** 更新时间 **/
	@TableField(fill = FieldFill.UPDATE)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/** 修改人姓名 **/
	@TableField(fill = FieldFill.UPDATE)
	private String updateBy;

	/** 修改人ID **/
	@TableField(fill = FieldFill.UPDATE)
	private String updateUser;

	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	@TableLogic
	private String delFlag;

}
