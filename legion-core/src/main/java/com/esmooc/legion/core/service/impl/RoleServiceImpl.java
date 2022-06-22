package com.esmooc.legion.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.entity.RolePermission;
import com.esmooc.legion.core.mapper.RoleMapper;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.mapper.RolePermissionMapper;
import com.esmooc.legion.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role > implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findByDefaultRole(Boolean defaultRole) {
        return roleMapper.findByDefaultRole(defaultRole);
    }

    @Override
    public IPage<Role> findByCondition(String key, PageVo pageable) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(Role::getName,key).or()
                .like(Role::getDefaultRole,key).or()
                .like(Role::getDataType,key).or()
                .like(Role::getDescription,key).or();
        return  this.page(PageUtil.initMpPage(pageable),queryWrapper);
    }
}
