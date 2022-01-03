package com.esmooc.legion.core.common.sms;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.SmsSetting;
import com.esmooc.legion.core.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daimao
 */
@Slf4j
@Component
public class AliSms implements Sms {

    @Autowired
    private SettingService settingService;

    @Override
    public SmsSetting getSmsSetting() {

        Setting setting = settingService.getById(SettingConstant.ALI_SMS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置阿里云短信服务");
        }
        return JSONUtil.toBean(setting.getValue(), SmsSetting.class);
    }

    @Override
    public void sendSms(String mobile, String params, String templateCode) {

        SmsSetting s = getSmsSetting();

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", s.getAccessKey(), s.getSecretKey());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        // 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔，上限为1000个手机号码
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", s.getSignName());
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", params);

        CommonResponse response;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            log.error(e.getMessage());
            throw new LegionException("请求发送短信验证码失败，" + e.getErrMsg());
        }
        JSONObject result = JSONUtil.parseObj(response.getData());
        String code = result.getStr("Code");
        String message = result.getStr("Message");
        if (!"OK".equals(code) && !"OK".equals(message)) {
            throw new LegionException("请求发送验证码失败，" + message);
        }
    }
}
