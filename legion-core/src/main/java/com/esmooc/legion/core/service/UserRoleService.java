package com.esmooc.legion.core.service;


import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author DaiMao
 */
public interface UserRoleService extends LegionBaseService<UserRole, String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 通过roleId查找用户
     * @param roleId
     * @return
     */
    List<User> findUserByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
