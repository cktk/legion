package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.mapper.RolePermissionMapper;
import com.esmooc.legion.core.entity.RolePermission;
import com.esmooc.legion.core.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class RolePermissionServiceImpl  extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {

        return rolePermissionMapper.findByPermissionId(permissionId);
    }

    @Override
    public List<RolePermission> findByRoleId(String roleId) {

        return rolePermissionMapper.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(String roleId) {

        rolePermissionMapper.deleteByRoleId(roleId);
    }
}
