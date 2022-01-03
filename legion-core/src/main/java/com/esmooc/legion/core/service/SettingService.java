package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.Setting;
import org.springframework.cache.annotation.CacheConfig;


/**
 * 配置接口
 *
 * @author Daimao
 */
@CacheConfig(cacheNames = "setting")
public interface SettingService extends IService<Setting> {
}
