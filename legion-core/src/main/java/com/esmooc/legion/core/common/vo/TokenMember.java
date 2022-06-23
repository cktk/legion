package com.esmooc.legion.core.common.vo;

import com.esmooc.legion.core.entity.AppMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author DaiMao
 */
@Data
@AllArgsConstructor
public class TokenMember implements UserDetails, Serializable {

    private String id;

    private String username;

    private String nickname;

    private String mobile;

    private String email;

    private Integer type;

    private String permissions;

    private Integer platform;

    public TokenMember(AppMember appMember, Integer platform) {

        this.id = appMember.getId();
        this.username = appMember.getUsername();
        this.nickname = appMember.getNickname();
        this.mobile = appMember.getMobile();
        this.email = appMember.getEmail();
        this.type = appMember.getType();
        this.permissions = appMember.getPermissions();

        this.platform = platform;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

