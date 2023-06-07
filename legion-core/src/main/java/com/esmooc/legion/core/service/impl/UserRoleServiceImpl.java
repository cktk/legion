package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.mapper.UserRoleMapper;
import com.esmooc.legion.core.service.UserRoleService;
import com.esmooc.legion.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl  extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Autowired
    private UserService userService;



    @Override
    public List<UserRole> findByRoleId(String roleId) {
        return userRoleMapper.findByRoleId(roleId);
    }

    @Override
    public List<User> findUserByRoleId(String roleId) {

        List<UserRole> userRoleList = userRoleMapper.findByRoleId(roleId);
        List<User> list = new ArrayList<>();
        for (UserRole ur : userRoleList) {
            User u = userService.getById(ur.getUserId());
            if (u != null && CommonConstant.USER_STATUS_NORMAL.equals(u.getStatus())) {
                list.add(u);
            }
        }
        return list;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRoleMapper.deleteByUserId(userId);
    }
}
