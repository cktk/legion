package com.esmooc.legion.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.mapper.SettingMapper;
import com.esmooc.legion.core.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {


    @Override
    public Setting get(String id) {
        log.info("主键  ---{}", id);
        Setting setting = this.getById(id);
        log.info("数据库获取 --- {} ", setting);

        if (setting==null){
            Setting newSett = new Setting();
            newSett.setId(id);
            System.out.println("没有配置过这玩意");
            return newSett;
        }
        System.err.println("查询到数据   "+ setting);
        return setting;
    }

    @Override
    public Setting saveOrUpdateById(Setting setting) {
        if (StrUtil.isBlank(setting.getId())){
            throw new LegionException("配置错误  主键为空");
        }
        boolean saveOrUpdate = this.saveOrUpdate(setting);
        if (saveOrUpdate) {
            return setting;
        }
        return null;
    }
}


