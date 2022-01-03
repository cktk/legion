package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.entity.Permission;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.vo.PermissionDTO;
import com.esmooc.legion.core.entity.vo.RoleDTO;
import com.esmooc.legion.core.mapper.PermissionMapper;
import com.esmooc.legion.core.mapper.UserMapper;
import com.esmooc.legion.core.mapper.UserRoleMapper;
import com.esmooc.legion.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByUsername(String username) {

        User user = userMapper.findByUsername(username);
        return userToDTO(user);
    }

    @Override
    public User findByMobile(String mobile) {

        User user = userMapper.findByMobile(mobile);
        return userToDTO(user);
    }

    @Override
    public User findByEmail(String email) {

        User user = userMapper.findByEmail(email);
        return userToDTO(user);
    }

    public User userToDTO(User user) {

        if (user == null) {
            return null;
        }
        // 关联角色
        List<Role> roleList = userRoleMapper.findByUserId(user.getId());
        List<RoleDTO> roleDTOList = roleList.stream().map(e -> {
            return new RoleDTO().setId(e.getId()).setName(e.getName());
        }).collect(Collectors.toList());
        user.setRoles(roleDTOList);
        // 关联权限菜单
        List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
        List<PermissionDTO> permissionDTOList = permissionList.stream()
                .filter(e -> CommonConstant.PERMISSION_OPERATION.equals(e.getType()))
                .map(e -> {
                    return new PermissionDTO().setTitle(e.getTitle()).setPath(e.getPath());
                }).collect(Collectors.toList());
        user.setPermissions(permissionDTOList);
        return user;
    }


    @Override
    public List<User> findByDepartmentId(String departmentId) {

        return userMapper.findByDepartmentId(departmentId);
    }

    @Override
    public List<User> findByUsernameLikeAndStatus(String username, Integer status) {

        return userMapper.findByUsernameLikeAndStatus(username, status);
    }

    @Override
    public void updateDepartmentTitle(String departmentId, String departmentTitle) {

        userMapper.updateDepartmentTitle(departmentId, departmentTitle);
    }
}
