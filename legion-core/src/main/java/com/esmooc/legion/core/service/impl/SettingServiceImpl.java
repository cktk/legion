package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.mapper.SettingMapper;
import com.esmooc.legion.core.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {

}
