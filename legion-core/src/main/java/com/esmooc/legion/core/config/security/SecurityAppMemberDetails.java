package com.esmooc.legion.core.config.security;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.MemberConstant;
import com.esmooc.legion.core.entity.AppMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DaiMao
 */
@Slf4j
public class SecurityAppMemberDetails extends AppMember implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String permissions;

    public SecurityAppMemberDetails(AppMember appMember) {

        if (appMember != null) {
            this.setUsername(appMember.getUsername());
            this.setPassword(appMember.getPassword());
            this.setStatus(appMember.getStatus());

            this.permissions = appMember.getPermissions();
        }
    }

    /**
     * 添加用户拥有的权限和角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (StrUtil.isBlank(permissions)) {
            return authorityList;
        }
        String[] as = permissions.split(",");
        for (String a : as) {
            authorityList.add(new SimpleGrantedAuthority(a));
        }
        return authorityList;
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return MemberConstant.MEMBER_STATUS_LOCK.equals(this.getStatus()) ? false : true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {

        return MemberConstant.MEMBER_STATUS_NORMAL.equals(this.getStatus()) ? true : false;
    }
}
