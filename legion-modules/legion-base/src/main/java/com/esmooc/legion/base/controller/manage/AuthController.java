package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.base.async.AddMessage;
import com.esmooc.legion.base.vo.QRStatusVo;
import com.esmooc.legion.core.common.annotation.RateLimiter;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.constant.AppToBConstant;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.config.properties.LegionTokenProperties;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.service.RoleService;
import com.esmooc.legion.core.service.UserRoleService;
import com.esmooc.legion.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(tags = "????????????????????????")
@RequestMapping("/legion/auth")
@CacheConfig(cacheNames = "user")
@Transactional
public class AuthController {

    public static final String USER = "user::";

    public static final String LOGIN_FAIL_FLAG = "loginFailFlag:";

    public static final String LOGIN_TIME_LIMIT = "loginTimeLimit:";

    public static final String GET_NEW_TOKEN_PRE = "GET_NEW_TOKEN";

    public static final Integer LOGIN_FAIL_TIP_TIME = 3;

    @Autowired
    private LegionTokenProperties tokenProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AddMessage addMessage;

    @Autowired
    private RedisTemplateHelper redisTemplate;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SystemLog(description = "????????????", type = LogType.LOGIN)
    @ApiOperation(value = "??????/??????/????????????")
    public Result<Object> login(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam(required = false) Boolean saveLogin) {

        String valueFailFlag = redisTemplate.get(LOGIN_FAIL_FLAG + username);
        Long timeRest = redisTemplate.getExpire(LOGIN_FAIL_FLAG, TimeUnit.MINUTES);
        if (StrUtil.isNotBlank(valueFailFlag)) {
            // ??????????????????
            return ResultUtil.error("????????????????????????????????????" + timeRest + "???????????????");
        }

        User user = SecurityUtil.checkUserPassword(username, password);
        if (user == null) {
            // ????????????????????????
            String valueTime = redisTemplate.get(LOGIN_TIME_LIMIT + username);
            if (StrUtil.isBlank(valueTime)) {
                valueTime = "0";
            }
            // ???????????????????????????
            Integer loginFailTime = Integer.parseInt(valueTime) + 1;
            redisTemplate.set(LOGIN_TIME_LIMIT, loginFailTime.toString(), tokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
            if (loginFailTime >= tokenProperties.getLoginTimeLimit()) {
                redisTemplate.set(LOGIN_FAIL_FLAG, "Fail", tokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
            }
            int restLoginTime = tokenProperties.getLoginTimeLimit() - loginFailTime;
            if (restLoginTime <= LOGIN_FAIL_TIP_TIME && restLoginTime > 0) {
                return ResultUtil.error("??????????????????????????????" + restLoginTime + "???????????????");
            } else if (restLoginTime <= 0) {
                return ResultUtil.error("????????????????????????????????????" + tokenProperties.getLoginAfterTime() + "???????????????");
            }
            return ResultUtil.error("?????????????????????");
        }

        String accessToken = SecurityUtil.getToken(user, saveLogin);
        return ResultUtil.data(accessToken);
    }

    @RequestMapping(value = "/smsLogin", method = RequestMethod.POST)
    @SystemLog(description = "????????????", type = LogType.LOGIN)
    @ApiOperation(value = "????????????")
    public Result<Object> smsLogin(@RequestParam String mobile,
                                   @RequestParam(required = false) Boolean saveLogin) {

        User user = userService.findByMobile(mobile);
        if (user == null) {
            throw new LegionException("??????????????????");
        }
        String accessToken = SecurityUtil.getToken(user, saveLogin);
        return ResultUtil.data(accessToken);
    }

    @RequestMapping(value = "/getLoginQRCode", method = RequestMethod.GET)
    @ApiOperation(value = "???????????????????????????")
    @RateLimiter(rate = 1, ipLimit = true)
    public Result<Object> getLoginQRCode() {

        String checkToken = IdUtil.simpleUUID();
        redisTemplate.set(checkToken, AppToBConstant.SCAN_LOGIN_STATUS_INIT, 2L, TimeUnit.MINUTES);
        String scheme = AppToBConstant.SCAN_LOGIN_SCHEME + "?checkToken=" + checkToken;
        return ResultUtil.data(scheme);
    }

    @RequestMapping(value = "/checkQRStatus/{checkToken}", method = RequestMethod.GET)
    @ApiOperation(value = "?????????????????????")
    public Result<Object> checkQRStatus(@PathVariable String checkToken) {

        String state = redisTemplate.get(checkToken);
        QRStatusVo statusVo = new QRStatusVo();
        if (StrUtil.isBlank(state)) {
            statusVo.setStatus(AppToBConstant.SCAN_LOGIN_STATUS_EXPIRED);
            return ResultUtil.data(statusVo);
        }

        if (AppToBConstant.SCAN_LOGIN_STATUS_SUCCESS.equals(state)) {
            String accessToken = redisTemplate.get(GET_NEW_TOKEN_PRE + ":" + checkToken);
            if (StrUtil.isBlank(accessToken)) {
                statusVo.setStatus(AppToBConstant.SCAN_LOGIN_STATUS_EXPIRED);
                return ResultUtil.data(statusVo);
            }
            statusVo.setAccessToken(accessToken);
        }
        statusVo.setStatus(state);
        return ResultUtil.data(statusVo);
    }

    @RequestMapping(value = "/scanLogin", method = RequestMethod.POST)
    @SystemLog(description = "????????????", type = LogType.LOGIN)
    @ApiOperation(value = "????????????")
    public Result<Object> scanLogin(@RequestParam String checkToken,
                                    @RequestParam(required = false, defaultValue = "false") Boolean cancel,
                                    @RequestParam(required = false) String accessToken) {

        String state = redisTemplate.get(checkToken);
        if (StrUtil.isBlank(state)) {
            return ResultUtil.error("??????????????????????????????");
        }
        if (AppToBConstant.SCAN_LOGIN_STATUS_INIT.equals(state)) {
            redisTemplate.set(checkToken, AppToBConstant.SCAN_LOGIN_STATUS_SCANNED, 2L, TimeUnit.MINUTES);
            return ResultUtil.data(AppToBConstant.SCAN_LOGIN_STATUS_SCANNED);
        } else if (AppToBConstant.SCAN_LOGIN_STATUS_SCANNED.equals(state)) {
            // ?????????
            if (cancel) {
                // ??????
                redisTemplate.set(checkToken, AppToBConstant.SCAN_LOGIN_STATUS_CANCEL, 2L, TimeUnit.MINUTES);
                return ResultUtil.data(AppToBConstant.SCAN_LOGIN_STATUS_CANCEL);
            }
            // ????????? ??????accessToken
            User user =  SecurityUtil.getUser();
            // ?????????Token
            String newAccessToken = SecurityUtil.getToken(user, true);
            redisTemplate.set(checkToken, AppToBConstant.SCAN_LOGIN_STATUS_SUCCESS, 2L, TimeUnit.MINUTES);
            redisTemplate.set(GET_NEW_TOKEN_PRE + ":" + checkToken, newAccessToken, 2L, TimeUnit.MINUTES);
            return ResultUtil.data(AppToBConstant.SCAN_LOGIN_STATUS_SUCCESS);
        }
        return ResultUtil.error("checkToken??????");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "????????????")
    public Result<Object> register(@Valid User u) {

        // ?????????????????????
        NameUtil.checkUserInfo(u.getUsername(), u.getMobile(), u.getEmail());

        // ??????Nickname
        String nickname = u.getMobile().substring(0, 3) + "****" + u.getMobile().substring(7, 11);
        // ????????????
        String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
        u.setPassword(encryptPass).setType(CommonConstant.USER_TYPE_NORMAL).setNickname(nickname);

         userService.save(u);
        User user =u;
        // ????????????
        List<Role> roleList = roleService.findByDefaultRole(true);
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                UserRole ur = new UserRole().setUserId(user.getId()).setRoleId(role.getId());
                userRoleService.save(ur);
            }
        }
        // ??????????????????????????????
        addMessage.addSendMessage(user.getId());

        return ResultUtil.data(user);
    }

    @RequestMapping(value = "/resetByMobile", method = RequestMethod.POST)
    @ApiOperation(value = "????????????????????????")
    public Result<Object> resetByMobile(@RequestParam String mobile,
                                        @RequestParam String password,
                                        @RequestParam String passStrength) {

        User u = userService.findByMobile(mobile);
        String encryptPass = new BCryptPasswordEncoder().encode(password);
        u.setPassword(encryptPass).setPassStrength(passStrength);
        userService.updateById(u);
        // ????????????
        redisTemplate.delete(USER + u.getUsername());
        return ResultUtil.success("??????????????????");
    }

    @RequestMapping(value = "/resetByEmail", method = RequestMethod.POST)
    @ApiOperation(value = "????????????????????????")
    public Result<Object> resetByEmail(@RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam String passStrength) {

        User u = userService.findByEmail(email);

        // ??????DEMO??????
        if ("test".equals(u.getUsername()) || "test2".equals(u.getUsername())) {
            return ResultUtil.error("?????????????????????????????????");
        }

        String encryptPass = new BCryptPasswordEncoder().encode(password);
        u.setPassword(encryptPass);
        u.setPassStrength(passStrength);
        userService.updateById(u);
        // ????????????
        redisTemplate.delete("user::" + u.getUsername());
        return ResultUtil.success("??????????????????");
    }

}
