package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.CommonUtil;
import com.esmooc.legion.core.common.utils.EmailUtil;
import com.esmooc.legion.core.common.utils.IpInfoUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.EmailValidate;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.vo.OtherSetting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "邮箱验证接口")
@RequestMapping("/legion/email")
@Transactional
public class EmailValidateController {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Autowired
    private SettingService settingService;



    public OtherSetting getOtherSetting() {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        if (StrUtil.isBlank(setting.getValue())) {
            throw new LegionException("系统未配置访问域名，请联系管理员");
        }
        return new Gson().fromJson(setting.getValue(), OtherSetting.class);
    }

    @RequestMapping(value = "/sendEditCode/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "发送修改邮箱验证码")
    public Result<Object> sendEditCode(@PathVariable String email,
                                       HttpServletRequest request) {

        return sendEmailCode(email, "修改邮箱", "【Legion】修改邮箱验证", "code-email", request);
    }

    @RequestMapping(value = "/sendResetCode/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "发送重置密码邮箱验证码")
    public Result<Object> sendResetCode(@PathVariable String email,
                                        HttpServletRequest request) {

        return sendEmailCode(email, "重置密码", "【Legion】重置密码邮箱验证", "code-email", request);
    }

    /**
     * 发送邮件验证码
     *
     * @param email
     * @param operation
     * @param title
     * @param template
     * @param request
     * @return
     */
    public Result<Object> sendEmailCode(String email, String operation, String title, String template, HttpServletRequest request) {

        // 生成验证码 存入相关信息
        EmailValidate e = new EmailValidate();
        e.setOperation(operation);
        // 验证是否注册
        User user = userService.findByEmail(email);
        if ("修改邮箱".equals(operation)) {
            if (user != null) {
                return ResultUtil.error("该邮箱已绑定账号");
            }
            User u = SecurityUtil.getUser();
            e.setUsername(u.getUsername());
        } else if ("重置密码".equals(operation)) {
            if (user == null) {
                return ResultUtil.error("该邮箱未注册");
            }
            e.setUsername(user.getUsername());
        }

        // IP限流 1分钟限1个请求
        String key = "sendEmailCode:" + ipInfoUtil.getIpAddr(request);
        String value = redisTemplate.get(key);
        if (StrUtil.isNotBlank(value)) {
            return ResultUtil.error("您发送的太频繁啦，请稍后再试");
        }

        String code = CommonUtil.getRandomNum();
        e.setCode(code);
        e.setEmail(email);
        e.setFullUrl(getOtherSetting().getDomain());
        redisTemplate.set(CommonConstant.PRE_EMAIL + email, new Gson().toJson(e, EmailValidate.class), 10L, TimeUnit.MINUTES);

        emailUtil.sendTemplateEmail(email, title, template, e);
        // 请求成功 标记限流
        redisTemplate.set(key, "sended", 1L, TimeUnit.MINUTES);
        return ResultUtil.success("发送成功");
    }

    @RequestMapping(value = "/editEmail", method = RequestMethod.POST)
    @ApiOperation(value = "修改邮箱或重置密码")
    public Result<Object> editEmail(@RequestParam String email) {

        User u =  SecurityUtil.getUser();
        u.setEmail(email);
        userService.updateById(u);
        // 删除缓存
        redisTemplate.delete("user::" + u.getUsername());
        return ResultUtil.success("修改邮箱成功");
    }
}
