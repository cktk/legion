package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.*;
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
 * @author Daimao
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
            return new ResultUtil<OssSetting>().setData(null);
        }
        OssSetting ossSetting = new Gson().fromJson(setting.getValue(), OssSetting.class);
        ossSetting.setSecretKey("**********");
        return new ResultUtil<OssSetting>().setData(ossSetting);
    }


    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ApiOperation(value = "查看email配置")
    public Result<EmailSetting> email() {

        Setting setting = settingService.get(SettingConstant.EMAIL_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<EmailSetting>().setData(null);
        }
        EmailSetting emailSetting = new Gson().fromJson(setting.getValue(), EmailSetting.class);
        emailSetting.setPassword("**********");
        return new ResultUtil<EmailSetting>().setData(emailSetting);
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    @ApiOperation(value = "查看其他配置")
    public Result<OtherSetting> other() {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<OtherSetting>().setData(null);
        }
        OtherSetting otherSetting = new Gson().fromJson(setting.getValue(), OtherSetting.class);
        return new ResultUtil<OtherSetting>().setData(otherSetting);
    }


    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ApiOperation(value = "查看公告配置")
    public Result<NoticeSetting> notice() {

        Setting setting = settingService.get(SettingConstant.NOTICE_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return new ResultUtil<NoticeSetting>().setData(null);
        }
        NoticeSetting noticeSetting = new Gson().fromJson(setting.getValue(), NoticeSetting.class);
        return new ResultUtil<NoticeSetting>().setData(noticeSetting);
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
        settingService.saveOrUpdate(setting);
        // 保存启用的OSS服务商
        Setting used = settingService.get(SettingConstant.OSS_USED);
        used.setValue(name);
        settingService.saveOrUpdate(used);
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
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }


    @RequestMapping(value = "/other/set", method = RequestMethod.POST)
    @ApiOperation(value = "其他配置")
    public Result<Object> otherSet(OtherSetting otherSetting) {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        setting.setValue(new Gson().toJson(otherSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }


    @RequestMapping(value = "/notice/set", method = RequestMethod.POST)
    @ApiOperation(value = "公告配置")
    public Result<Object> noticeSet(NoticeSetting noticeSetting) {

        Setting setting = settingService.get(SettingConstant.NOTICE_SETTING);
        setting.setValue(new Gson().toJson(noticeSetting));
        settingService.saveOrUpdate(setting);
        return ResultUtil.data(null);
    }
}
