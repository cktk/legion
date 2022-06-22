package com.esmooc.legion.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.entity.Role;

import java.util.List;

/**
 * 角色接口
 *
 * @author DaiMao
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取默认角色
     *
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);

    /**
     * 分页获取
     *
     * @param key
     * @param pageable
     * @return
     */
    IPage<Role> findByCondition(String key, PageVo pageable);
}
