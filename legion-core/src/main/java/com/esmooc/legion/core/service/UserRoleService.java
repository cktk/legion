package com.esmooc.legion.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author Daimao
 */
public interface UserRoleService extends IService<UserRole> {

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
    boolean deleteByUserId(String userId);
}
