package com.esmooc.legion.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author Daimao
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);


}
