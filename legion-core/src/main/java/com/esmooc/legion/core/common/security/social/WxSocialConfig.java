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

package com.esmooc.legion.core.common.security.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author kite kite
 * @date 2018/8/16 微信登录配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.wx")
public class WxSocialConfig {

	private String appid;

	private String secret;

}
