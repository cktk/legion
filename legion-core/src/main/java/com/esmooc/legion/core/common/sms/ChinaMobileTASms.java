package com.esmooc.legion.core.common.sms;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.MD5Util;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.MessageSmsSend;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.service.mybatis.MessageSmsSendService;
import com.esmooc.legion.core.vo.MessageResp;
import com.esmooc.legion.core.vo.SmsSetting;
import com.esmooc.legion.core.vo.SubmitRep;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * @Author 中国移动泰安地区两癌专业
 * @Date: 2022/02/17/ 20:36
 * @Description:
 */
@Slf4j
@Component
public class ChinaMobileTASms implements Sms {

    @Autowired
    private SettingService settingService;
    @Autowired
    private MessageSmsSendService messageSmsSendService;


    /**
     * 获取配置
     *
     * @return
     */
    @Override
    public SmsSetting getSmsSetting() {

        Setting setting = settingService.get(SettingConstant.CHINA_MOBILE_TA_SMS);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("您还未配置泰安移动短信服务");
        }
        return new Gson().fromJson(setting.getValue(), SmsSetting.class);
    }

    /**
     * 发送短信
     *
     * @param mobile       手机号 多个,逗号分隔 若为11位国内手机号无需加国家区号86
     *                     国际号码需加上区号 [国家或地区码][手机号] 如8109012345678、86为日本、09012345678为手机号
     * @param params       参数 JSON格式，如{"code": "1234"}
     *                     若启用腾讯短信会自动按顺序转换为逗号分隔的数组值如[1234]
     * @param templateCode 短信模板code/id
     */
    @Override
    public MessageSmsSend sendSms(String mobile, String params, String templateCode) {
        log.info("{中国移动泰安短信平台开始发送短信 手机号 {},参数 {}, 模板code {}",mobile,params,templateCode);
        if (mobile.length() > 11) {
            throw new LegionException("最多能批量发送1个手机号");
        }


        SmsSetting setting = getSmsSetting();
        log.info("中国移动配置信息 {}",setting);

        if (StrUtil.isBlank(setting.getUrl())){
            throw new LegionException("中国移动泰安平台地址未配置");
        }



//        MessageSms template = messageSmsService.getTemplate(templateCode);
        //messageSmsSend.setWorkType("业务类型不知道");
        //messageSmsSend.setRetrySend(); 失败后是否重发
        //messageSmsSend.setRetryId();
        MessageSmsSend messageSmsSend = new MessageSmsSend();
        messageSmsSend.setType(SettingConstant.CHINA_MOBILE_TA_SMS);
        messageSmsSend.setStatus(SystemConstant.FLAG_N);
        messageSmsSend.setTemplateType(templateCode);//模板类型
//        messageSmsSend.setTemplateId(template.getId()); //本地模板ID
//        messageSmsSend.setSettingId(setting.getId());
        messageSmsSend.setContent(params);
        messageSmsSend.setPhone(mobile); //批量发送短信
        messageSmsSend.setSendTime(new Date());
        User user = SecurityUtil.securityUtil.getCurrUser();
        messageSmsSend.setSendUserId(user.getId());
        messageSmsSend.setSendUserName(user.getUsername());
        MessageResp resp =new MessageResp();

        log.info("中国移动配置信息 构建业务信息  {}",messageSmsSend);
        try {
            messageSmsSend.setSendStatus(SystemConstant.FLAG_Y); //是否已发送
            String templateContent = setting.getContent();//模板获取
            JSONObject object = JSONUtil.parseObj(params);
            Set<String> keySet = object.keySet();
            String message = "";
            for (String key : keySet) {
                String param = object.getStr(key, "");
                templateContent = StrUtil.replace(templateContent, "${" + key + "}", param);
            }
            log.info("中国移动配置信息 短信内容 {}",templateContent);
            SubmitRep submitReq = new SubmitRep();
            submitReq.setApId(setting.getAppId()); //apID
            submitReq.setEcName(setting.getUseName()); //ecName
            submitReq.setSecretKey(setting.getSecretKey()); //secretKey
            submitReq.setContent(message);
            submitReq.setMobile(mobile);
            submitReq.setAddSerial(setting.getAccessKey()); //addSerial
            submitReq.setSign(setting.getSignName()); //sign
             resp = sendMessage(submitReq,setting.getUrl());
            log.info("中国移动配置信息 返回参数 {}",resp);
            if (resp.getSuccess()) {
                messageSmsSend.setStatus(SystemConstant.FLAG_Y);
                messageSmsSend.setBizId(resp.getMsgGroup()); //腾讯的流水号
                messageSmsSend.setCode(resp.getRspcod());//运营商返回的code
                messageSmsSend.setSendRes(JSONUtil.toJsonStr(resp)); //运营商原始数据
            }

        } catch (LegionException e) {
            log.error("中国移动泰安短信请求发失败 原始信息 {}", e);
            messageSmsSend.setErrMsg(e.getMessage());
            messageSmsSend.setErrType(SystemConstant.SMS_ERROR_OTH);

        } catch (Exception e) {
            log.error("中国移动泰安 原始信息 {}", e);
            messageSmsSend.setErrMsg(e.getMessage());
            messageSmsSend.setErrType(SystemConstant.SMS_ERROR_ERR);
        }finally {
            messageSmsSend.setBizId(resp.getMsgGroup());
            messageSmsSend.setCode(resp.getSuccess()+"");
            messageSmsSend.setSendRes(JSONUtil.toJsonStr(resp));
            messageSmsSend.setErrCode(resp.getSuccess()+"");
            messageSmsSend.setErrType(SystemConstant.SMS_ERROR_TYPE);
            messageSmsSend.setErrMsg(resp.getRspcod());
            log.info("中国移动配置信息 最终构建参数 {}",messageSmsSend);
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


    /**
     * 在业务代码里面填上ip限流
     * @param submitReq
     * @param url
     * @return
     */
    public static MessageResp sendMessage(SubmitRep submitReq,String url) {

        String key = "sendSms:" + submitReq.getMobile();
       /* // IP限流 1分钟限1个请求
        String key = "sendSms:" + ipInfoUtil.getIpAddr(request);
        String value = redisTemplate.get(key);
        if (StrUtil.isNotBlank(value)) {
            return ResultUtil.error("您发送的太频繁啦，请稍后再试");
        }
        // 生成6位数验证码
        String code = CommonUtil.getRandomNum();
        // 缓存验证码
        redisTemplate.set(CommonConstant.PRE_SMS + mobile, code, 5L, TimeUnit.MINUTES);
        // 发送验证码
        smsUtil.sendCode(mobile, code, templateType);
        // 请求成功 标记限流
        redisTemplate.set(key, "sended", 1L, TimeUnit.MINUTES);*/



        log.info("中国移动泰安短信平台 请求参数 {} 请求地址 {} "  ,submitReq,url);
        String stringBuffer = submitReq.getEcName() +
                submitReq.getApId() +
                submitReq.getSecretKey() +
                submitReq.getMobile() +
                submitReq.getContent() +
                submitReq.getSign() +
                submitReq.getAddSerial();
        submitReq.setMac(MD5Util.MD5(stringBuffer));
        //有顺序要求
        String reqText = "{\"addSerial\":\""
                + submitReq.getAddSerial() + "\", \"apId\":\""
                + submitReq.getApId() + "\", \"content\":\""
                + submitReq.getContent() + "\", \"ecName\":\""
                + submitReq.getEcName() + "\", \"mac\":\""
                + submitReq.getMac().toLowerCase() + "\",\"mobiles\":\""
                + submitReq.getMobile() + "\", \"secretKey\":\""
                + submitReq.getSecretKey() + "\" , \"sign\":\""
                + submitReq.getSign() + "\"}";
        String encode = Base64Util.encode(reqText);
        log.info("中国移动配置信息 加密参数  {} reqText {} stringBuffer {}",encode,reqText,stringBuffer);
        String msg = HttpUtil.post(url, encode);
        System.out.println(JSONUtil.toBean(msg, MessageResp.class));
        log.info("中国移动泰安短信平台返回数据 {} ," ,msg );
        return JSONUtil.toBean(msg, MessageResp.class);
    }



}
