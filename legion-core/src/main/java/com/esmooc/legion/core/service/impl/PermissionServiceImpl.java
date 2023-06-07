package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getParentId, parentId).orderBy(true,true,Permission::getSortOrder );
        return this.list(queryWrapper);
    }

    @Override
    public List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status) {
        return  this.list(new QueryWrapper<Permission>().lambda().eq(Permission::getType, type)
                .eq(Permission::getStatus,status).orderBy(true,true,Permission::getSortOrder));
    }

    @Override
    public List<Permission> findByTitle(String title) {

        return  this.list(new QueryWrapper<Permission>().lambda()
                .eq(Permission::getTitle,title));

    }

    @Override
    public List<Permission> findByTitleLikeOrderBySortOrder(String title) {

        return  this.list(new QueryWrapper<Permission>().lambda()
                .like(Permission::getTitle,title).orderBy(true,true, Permission::getTitle));

    }
}
