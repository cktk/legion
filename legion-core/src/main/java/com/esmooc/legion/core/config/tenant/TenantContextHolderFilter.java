package com.esmooc.legion.core.config.tenant;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String headerTenantId = request.getHeader(CommonConstant.TENANT_ID);
        String paramTenantId = request.getParameter(CommonConstant.TENANT_ID);
        Long userTenantId = null;
        try {
            userTenantId = SecurityUtil.getUser().getTenantId();
        } catch (Exception e) {

        }
        log.debug("获取header中的租户ID为:{}", headerTenantId);
        if (userTenantId!=null) {
            TenantContextHolder.setTenantId(userTenantId);
        } else if (StrUtil.isNotBlank(headerTenantId)) {
            TenantContextHolder.setTenantId(Long.parseLong(headerTenantId));
        } else if (StrUtil.isNotBlank(paramTenantId)) {
            TenantContextHolder.setTenantId(Long.parseLong(paramTenantId));
        } else {
            TenantContextHolder.setTenantId(CommonConstant.TENANT_ID_0);
        }

        filterChain.doFilter(request, response);
        TenantContextHolder.clear();
    }

}
