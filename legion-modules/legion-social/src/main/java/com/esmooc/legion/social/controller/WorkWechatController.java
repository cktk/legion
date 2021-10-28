package com.esmooc.legion.social.controller;

import cn.hutool.json.JSONUtil;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SecurityConstant;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.config.security.SecurityUserDetails;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.social.entity.Social;
import com.esmooc.legion.social.service.SocialService;
import com.esmooc.legion.social.vo.WokWechatUserInfo;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * https://work.weixin.qq.com/api/doc/90000/90135/91020
 * @author Daimao
 */
@Slf4j
@Api(tags = "企业微信登录接口")
@RequestMapping("/legion/social/workwechat")
@RestController
public class WorkWechatController {

    @Value("${legion.social.workwechat.appId}")
    private String appId;

    @Value("${legion.social.workwechat.agentId}")
    private String agentId;

    @Value("${legion.social.workwechat.appSecret}")
    private String appSecret;

    @Value("${legion.social.workwechat.callbackUrl}")
    private String callbackUrl;

    @Value("${legion.social.callbackFeUrl}")
    private String callbackFeUrl;

    @Value("${legion.social.callbackFeRelateUrl}")
    private String callbackFeRelateUrl;

    private static final String STATE = SecurityConstant.WORKWECHAT_STATE;

    private static final Integer TYPE = CommonConstant.SOCIAL_TYPE_WORKWECHAT;

    @Autowired
    private SocialService socialService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    /**
     * 申请令牌地址
     */
    private static final String AUTHORIZE_URL = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect";

    /**
     * 获取access_token地址
     */
    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    /**
     * 获取用户ID地址
     */
    private static final String GET_USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

    /**
     * 获取用户信息地址
     */
    private static final String GET_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业微信认证链接")
    public Result<Object> login() {

        // 生成并保存state 忽略该参数有可能导致CSRF攻击
        String state = String.valueOf(System.currentTimeMillis());
        redisTemplate.set(STATE + state, "VALID", 3L, TimeUnit.MINUTES);

        // 传递参数appId、agentid、state、redirect_uri
        String url = AUTHORIZE_URL + "?appid=" + appId + "&agentid=" + agentId + "&state=" + state
                + "&redirect_uri=" + callbackUrl;

        return ResultUtil.data(url);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    @ApiOperation(value = "获取accessToken")
    @SystemLog(description = "企业微信关联登录", type = LogType.LOGIN)
    public String callback(@RequestParam(required = false) String code,
                                 @RequestParam(required = false) String state) throws UnsupportedEncodingException {

        if (StrUtil.isBlank(code)) {
            return "redirect:" + callbackFeUrl + "?error=" + URLEncoder.encode("您未同意授权", "utf-8");
        }
        // 验证state
        String v = redisTemplate.get(STATE + state);
        redisTemplate.delete(STATE + state);
        if (StrUtil.isBlank(v)) {
            return "redirect:" + callbackFeUrl + "?error=" + URLEncoder.encode("授权超时或state不正确", "utf-8");
        }

        // 申请令牌 get请求 传递参数appId、appSecret
        String result = HttpUtil.get(ACCESS_TOKEN_URL + "?corpid=" + appId +"&corpsecret=" + appSecret);
        if (!result.contains("access_token")) {
            return "redirect:" + callbackFeUrl + "?error=" + URLEncoder.encode("获取access_token失败", "utf-8");
        }
        String accessToken = JSONUtil.parseObj(result).getStr("access_token");

        // 获取ID信息
        String userIdResult = HttpUtil.get(GET_USERINFO_URL + "?access_token=" + accessToken + "&code=" + code);
        String userId = JSONUtil.parseObj(userIdResult).getStr("UserId");

        // 获取User信息
        String userInfo = HttpUtil.get(GET_USER_URL + "?access_token=" + accessToken + "&userid=" + userId);

        WokWechatUserInfo w = JSONUtil.toBean(userInfo, WokWechatUserInfo.class);
        // 存入数据库
        Social workwechat = socialService.findByOpenIdAndPlatform(userId, TYPE);
        if (workwechat == null) {
            Social neww = new Social().setOpenId(userId).setUsername(w.getName()).setAvatar(w.getAvatar()).setPlatform(TYPE);
            workwechat = socialService.save(neww);
        }

        String url = "";
        // 判断是否绑定账号
        if (StrUtil.isNotBlank(workwechat.getRelateUsername())) {
            // 已绑定 直接登录
            String JWT = securityUtil.getToken(workwechat.getRelateUsername(), true);
            // 存入redis
            String JWTKey = IdUtil.simpleUUID();
            redisTemplate.set(JWTKey, JWT, 2L, TimeUnit.MINUTES);
            url = callbackFeUrl + "?related=1&JWTKey=" + JWTKey;
            // 记录日志使用
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    new SecurityUserDetails(new User().setUsername(workwechat.getRelateUsername())), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // 未绑定 Redis存入id
            String idToken = IdUtil.simpleUUID();
            redisTemplate.set(idToken, workwechat.getId(), 5L, TimeUnit.MINUTES);
            url = callbackFeRelateUrl + "?socialType=" + TYPE + "&id=" + idToken;
        }
        return "redirect:" + url;
    }
}
