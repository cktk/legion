package com.esmooc.legion.core.common.sms;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 工厂模式
 * @author Daimao
 */
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SmsFactory {

    SettingService settingService;
    AliSms aliSms;
    TencentSms tencentSms;

    public Sms getSms() {

        Setting setting = settingService.getById(SettingConstant.SMS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置OSS存储服务");
        }
        String type = setting.getValue();
        if (type.equals(SettingConstant.ALI_SMS)) {
            return aliSms;
        } else if (type.equals(SettingConstant.TENCENT_SMS)) {
            return tencentSms;
        } else {
            throw new LegionException("暂不支持该存储配置，请检查配置");
        }
    }
}
