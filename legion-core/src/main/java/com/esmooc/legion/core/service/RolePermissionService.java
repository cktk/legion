package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.RolePermission;

import java.util.List;

/**
 * 角色权限接口
 * @author Daimao
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId获取
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(String roleId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}
