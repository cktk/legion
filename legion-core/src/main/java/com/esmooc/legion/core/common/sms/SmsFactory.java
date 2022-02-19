package com.esmooc.legion.core.common.sms;

import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工厂模式
 * @author DaiMao
 */
@Component
public class SmsFactory {

    @Autowired
    private SettingService settingService;

    @Autowired
    private AliSms aliSms;

    @Autowired
    private TencentSms tencentSms;
    @Autowired
    private ChinaMobileTASms chinaMobileTASms;

    public Sms getSms() {

        Setting setting = settingService.get(SettingConstant.SMS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置OSS存储服务");
        }
        String type = setting.getValue();
        if (type.equals(SettingConstant.ALI_SMS)) {
            return aliSms;
        } else if (type.equals(SettingConstant.TENCENT_SMS)) {
            return tencentSms;
        }else if (type.equals(SettingConstant.CHINA_MOBILE_TA_SMS)){
            return chinaMobileTASms;
        }else {
            throw new LegionException("暂不支持该短信配置，请检查配置");
        }
    }
}
