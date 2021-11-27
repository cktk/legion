package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.sms.SmsUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.*;
import com.esmooc.legion.core.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "基本配置接口")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/legion/setting")
public class SettingController {

    SettingService settingService;

    @RequestMapping(value = "/seeSecret/{settingName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看私密配置")
    public Result<Object> seeSecret(@PathVariable String settingName) {

        String result = "";
        Setting setting = settingService.getById(settingName);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.error("配置不存在");
        }
        switch (settingName) {
            case SettingConstant.QINIU_OSS:
            case SettingConstant.ALI_OSS:
            case SettingConstant.TENCENT_OSS:
            case SettingConstant.MINIO_OSS:
                result = JSONUtil.toBean(setting.getValue(), OssSetting.class).getSecretKey();
                break;
            case SettingConstant.ALI_SMS:
            case SettingConstant.TENCENT_SMS:
                result = JSONUtil.toBean(setting.getValue(), SmsSetting.class).getSecretKey();
                break;
            case SettingConstant.EMAIL_SETTING:
                result = JSONUtil.toBean(setting.getValue(), EmailSetting.class).getPassword();
                break;
            case SettingConstant.VAPTCHA_SETTING:
                result = JSONUtil.toBean(setting.getValue(), VaptchaSetting.class).getSecretKey();
                break;
        }
        return ResultUtil.data(result);
    }

    @RequestMapping(value = "/oss/check", method = RequestMethod.GET)
    @ApiOperation(value = "检查OSS配置")
    public Result<Object> ossCheck() {

        Setting setting = settingService.getById(SettingConstant.OSS_USED);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return ResultUtil.error(501, "您还未配置第三方OSS服务");
        }
        return ResultUtil.data(setting.getValue());
    }

//    @RequestMapping(value = "/sms/check", method = RequestMethod.GET)
//    @ApiOperation(value = "检查短信配置")
//    public Result<Object> smsCheck() {
//
//        Setting setting = settingService.get(SettingConstant.SMS_USED);
//        if (setting == null || StrUtil.isBlank(setting.getValue())) {
//            return ResultUtil.error(501, "您还未配置第三方短信服务");
//        }
//        return ResultUtil.data(setting.getValue());
//    }

    @RequestMapping(value = "/oss/{serviceName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看OSS配置")
    public Result<OssSetting> oss(@PathVariable String serviceName) {

        Setting setting = new Setting();
        if (serviceName.equals(SettingConstant.QINIU_OSS) || serviceName.equals(SettingConstant.ALI_OSS)
                || serviceName.equals(SettingConstant.TENCENT_OSS) || serviceName.equals(SettingConstant.MINIO_OSS)
                || serviceName.equals(SettingConstant.LOCAL_OSS)) {
            setting = settingService.getById(serviceName);
        }
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<OssSetting>().setData(null);
        }
        OssSetting ossSetting = JSONUtil.toBean(setting.getValue(), OssSetting.class);
        ossSetting.setSecretKey("**********");
        return new ResultUtil<OssSetting>().setData(ossSetting);
    }

    @RequestMapping(value = "/sms/{serviceName}", method = RequestMethod.GET)
    @ApiOperation(value = "查看短信配置")
    public Result<SmsSetting> sms(@PathVariable String serviceName) {

        Setting setting = new Setting();
        if (serviceName.equals(SettingConstant.ALI_SMS) || serviceName.equals(SettingConstant.TENCENT_SMS)) {
            setting = settingService.getById(serviceName);
        }
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<SmsSetting>().setData(null);
        }
        SmsSetting smsSetting = JSONUtil.toBean(setting.getValue(), SmsSetting.class);
        smsSetting.setSecretKey("**********");
        if (smsSetting.getType() != null) {
            Setting code = settingService.getById(serviceName + "_" + SmsUtil.getTemplateSuffix(smsSetting.getType()));
            smsSetting.setTemplateCode(code.getValue());
        }
        return new ResultUtil<SmsSetting>().setData(smsSetting);
    }

    @RequestMapping(value = "/sms/templateCode/{serviceName}/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "查看短信模板配置")
    public Result<String> smsTemplateCode(@PathVariable String serviceName,
                                          @PathVariable Integer type) {

        String templateCode = "";
        if (type != null) {
            Setting setting = settingService.getById(serviceName + "_" + SmsUtil.getTemplateSuffix(type));
            if (StrUtil.isNotBlank(setting.getValue())) {
                templateCode = setting.getValue();
            }
        }
        return new ResultUtil<String>().setData(templateCode);
    }

    @RequestMapping(value = "/vaptcha", method = RequestMethod.GET)
    @ApiOperation(value = "查看vaptcha配置")
    public Result<VaptchaSetting> vaptcha() {

        Setting setting = settingService.getById(SettingConstant.VAPTCHA_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<VaptchaSetting>().setData(null);
        }
        VaptchaSetting vaptchaSetting = JSONUtil.toBean(setting.getValue(), VaptchaSetting.class);
        vaptchaSetting.setSecretKey("**********");
        return new ResultUtil<VaptchaSetting>().setData(vaptchaSetting);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ApiOperation(value = "查看email配置")
    public Result<EmailSetting> email() {

        Setting setting = settingService.getById(SettingConstant.EMAIL_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<EmailSetting>().setData(null);
        }
        EmailSetting emailSetting = JSONUtil.toBean(setting.getValue(), EmailSetting.class);
        emailSetting.setPassword("**********");
        return new ResultUtil<EmailSetting>().setData(emailSetting);
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    @ApiOperation(value = "查看其他配置")
    public Result<OtherSetting> other() {

        Setting setting = settingService.getById(SettingConstant.OTHER_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<OtherSetting>().setData(null);
        }
        OtherSetting otherSetting = JSONUtil.toBean(setting.getValue(), OtherSetting.class);
        return new ResultUtil<OtherSetting>().setData(otherSetting);
    }

    @RequestMapping(value = "/autoChat", method = RequestMethod.GET)
    @ApiOperation(value = "机器人配置")
    public Result<AutoChatSetting> autoChat() {

        Setting setting = settingService.getById(SettingConstant.CHAT_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<AutoChatSetting>().setData(null);
        }
        AutoChatSetting chatSetting = JSONUtil.toBean(setting.getValue(), AutoChatSetting.class);
        return new ResultUtil<AutoChatSetting>().setData(chatSetting);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ApiOperation(value = "查看公告配置")
    public Result<NoticeSetting> notice() {

        Setting setting = settingService.getById(SettingConstant.NOTICE_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<NoticeSetting>().setData(null);
        }
        NoticeSetting noticeSetting = JSONUtil.toBean(setting.getValue(), NoticeSetting.class);
        return new ResultUtil<NoticeSetting>().setData(noticeSetting);
    }

    @RequestMapping(value = "/oss/set", method = RequestMethod.POST)
    @ApiOperation(value = "OSS配置")
    public Result<Object> ossSet(OssSetting ossSetting) {

        String name = ossSetting.getServiceName();
        Setting setting = settingService.getById(name);

        if (name.equals(SettingConstant.QINIU_OSS) || name.equals(SettingConstant.ALI_OSS)
                || name.equals(SettingConstant.TENCENT_OSS) || name.equals(SettingConstant.MINIO_OSS)) {
            // 判断是否修改secrectKey 保留原secrectKey 避免保存***加密字符
            if (StrUtil.isNotBlank(setting.getValue()) && !ossSetting.getChanged()) {
                String secrectKey = JSONUtil.toBean(setting.getValue(), OssSetting.class).getSecretKey();
                ossSetting.setSecretKey(secrectKey);
            }
        }

        if (setting == null) setting = new Setting();

        //不用发起质疑 这边就是这么奇怪
        setting.setId(name);
        setting.setValue(JSONUtil.toJsonStr(ossSetting));
        settingService.saveOrUpdate(setting);
        // 保存启用的OSS服务商
        Setting used = settingService.getById(SettingConstant.OSS_USED);
        used.setValue(name);
        settingService.saveOrUpdate(used);
        return ResultUtil.data(ossSetting);
    }

    @RequestMapping(value = "/sms/set", method = RequestMethod.POST)
    @ApiOperation(value = "短信配置")
    public Result<Object> smsSet(SmsSetting smsSetting) {

        String name = smsSetting.getServiceName();
        Setting setting = settingService.getById(name);
        if (name.equals(SettingConstant.ALI_SMS) || name.equals(SettingConstant.TENCENT_SMS)) {
            // 判断是否修改secrectKey 保留原secrectKey 避免保存***加密字符
            if (StrUtil.isNotBlank(setting.getValue()) && !smsSetting.getChanged()) {
                String secrectKey = JSONUtil.toBean(setting.getValue(), SmsSetting.class).getSecretKey();
                smsSetting.setSecretKey(secrectKey);
            }
        }
        if (smsSetting.getType() != null) {
            Setting codeSetting = settingService.getById(name + "_" + SmsUtil.getTemplateSuffix(smsSetting.getType()));
            codeSetting.setValue(smsSetting.getTemplateCode());
            settingService.saveOrUpdate(codeSetting);
        }
        setting.setValue(JSONUtil.toJsonStr(smsSetting.setType(null).setTemplateCode(null)));
        settingService.saveOrUpdate(setting);
        // 保存启用的短信服务商
        Setting used = settingService.getById(SettingConstant.SMS_USED);
        used.setValue(name);
        settingService.saveOrUpdate(used);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/email/set", method = RequestMethod.POST)
    @ApiOperation(value = "email配置")
    public Result<Object> emailSet(EmailSetting emailSetting) {

        Setting setting = settingService.getById(SettingConstant.EMAIL_SETTING);
        if (StrUtil.isNotBlank(setting.getValue()) && !emailSetting.getChanged()) {
            String password = JSONUtil.toBean(setting.getValue(), EmailSetting.class).getPassword();
            emailSetting.setPassword(password);
        }
        setting.setValue(JSONUtil.toJsonStr(emailSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/vaptcha/set", method = RequestMethod.POST)
    @ApiOperation(value = "vaptcha配置")
    public Result<Object> vaptchaSet(VaptchaSetting vaptchaSetting) {

        Setting setting = settingService.getById(SettingConstant.VAPTCHA_SETTING);
        if (StrUtil.isNotBlank(setting.getValue()) && !vaptchaSetting.getChanged()) {
            String key = JSONUtil.toBean(setting.getValue(), VaptchaSetting.class).getSecretKey();
            vaptchaSetting.setSecretKey(key);
        }
        setting.setValue(JSONUtil.toJsonStr(vaptchaSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/other/set", method = RequestMethod.POST)
    @ApiOperation(value = "其他配置")
    public Result<Object> otherSet(OtherSetting otherSetting) {

        Setting setting = settingService.getById(SettingConstant.OTHER_SETTING);
        setting.setValue(JSONUtil.toJsonStr(otherSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/autoChat/set", method = RequestMethod.POST)
    @ApiOperation(value = "机器人配置")
    public Result<Object> autoChatSet(AutoChatSetting chatSetting) {

        Setting setting = settingService.getById(SettingConstant.CHAT_SETTING);
        setting.setValue(JSONUtil.toJsonStr(chatSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }

    @RequestMapping(value = "/notice/set", method = RequestMethod.POST)
    @ApiOperation(value = "公告配置")
    public Result<Object> noticeSet(NoticeSetting noticeSetting) {

        Setting setting = settingService.getById(SettingConstant.NOTICE_SETTING);
        setting.setValue(JSONUtil.toJsonStr(noticeSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }
}
