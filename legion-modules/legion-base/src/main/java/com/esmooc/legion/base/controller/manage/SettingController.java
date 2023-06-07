package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.sms.SmsUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.AutoChatSetting;
import com.esmooc.legion.core.entity.vo.EmailSetting;
import com.esmooc.legion.core.entity.vo.NoticeSetting;
import com.esmooc.legion.core.entity.vo.OssSetting;
import com.esmooc.legion.core.entity.vo.OtherSetting;
import com.esmooc.legion.core.entity.vo.SmsSetting;
import com.esmooc.legion.core.entity.vo.VaptchaSetting;
import com.esmooc.legion.core.service.SettingService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "基本配置接口")
@RequestMapping("/legion/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "/seeSecret/{settingName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看私密配置")
    public Result<Object> seeSecret(@PathVariable String settingName) {

        String result = "";
        Setting setting = settingService.get(settingName);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.error("配置不存在");
        }
        if (settingName.equals(SettingConstant.QINIU_OSS) || settingName.equals(SettingConstant.ALI_OSS)
                || settingName.equals(SettingConstant.TENCENT_OSS) || settingName.equals(SettingConstant.MINIO_OSS)) {
            result = new Gson().fromJson(setting.getValue(), OssSetting.class).getSecretKey();
        } else if (settingName.equals(SettingConstant.ALI_SMS) || settingName.equals(SettingConstant.TENCENT_SMS)) {
            result = new Gson().fromJson(setting.getValue(), SmsSetting.class).getSecretKey();
        } else if (settingName.equals(SettingConstant.EMAIL_SETTING)) {
            result = new Gson().fromJson(setting.getValue(), EmailSetting.class).getPassword();
        } else if (settingName.equals(SettingConstant.VAPTCHA_SETTING)) {
            result = new Gson().fromJson(setting.getValue(), VaptchaSetting.class).getSecretKey();
        }
        return ResultUtil.data(result);
    }

    @RequestMapping(value = "/oss/check", method = RequestMethod.GET)
    @ApiOperation(value = "检查OSS配置")
    public Result<Object> ossCheck() {

        Setting setting = settingService.get(SettingConstant.OSS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.error(501, "您还未配置第三方OSS服务");
        }
        return ResultUtil.data(setting.getValue());
    }

    @RequestMapping(value = "/sms/check", method = RequestMethod.GET)
    @ApiOperation(value = "检查短信配置")
    public Result<Object> smsCheck() {

        Setting setting = settingService.get(SettingConstant.SMS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.error(501, "您还未配置第三方短信服务");
        }
        return ResultUtil.data(setting.getValue());
    }

    @RequestMapping(value = "/oss/{serviceName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看OSS配置")
    public Result<OssSetting> oss(@PathVariable String serviceName) {

        Setting setting = new Setting();
        if (serviceName.equals(SettingConstant.QINIU_OSS) || serviceName.equals(SettingConstant.ALI_OSS)
                || serviceName.equals(SettingConstant.TENCENT_OSS) || serviceName.equals(SettingConstant.MINIO_OSS)
                || serviceName.equals(SettingConstant.LOCAL_OSS)) {
            setting = settingService.get(serviceName);
        }
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        OssSetting ossSetting = new Gson().fromJson(setting.getValue(), OssSetting.class);
        ossSetting.setSecretKey("**********");
        return ResultUtil.data(ossSetting);
    }

    @RequestMapping(value = "/sms/{serviceName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看短信配置")
    public Result<SmsSetting> sms(@PathVariable String serviceName) {

        Setting setting = new Setting();
        //TODO 加短信运营商需要加
        if (serviceName.equals(SettingConstant.ALI_SMS) || serviceName.equals(SettingConstant.TENCENT_SMS)) {
            setting = settingService.get(serviceName);
        }
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        SmsSetting smsSetting = new Gson().fromJson(setting.getValue(), SmsSetting.class);
        smsSetting.setSecretKey("**********");
        if (smsSetting.getType() != null) {
            Setting code = settingService.get(serviceName + "_" + SmsUtil.getTemplateSuffix(smsSetting.getType()));
            smsSetting.setTemplateCode(code.getValue());
        }
        return ResultUtil.data(smsSetting);
    }

    @RequestMapping(value = "/sms/templateCode/{serviceName}/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "查看短信模板配置")
    public Result<String> smsTemplateCode(@PathVariable String serviceName,
                                          @PathVariable Integer type) {

        String templateCode = "";
        if (type != null) {
            Setting setting = settingService.get(serviceName + "_" + SmsUtil.getTemplateSuffix(type));
            if (StrUtil.isNotBlank(setting.getValue())) {
                templateCode = setting.getValue();
            }
        }
        return ResultUtil.data(templateCode);
    }

    @RequestMapping(value = "/vaptcha", method = RequestMethod.GET)
    @ApiOperation(value = "查看vaptcha配置")
    public Result<VaptchaSetting> vaptcha() {

        Setting setting = settingService.get(SettingConstant.VAPTCHA_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        VaptchaSetting vaptchaSetting = new Gson().fromJson(setting.getValue(), VaptchaSetting.class);
        vaptchaSetting.setSecretKey("**********");
        return ResultUtil.data(vaptchaSetting);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ApiOperation(value = "查看email配置")
    public Result<EmailSetting> email() {

        Setting setting = settingService.get(SettingConstant.EMAIL_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        EmailSetting emailSetting = new Gson().fromJson(setting.getValue(), EmailSetting.class);
        emailSetting.setPassword("**********");
        return ResultUtil.data(emailSetting);
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    @ApiOperation(value = "查看其他配置")
    public Result<OtherSetting> other() {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        OtherSetting otherSetting = new Gson().fromJson(setting.getValue(), OtherSetting.class);
        return ResultUtil.data(otherSetting);
    }

    @RequestMapping(value = "/autoChat", method = RequestMethod.GET)
    @ApiOperation(value = "机器人配置")
    public Result<AutoChatSetting> autoChat() {

        Setting setting = settingService.get(SettingConstant.CHAT_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        AutoChatSetting chatSetting = new Gson().fromJson(setting.getValue(), AutoChatSetting.class);
        return ResultUtil.data(chatSetting);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ApiOperation(value = "查看公告配置")
    public Result<NoticeSetting> notice() {

        Setting setting = settingService.get(SettingConstant.NOTICE_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.data(null);
        }
        NoticeSetting noticeSetting = new Gson().fromJson(setting.getValue(), NoticeSetting.class);

        return ResultUtil.data(noticeSetting);
    }

    @RequestMapping(value = "/oss/set", method = RequestMethod.POST)
    @ApiOperation(value = "OSS配置")
    public Result<Object> ossSet(OssSetting ossSetting) {

        String name = ossSetting.getServiceName();
        Setting setting = settingService.get(name);
        if (name.equals(SettingConstant.QINIU_OSS) || name.equals(SettingConstant.ALI_OSS)
                || name.equals(SettingConstant.TENCENT_OSS) || name.equals(SettingConstant.MINIO_OSS)) {

            // 判断是否修改secrectKey 保留原secrectKey 避免保存***加密字符
            if (StrUtil.isNotBlank(setting.getValue()) && !ossSetting.getChanged()) {
                String secrectKey = new Gson().fromJson(setting.getValue(), OssSetting.class).getSecretKey();
                ossSetting.setSecretKey(secrectKey);
            }
        }
        setting.setValue(new Gson().toJson(ossSetting));
        settingService.saveOrUpdateById(setting);
        // 保存启用的OSS服务商
        Setting used = settingService.get(SettingConstant.OSS_USED);
        used.setValue(name);
        settingService.saveOrUpdateById(used);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/sms/set", method = RequestMethod.POST)
    @ApiOperation(value = "短信配置")
    public Result<Object> smsSet(SmsSetting smsSetting) {

        String name = smsSetting.getServiceName();
        Setting setting = settingService.get(name);
        if ( name.equals(SettingConstant.ALI_SMS) || name.equals(SettingConstant.TENCENT_SMS)) {
            // 判断是否修改secrectKey 保留原secrectKey 避免保存***加密字符
            if (StrUtil.isNotBlank(setting.getValue()) && !smsSetting.getChanged()) {
                String secrectKey = new Gson().fromJson(setting.getValue(), SmsSetting.class).getSecretKey();
                smsSetting.setSecretKey(secrectKey);
            }
        }
        if (smsSetting.getType() != null) {
            Setting codeSetting = settingService.get(name + "_" + SmsUtil.getTemplateSuffix(smsSetting.getType()));
            codeSetting.setValue(smsSetting.getTemplateCode());
            settingService.saveOrUpdateById(codeSetting);
        }
        setting.setValue(new Gson().toJson(smsSetting.setType(null).setTemplateCode(null)));
        settingService.saveOrUpdateById(setting);
        // 保存启用的短信服务商
        Setting used = settingService.get(SettingConstant.SMS_USED);
        used.setValue(name);
        settingService.saveOrUpdateById(used);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/email/set", method = RequestMethod.POST)
    @ApiOperation(value = "email配置")
    public Result<Object> emailSet(EmailSetting emailSetting) {

        Setting setting = settingService.get(SettingConstant.EMAIL_SETTING);
        if (StrUtil.isNotBlank(setting.getValue()) && !emailSetting.getChanged()) {
            String password = new Gson().fromJson(setting.getValue(), EmailSetting.class).getPassword();
            emailSetting.setPassword(password);
        }
        setting.setValue(new Gson().toJson(emailSetting));
        settingService.saveOrUpdateById(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/vaptcha/set", method = RequestMethod.POST)
    @ApiOperation(value = "vaptcha配置")
    public Result<Object> vaptchaSet(VaptchaSetting vaptchaSetting) {

        Setting setting = settingService.get(SettingConstant.VAPTCHA_SETTING);
        if (StrUtil.isNotBlank(setting.getValue()) && !vaptchaSetting.getChanged()) {
            String key = new Gson().fromJson(setting.getValue(), VaptchaSetting.class).getSecretKey();
            vaptchaSetting.setSecretKey(key);
        }
        setting.setValue(new Gson().toJson(vaptchaSetting));
        settingService.saveOrUpdateById(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/other/set", method = RequestMethod.POST)
    @ApiOperation(value = "其他配置")
    public Result<Object> otherSet(OtherSetting otherSetting) {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        setting.setValue(new Gson().toJson(otherSetting));
        settingService.saveOrUpdateById(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/autoChat/set", method = RequestMethod.POST)
    @ApiOperation(value = "机器人配置")
    public Result<Object> autoChatSet(AutoChatSetting chatSetting) {

        Setting setting = settingService.get(SettingConstant.CHAT_SETTING);
        setting.setValue(new Gson().toJson(chatSetting));
        settingService.saveOrUpdateById(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/notice/set", method = RequestMethod.POST)
    @ApiOperation(value = "公告配置")
    public Result<Object> noticeSet(NoticeSetting noticeSetting) {

        Setting setting = settingService.get(SettingConstant.NOTICE_SETTING);
        setting.setValue(new Gson().toJson(noticeSetting));
        settingService.saveOrUpdateById(setting);
        return ResultUtil.data(null);
    }
}
