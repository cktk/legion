package com.esmooc.legion.activiti.utils;

import com.esmooc.legion.activiti.dao.ActProcessDao;
import com.esmooc.legion.activiti.entity.ActBusiness;
import com.esmooc.legion.activiti.entity.ActProcess;
import com.esmooc.legion.activiti.vo.EmailMessage;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.sms.SmsUtil;
import com.esmooc.legion.core.common.utils.EmailUtil;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.MessageSendService;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.service.UserService;
import com.esmooc.legion.core.vo.OtherSetting;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DaiMao
 */
@Transactional
@Component
@Slf4j
public class MessageUtil {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private ActProcessDao actProcessDao;

    @Autowired
    private SettingService settingService;

    public OtherSetting getOtherSetting() {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        if (StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("系统未配置访问域名");
        }
        return new Gson().fromJson(setting.getValue(), OtherSetting.class);
    }

    /**
     * 发送工作流消息
     * @param userId      发送用户
     * @param messageId   消息ID
     * @param actBusiness 业务关联表
     * @param smsMessage  短信消息
     * @param sendMessage 是否发站内信息
     * @param sendSms     是否发短信
     * @param sendEmail   是否发邮件
     */
    @Async
    public void sendActMessage(String userId, String messageId, ActBusiness actBusiness, String smsMessage,
                               Boolean sendMessage, Boolean sendSms, Boolean sendEmail) {

        User user = userService.get(userId);
        if (user == null) {
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("nickname", user.getNickname());
        if (StrUtil.isNotBlank(actBusiness.getUserId())) {
            User applyer = userService.get(actBusiness.getUserId());
            if (applyer != null) {
                params.put("applyer", applyer.getNickname());
            }
        }
        ActProcess actProcess = actProcessDao.findById(actBusiness.getProcDefId()).orElse(null);
        if (actProcess != null) {
            params.put("processName", actProcess.getName());
        }

        // 站内消息
        if (sendMessage) {
            messageSendService.sendTemplateMessage(userId, messageId, params);
        }

        // 短信消息
        if (StrUtil.isNotBlank(user.getMobile()) && sendSms) {
            try {
                smsUtil.sendActMessage(user.getMobile(), smsMessage);
            } catch (Exception e) {
                log.error(e.toString());
            }
        }

        // 邮件消息
        Message message = messageSendService.getTemplateMessage(messageId, params);
        // 填充模版消息 邮箱消息使用
        String title = message.getTitle(), content = HtmlUtil.cleanHtmlTag(message.getContent());
        if (StrUtil.isNotBlank(user.getEmail()) && sendEmail) {
            EmailMessage e = new EmailMessage().setContent(content).setFullUrl(getOtherSetting().getDomain());
            try {
                emailUtil.sendTemplateEmail(user.getEmail(), "【Legion】" + title, "act-message-email", e);
            } catch (Exception ex) {
                log.error(ex.toString());
            }
        }
    }
}
