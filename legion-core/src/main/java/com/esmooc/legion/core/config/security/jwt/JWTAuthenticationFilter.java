package com.esmooc.legion.core.config.security.jwt;

import com.esmooc.legion.core.common.constant.SecurityConstant;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResponseUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.TokenMember;
import com.esmooc.legion.core.common.vo.TokenUser;
import com.esmooc.legion.core.config.properties.LegionAppTokenProperties;
import com.esmooc.legion.core.config.properties.LegionTokenProperties;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Daimao
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private LegionTokenProperties tokenProperties;

    private LegionAppTokenProperties appTokenProperties;

    private RedisTemplateHelper redisTemplate;

    private SecurityUtil securityUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   LegionTokenProperties tokenProperties,
                                   LegionAppTokenProperties appTokenProperties,
                                   RedisTemplateHelper redisTemplate, SecurityUtil securityUtil) {
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
        this.appTokenProperties = appTokenProperties;
        this.redisTemplate = redisTemplate;
        this.securityUtil = securityUtil;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstant.HEADER);
        if (StrUtil.isBlank(header)) {
            header = request.getParameter(SecurityConstant.HEADER);
        }
        String appHeader = request.getHeader(SecurityConstant.APP_HEADER);
        if (StrUtil.isBlank(appHeader)) {
            appHeader = request.getParameter(SecurityConstant.APP_HEADER);
        }
        Boolean notValid = (StrUtil.isBlank(header) || (!tokenProperties.getRedis() && !header.startsWith(SecurityConstant.TOKEN_SPLIT)))
                && StrUtil.isBlank(appHeader);
        if (notValid) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = null;
            if (StrUtil.isNotBlank(header)) {
                authentication = getAuthentication(header, response);
            } else {
                authentication = getAppAuthentication(appHeader, response);
            }
            if (authentication == null) {
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.warn(e.toString());
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {

        // 用户名
        String username = null;
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (tokenProperties.getRedis()) {
            // redis
            String v = redisTemplate.get(SecurityConstant.TOKEN_PRE + header);
            if (StrUtil.isBlank(v)) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 401, "登录已失效，请重新登录"));
                return null;
            }
            TokenUser user = new Gson().fromJson(v, TokenUser.class);
            username = user.getUsername();
            if (tokenProperties.getStorePerms()) {
                // 缓存了权限
                for (String ga : user.getPermissions()) {
                    authorities.add(new SimpleGrantedAuthority(ga));
                }
            } else {
                // 未缓存 读取权限数据
                authorities = securityUtil.getCurrUserPerms(username);
            }
            if (!user.getSaveLogin()) {
                // 若未保存登录状态重新设置失效时间
                redisTemplate.set(SecurityConstant.USER_TOKEN + username, header, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
                redisTemplate.set(SecurityConstant.TOKEN_PRE + header, v, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
            }
        } else {
            // JWT
            try {
                // 解析token
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                        .parseClaimsJws(header.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();

                // 获取用户名
                username = claims.getSubject();
                // JWT不缓存权限 读取权限数据 避免JWT长度过长
                authorities = securityUtil.getCurrUserPerms(username);
            } catch (ExpiredJwtException e) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 401, "登录已失效，请重新登录"));
            } catch (Exception e) {
                log.error(e.toString());
                ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "解析token错误"));
            }
        }

        if (StrUtil.isNotBlank(username)) {
            // 踩坑提醒 此处password不能为null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }

    private UsernamePasswordAuthenticationToken getAppAuthentication(String appHeader, HttpServletResponse response) {

        // 用户名
        String username = null;

        String v = redisTemplate.get(SecurityConstant.TOKEN_MEMBER_PRE + appHeader);
        if (StrUtil.isBlank(v)) {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 401, "会员登录已失效，请重新登录"));
            return null;
        }
        TokenMember member = new Gson().fromJson(v, TokenMember.class);
        username = member.getUsername();
        // 权限
        List<GrantedAuthority> authorities = securityUtil.getCurrMemberPerms(username);

        // 重新设置失效时间
        redisTemplate.set(SecurityConstant.MEMBER_TOKEN + username + ":" + member.getPlatform(), appHeader, appTokenProperties.getTokenExpireTime(), TimeUnit.DAYS);
        redisTemplate.set(SecurityConstant.TOKEN_MEMBER_PRE + appHeader, v, appTokenProperties.getTokenExpireTime(), TimeUnit.DAYS);

        if (StrUtil.isNotBlank(username)) {
            // 踩坑提醒 此处password不能为null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }
}
