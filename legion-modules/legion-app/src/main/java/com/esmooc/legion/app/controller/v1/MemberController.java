package com.esmooc.legion.app.controller.v1;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.NameUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.utils.SnowFlakeUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Member;
import com.esmooc.legion.core.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "会员接口")
@RequestMapping(value = "/legion/app/v1/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private EntityManager entityManager;

    @RequestMapping(value = "/quickLogin", method = RequestMethod.POST)
    @ApiOperation(value = "手机号快捷登录/注册")
    @SystemLog(description = "快捷登录", type = LogType.MEMBER_LOGIN)
    public Result<Object> quickLogin(@RequestParam String mobile,
                                     @RequestParam(required = false) String inviteCode,
                                     @RequestParam(required = false, defaultValue = "-1") Integer platform) {

        if (!NameUtil.mobile(mobile)) {
            return ResultUtil.error("11位手机号格式不正确");
        }
        Boolean isNew = false;
        String inviteBy = null;
        if (StrUtil.isNotBlank(inviteCode)) {
            Long uid = Long.parseLong(inviteCode, 32);
            Member inviter = memberService.findByUsername(uid.toString());
            if (inviter == null) {
                return ResultUtil.error("邀请码不正确");
            }
            inviteBy = inviter.getUsername();
        }
        Member member = memberService.findByMobile(mobile);
        if (member == null) {
            // 先注册
            String nickname = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
            Long uid = SnowFlakeUtil.nextId();
            member = new Member().setUsername(uid.toString()).setInviteCode(Long.toString(uid, 32).toUpperCase()).
                    setMobile(mobile).setNickname(nickname).setPlatform(platform).setInviteBy(inviteBy);
            memberService.save(member);
            isNew = true;
        }
        // 登录
        String appToken = securityUtil.getAppToken(member.getUsername(), platform);
        Map<String, Object> result = new HashMap<>(16);
        result.put("isNew", isNew);
        result.put("token", appToken);
        return ResultUtil.data(result);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登录会员信息接口")
    public Result<Member> getUserInfo() {

        Member member = securityUtil.getCurrMember();
        // 清除持久上下文环境 避免后面语句导致持久化
        entityManager.detach(member);
        member.setPassword(null);
        return ResultUtil.data(member);
    }
}
