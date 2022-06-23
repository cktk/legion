package com.esmooc.legion.core.common.sms;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.MessageSmsSend;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.service.MessageSmsSendService;
import com.esmooc.legion.core.entity.vo.SmsSetting;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author DaiMao
 */
@Slf4j
@Component
public class AliSms implements Sms {

    @Autowired
    private SettingService settingService;
    @Autowired
    private MessageSmsSendService messageSmsSendService;

    @Override
    public SmsSetting getSmsSetting() {

        Setting setting = settingService.get(SettingConstant.ALI_SMS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置阿里云短信服务");
        }
        return new Gson().fromJson(setting.getValue(), SmsSetting.class);
    }

    @Override
    public MessageSmsSend sendSms(String mobile, String params, String templateCode) {


        if (mobile.length() > 600) {
            throw new LegionException("最多能批量发送50个手机号");
        }

        SmsSetting setting = getSmsSetting();
        MessageSmsSend messageSmsSend = new MessageSmsSend();
        messageSmsSend.setStatus(SystemConstant.FLAG_N);
        messageSmsSend.setTemplateType(templateCode);//模板类型
        messageSmsSend.setType(SettingConstant.ALI_SMS); //我也不知道啥类型啊
//        messageSmsSend.setSettingId(setting.getId());
        messageSmsSend.setContent(params);
        messageSmsSend.setPhone(mobile); //批量发送短信
        messageSmsSend.setSendTime(new Date());
        try {
            User user = SecurityUtil.getUser();
            messageSmsSend.setSendUserId(user.getId());
            messageSmsSend.setSendUserName(user.getUsername());
        } catch (Exception e) {
            log.info("未登陆用户");
        }
//        messageSmsSend.setRetrySend(); 失败后是否重发
//        messageSmsSend.setRetryId();


        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", setting.getAccessKey(), setting.getSecretKey());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        // 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔，上限为1000个手机号码
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", setting.getSignName());
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", params);

        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
            JsonObject result = JsonParser.parseString(response.getData()).getAsJsonObject();
            String code = result.get("Code").getAsString();
            String message = result.get("Message").getAsString();
            String bizId = result.get("BizId").getAsString();
            messageSmsSend.setSendStatus(SystemConstant.FLAG_Y); //是否已发送
            if (!"OK".equals(code) && !"OK".equals(message)) {
                log.error("阿里云短信发送失败 错误代码{} 错误已信息{} 原始信息 {}", code, message, result);
                messageSmsSend.setSendRes(result.toString());
                messageSmsSend.setErrCode(code);
                messageSmsSend.setErrMsg(message);
                messageSmsSend.setErrType(SystemConstant.SMS_ERROR_TYPE);
            } else {
                //            messageSmsSend.setWorkType("业务类型不知道");
                messageSmsSend.setStatus(SystemConstant.FLAG_Y);
                messageSmsSend.setBizId(bizId); //腾讯的流水号
                messageSmsSend.setCode(code);//运营商返回的code
                messageSmsSend.setSendRes(result.toString());//运营商返回原始数据
            }
        } catch (ClientException e) {
            log.error(e.getMessage());
            log.error("请求发送短信验证码失败， " + e.getErrMsg());
            messageSmsSend.setErrMsg(e.getMessage());
            messageSmsSend.setErrType(SystemConstant.SMS_ERROR_OTH);
        } catch (Exception e) {
            log.error("腾讯云短信请求发送失败 原始信息 {}", e);
            messageSmsSend.setErrMsg(e.getMessage());
            messageSmsSend.setErrType(SystemConstant.SMS_ERROR_ERR);
        }

        saveMsgLog(messageSmsSend);
        return messageSmsSend;
    }

    /**
     * 保存发送短信记录
     *
     * @param messageSmsSend
     * @return
     */
    @Override
    public Boolean saveMsgLog(MessageSmsSend messageSmsSend) {
        return messageSmsSendService.saveOrUpdate(messageSmsSend);
    }


}
