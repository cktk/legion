package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.mapper.RoleMapper;
import com.esmooc.legion.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByDefaultRole(Boolean defaultRole) {
        return roleMapper.findByDefaultRole(defaultRole);
    }

}
