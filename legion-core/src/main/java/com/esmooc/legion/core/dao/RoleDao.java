package com.esmooc.legion.core.dao;

import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.core.entity.Role;

import java.util.List;

/**
 * 角色数据处理层
 * @author DaiMao
 */
public interface RoleDao extends LegionBaseDao<Role, String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
