package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.Permission;

import com.esmooc.legion.core.mapper.PermissionMapper;
import com.esmooc.legion.core.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl   extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {

        return permissionMapper.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status) {

        return permissionMapper.findByTypeAndStatusOrderBySortOrder(type, status);
    }

    @Override
    public List<Permission> findByTitle(String title) {

        return permissionMapper.findByTitle(title);
    }

    @Override
    public List<Permission> findByTitleLikeOrderBySortOrder(String title) {

        return permissionMapper.findByTitleLikeOrderBySortOrder(title);
    }
}
