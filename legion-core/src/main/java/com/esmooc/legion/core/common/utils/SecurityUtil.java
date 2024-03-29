package com.esmooc.legion.core.common.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SecurityConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.vo.TokenMember;
import com.esmooc.legion.core.common.vo.TokenUser;
import com.esmooc.legion.core.config.datascope.DataScopeTypeEnum;
import com.esmooc.legion.core.config.properties.LegionAppTokenProperties;
import com.esmooc.legion.core.config.properties.LegionTokenProperties;
import com.esmooc.legion.core.entity.AppMember;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.vo.PermissionDTO;
import com.esmooc.legion.core.service.DepartmentService;
import com.esmooc.legion.core.service.IUserRoleService;
import com.esmooc.legion.core.service.MemberService;
import com.esmooc.legion.core.service.UserService;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author DaiMao
 */
public class SecurityUtil {

    // 声明对象
    private static UserService userService = SpringUtil.getBean(UserService.class);
    private static LegionTokenProperties tokenProperties = SpringUtil.getBean(LegionTokenProperties.class);
    private static LegionAppTokenProperties appTokenProperties = SpringUtil.getBean(LegionAppTokenProperties.class);
    private static MemberService memberService = SpringUtil.getBean(MemberService.class);
    private static IUserRoleService iUserRoleService = SpringUtil.getBean(IUserRoleService.class);
    private static DepartmentService departmentService = SpringUtil.getBean(DepartmentService.class);
    private static RedisTemplateHelper redisTemplate = SpringUtil.getBean(RedisTemplateHelper.class);


    /**
     * 获取当前登录用户部分基本信息
     *
     * @return
     */
    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new LegionException("未检测到登录用户");
        }
        return userService.findByUsername(authentication.getName());
    }


    public static User checkUserPassword(String username, String password) {

        User user;
        // 校验用户名
        if (NameUtil.mobile(username)) {
            user = userService.findByEmail(username);
        } else if (NameUtil.email(username)) {
            user = userService.findByEmail(username);
        } else {
            user = userService.findByUsername(username);
        }
        if (user == null) {
            return null;
        }
        // 校验密码
        Boolean isValid = new BCryptPasswordEncoder().matches(password, user.getPassword());
        if (!isValid) {
            return null;
        }
        return user;
    }

    public static String getToken(String username, Boolean saveLogin) {

        if (StrUtil.isBlank(username)) {
            throw new LegionException("username不能为空");
        }
        User user = userService.findByUsername(username);
        return getToken(user, saveLogin);
    }

    public static String getToken(User user, Boolean saveLogin) {

        if (user == null) {
            throw new LegionException("user不能为空");
        }
        if (CommonConstant.USER_STATUS_LOCK.equals(user.getStatus())) {
            throw new LegionException("账户被禁用，请联系管理员");
        }
        Boolean saved = false;
        if (saveLogin == null || saveLogin) {
            saved = true;
            if (!tokenProperties.getRedis()) {
                tokenProperties.setTokenExpireTime(tokenProperties.getSaveLoginTime() * 60 * 24);
            }
        }
        // 生成token
        String token;
        TokenUser tokenUser;
        if (tokenProperties.getRedis()) {
            // redis
            token = IdUtil.simpleUUID();
            tokenUser = new TokenUser(user, tokenProperties.getStorePerms(), saved);
            // 单设备登录 之前的token失效
            if (tokenProperties.getSdl()) {
                String oldToken = redisTemplate.get(SecurityConstant.USER_TOKEN + user.getUsername());
                if (StrUtil.isNotBlank(oldToken)) {
                    redisTemplate.delete(SecurityConstant.TOKEN_PRE + oldToken);
                }
            }
            // 是否记住账号/保存登录
            if (saved) {
                redisTemplate.set(SecurityConstant.USER_TOKEN + user.getUsername(), token, tokenProperties.getSaveLoginTime(), TimeUnit.DAYS);
                redisTemplate.set(SecurityConstant.TOKEN_PRE + token, new Gson().toJson(tokenUser), tokenProperties.getSaveLoginTime(), TimeUnit.DAYS);
            } else {
                redisTemplate.set(SecurityConstant.USER_TOKEN + user.getUsername(), token, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
                redisTemplate.set(SecurityConstant.TOKEN_PRE + token, new Gson().toJson(tokenUser), tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
            }
        } else {
            // JWT不缓存权限 避免JWT长度过长
            tokenUser = new TokenUser(user, false, null);
            token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
                    // 主题 放入用户信息
                    .setSubject(new Gson().toJson(tokenUser))
                    //失效时间
                    .setExpiration(new Date(System.currentTimeMillis() + tokenProperties.getTokenExpireTime() * 60 * 1000))
                    //签名算法和密钥
                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                    .compact();
        }
        // 记录日志使用
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tokenUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }



    /**
     * 获取当前用户数据权限 null代表具有所有权限 包含值为-1的数据代表无任何权限
     */
    public static List<String> getDeparmentIds() {

        List<String> deparmentIds = new ArrayList<>();
        User u = getUser();
        // 读取缓存

        // 当前用户拥有角色
        List<Role> roles = iUserRoleService.findByUserId(u.getId());
        // 判断有无全部数据的角色
        Boolean flagAll = false;
        for (Role r : roles) {
            if (r.getDataType() == null || r.getDataType().equals(DataScopeTypeEnum.ALL.getType())) {
                flagAll = true;
                break;
            }
        }
        // 包含全部权限返回null
        if (flagAll) {
            return null;
        }
        // 每个角色判断 求并集
        for (Role r : roles) {
            if (r.getDataType().equals(DataScopeTypeEnum.OWN_CHILD_LEVEL.getType())) {
                // 本部门及以下
                if (StrUtil.isBlank(u.getDepartmentId())) {
                    // 用户无部门
//                    deparmentIds.add("-1");
                } else {
                    // 递归获取自己与子级
                    List<String> ids = new ArrayList<>();
                    getDepRecursion(u.getDepartmentId(), ids);
                    deparmentIds.addAll(ids);
                }
            } else if (r.getDataType().equals(DataScopeTypeEnum.OWN_LEVEL.getType())) {
                // 本部门
                if (StrUtil.isBlank(u.getDepartmentId())) {
                    // 用户无部门
//                    deparmentIds.add("-1");
                } else {
                    deparmentIds.add(u.getDepartmentId());
                }
            } else if (r.getDataType().equals(DataScopeTypeEnum.CUSTOM.getType())) {
                // 自定义
                List<String> depIds = iUserRoleService.findDepIdsByUserId(u.getId());
                if (depIds == null || depIds.size() == 0) {
//                    deparmentIds.add("-1");
                } else {
                    deparmentIds.addAll(depIds);
                }
            }
        }
        // 去重
        LinkedHashSet<String> set = new LinkedHashSet<>(deparmentIds.size());
        set.addAll(deparmentIds);
        deparmentIds.clear();
        deparmentIds.addAll(set);
        // 缓存
        return deparmentIds;
    }

    private  static void getDepRecursion(String departmentId, List<String> ids) {

        Department department = departmentService.getById(departmentId);
        ids.add(department.getId());
        if (department.getIsParent() && department.getIsParent()) {
            // 获取其下级
            List<Department> departments = departmentService.findByParentIdAndStatusOrderBySortOrder(departmentId, CommonConstant.STATUS_NORMAL);
            departments.forEach(d -> {
                getDepRecursion(d.getId(), ids);
            });
        }
    }

    /**
     * 通过用户名获取用户拥有权限
     *
     * @param username
     */
    public static List<GrantedAuthority> getCurrUserPerms(String username) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.findByUsername(username);
        if (user == null || user.getPermissions() == null || user.getPermissions().isEmpty()) {
            return authorities;
        }
        for (PermissionDTO p : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(p.getTitle()));
        }
        return authorities;
    }

    /**
     * -------------------App ToC-------------------------
     */

    public static String getAppToken(String username, Integer platform) {

        if (StrUtil.isBlank(username)) {
            throw new LegionException("username不能为空");
        }
        AppMember appMember = memberService.findByUsername(username);
        return getAppToken(appMember, platform);
    }

    public static String getAppToken(AppMember appMember, Integer platform) {

        if (appMember == null) {
            throw new LegionException("member不能为空");
        }
        if (CommonConstant.USER_STATUS_LOCK.equals(appMember.getStatus())) {
            throw new LegionException("账户被禁用，请联系管理员");
        }
        // 生成token
        String token;
        TokenMember tokenMember;
        if (appTokenProperties.getRedis()) {
            // redis
            token = IdUtil.simpleUUID();
            tokenMember = new TokenMember(appMember, platform);
            String key = SecurityConstant.MEMBER_TOKEN + tokenMember.getUsername() + ":" + platform;
            // 单平台登录 之前的token失效
            if (appTokenProperties.getSpl()) {
                String oldToken = redisTemplate.get(key);
                if (StrUtil.isNotBlank(oldToken)) {
                    redisTemplate.delete(SecurityConstant.TOKEN_MEMBER_PRE + oldToken);
                }
            }
            redisTemplate.set(key, token, appTokenProperties.getTokenExpireTime(), TimeUnit.DAYS);
            redisTemplate.set(SecurityConstant.TOKEN_MEMBER_PRE + token, new Gson().toJson(tokenMember), appTokenProperties.getTokenExpireTime(), TimeUnit.DAYS);
        } else {
            // JWT
            tokenMember = new TokenMember(appMember, platform);
            token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
                    // 主题 放入会员信息
                    .setSubject(new Gson().toJson(tokenMember))
                    // 失效时间
                    .setExpiration(new Date(System.currentTimeMillis() + appTokenProperties.getTokenExpireTime() * 60 * 1000))
                    // 签名算法和密钥
                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                    .compact();
        }
        // 记录日志使用
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(tokenMember, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }

    /**
     * 获取当前登录会员信息 包含所有
     *
     * @return
     */
    public static AppMember getCurrMember() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new LegionException("未检测到登录会员");
        }
        return memberService.findByUsername(authentication.getName());
    }

}
