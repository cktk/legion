package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.UserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author Daimao
 */
@CacheConfig(cacheNames = "userRole")
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    List<Role> findByUserId(String userId);

    /**
     * 通过用户id获取用户角色关联的部门数据
     * @param userId
     * @return
     */
    List<String> findDepIdsByUserId(String userId);
}
