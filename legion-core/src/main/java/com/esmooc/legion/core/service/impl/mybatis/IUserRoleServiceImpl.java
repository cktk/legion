package com.esmooc.legion.core.service.impl.mybatis;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.dao.mapper.UserRoleMapper;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.service.mybatis.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daimao
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }

    @Override
    public List<String> findDepIdsByUserId(String userId) {

        return userRoleMapper.findDepIdsByUserId(userId);
    }
}
