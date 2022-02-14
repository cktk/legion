package com.esmooc.legion.core.common.sms;

import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.SmsSetting;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author DaiMao
 */
@Slf4j
@Component
public class TencentSms implements Sms {

    @Autowired
    private SettingService settingService;

    @Override
    public SmsSetting getSmsSetting() {

        Setting setting = settingService.get(SettingConstant.TENCENT_SMS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置腾讯云短信服务");
        }
        return new Gson().fromJson(setting.getValue(), SmsSetting.class);
    }

    @Override
    public void sendSms(String mobile, String params, String templateCode) {

        SmsSetting s = getSmsSetting();
        try {
            Credential cred = new Credential(s.getAccessKey(), s.getSecretKey());
            SmsClient client = new SmsClient(cred, "");
            SendSmsRequest req = new SendSmsRequest();

            // 短信应用 ID: 在 [短信控制台-应用管理] 添加应用后生成的实际 SDKAppID，例如1400006666
            req.setSmsSdkAppid(s.getAppId());
            // 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息
            req.setSign(s.getSignName());
            // 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID
            req.setTemplateID(templateCode);
            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 例如+8613800000000， 其中前面有一个+号 ，86为国家码，13800000000为手机号，最多不要超过200个手机号*/
            String[] phoneNumbers = {"+86" + mobile};
            req.setPhoneNumberSet(phoneNumbers);
            /* 模板参数: 若无模板参数，则设置为空*/
            req.setTemplateParamSet(getParams(params));

            SendStatus sendStatus = client.SendSms(req).getSendStatusSet()[0];
            if (!"Ok".equals(sendStatus.getCode())) {
                throw new LegionException("请求发送验证码失败，" + sendStatus.getMessage());
            }
        } catch (TencentCloudSDKException e) {
            throw new LegionException("请求发送验证码失败，错误码：" + e.getErrorCode());
        }
    }

    public static String[] getPhoneNumbers(String mobile) {

        String[] phoneNumbers = mobile.split(",");
        for (int i = 0; i < phoneNumbers.length; i++) {
            String number = phoneNumbers[i];
            if (NameUtil.mobile(number)) {
                phoneNumbers[i] = "+86" + number;
            } else {
                phoneNumbers[i] = "+" + number;
            }
        }
        return phoneNumbers;
    }

    public static String[] getParams(String params) {

        Set<Map.Entry<String, JsonElement>> entries = JsonParser.parseString(params).getAsJsonObject().entrySet();
        String[] templateParams = new String[entries.size()];
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : entries) {
            String value = entry.getValue().getAsString();
            templateParams[i] = value;
            i++;
        }
        return templateParams;
    }
}
