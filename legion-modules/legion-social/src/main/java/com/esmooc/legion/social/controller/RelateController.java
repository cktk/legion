package com.esmooc.legion.social.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.UserService;
import com.esmooc.legion.social.entity.Social;
import com.esmooc.legion.social.service.SocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author DaiMao
 */
@Slf4j
@Api(tags = "绑定第三方账号接口")
@RequestMapping("/legion/social")
@RestController
@Transactional
public class RelateController {

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;



    @Autowired
    private RedisTemplateHelper redisTemplate;

    @RequestMapping(value = "/relate", method = RequestMethod.POST)
    @ApiOperation(value = "绑定账号")
    public Result<Object> relate(@RequestParam Boolean isLogin,
                                 @RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password,
                                 @RequestParam Integer socialType,
                                 @RequestParam String id) {

        if (isLogin) {
            // 用户已登录
            User user = SecurityUtil.getUser();
            username = user.getUsername();
        } else {
            // 用户未登录
            if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
                return ResultUtil.error("用户名或密码不能为空");
            }
            User user = userService.findByUsername(username);
            if (user == null) {
                return ResultUtil.error("账号或密码错误");
            }
            if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return ResultUtil.error("账号或密码错误");
            }
        }

        // 从redis中获取表id
        String ID = redisTemplate.get(id);
        if (StrUtil.isBlank(ID)) {
            return ResultUtil.error("无效的id");
        }

        String platform = getPlatform(socialType);
        Social s = socialService.findByRelateUsernameAndPlatform(username, socialType);
        if (s != null) {
            return ResultUtil.error("该账户已绑定有" + platform + "账号，请先进行解绑操作");
        }
        Social social = socialService.getById(ID);
        if (social == null) {
            return ResultUtil.error("绑定失败，请先进行第三方授权认证");
        }
        if (StrUtil.isNotBlank(social.getRelateUsername())) {
            return ResultUtil.error("该" + platform + "账号已绑定有用户，请先进行解绑操作");
        }
        social.setRelateUsername(username);
        socialService.updateById(social);

        if (!isLogin) {
            String JWT = SecurityUtil.getToken(username, true);
            // 存入redis
            String JWTKey = IdUtil.simpleUUID();
            redisTemplate.set(JWTKey, JWT, 2L, TimeUnit.MINUTES);
            return ResultUtil.data(JWTKey);
        } else {
            return ResultUtil.data("绑定成功");
        }
    }

    @RequestMapping(value = "/getJWT", method = RequestMethod.GET)
    @ApiOperation(value = "获取JWT")
    public Result<Object> getJWT(@RequestParam String JWTKey) {

        String JWT = redisTemplate.get(JWTKey);
        if (StrUtil.isBlank(JWT)) {
            return ResultUtil.error("获取JWT失败");
        }
        return ResultUtil.data(JWT);
    }

    public String getPlatform(Integer socialType) {

        String platform = "";
        if (CommonConstant.SOCIAL_TYPE_GITHUB.equals(socialType)) {
            platform = "Github";
        } else if (CommonConstant.SOCIAL_TYPE_WECHAT.equals(socialType)) {
            platform = "微信";
        } else if (CommonConstant.SOCIAL_TYPE_QQ.equals(socialType)) {
            platform = "QQ";
        } else if (CommonConstant.SOCIAL_TYPE_WEIBO.equals(socialType)) {
            platform = "微博";
        } else if (CommonConstant.SOCIAL_TYPE_DINGDING.equals(socialType)) {
            platform = "钉钉";
        } else if (CommonConstant.SOCIAL_TYPE_WORKWECHAT.equals(socialType)) {
            platform = "企业微信";
        }
        return platform;
    }
}
