package com.esmooc.legion.core.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl  extends ServiceImpl<UserMapper ,User > implements UserService {

    @Autowired
    private UserMapper usermapper;


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;



    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,username);
        return userToDTO(this.getOne(queryWrapper));
    }

    @Override
    public User findByMobile(String mobile) {

        User user = usermapper.findByMobile(mobile);
        return userToDTO(user);
    }

    @Override
    public User findByEmail(String email) {

        User user = usermapper.findByEmail(email);
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
    public IPage<User> findByCondition(User user, SearchVo searchVo, PageVo pageable) {


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StrUtil.isNotBlank(user.getId()),User::getId,user.getId())
                .like(StrUtil.isNotBlank(user.getUsername()),User::getUsername,user.getUsername()).or()
                .like(StrUtil.isNotBlank(user.getNickname()),User::getNickname,user.getNickname()).or()
                .like(StrUtil.isNotBlank(user.getMobile()),User::getMobile,user.getMobile()).or()
                .like(StrUtil.isNotBlank(user.getEmail()),User::getEmail,user.getEmail()).or()
                .like(StrUtil.isNotBlank(user.getId()),User::getId,user.getId())
                .eq(user.getDepartmentId()==null,User::getDepartmentId,user.getDepartmentId())
                .eq(user.getType()!=null,User::getType,user.getType())
                .eq(user.getStatus()!=null,User::getStatus,user.getStatus())
                .eq(StrUtil.isNotBlank(user.getSex()),User::getSex,user.getSex());

        // 创建时间
        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(User::getCreateTime,start,end);
        }



        // 数据权限
        List<String> depIds = SecurityUtil.getDeparmentIds();
        if (depIds != null && depIds.size() > 0) {
            queryWrapper.lambda().in(User::getDepartmentId,depIds);
        }


        return this.page(PageUtil.initMpPage(pageable),queryWrapper);
    }

    @Override
    public List<User> findByDepartmentId(String departmentId) {

        return usermapper.findByDepartmentId(departmentId);
    }

    @Override
    public List<User> findByUsernameLikeAndStatus(String username, Integer status) {

        return usermapper.findByUsernameLikeAndStatus(username, status);
    }

    @Override
    public void updateDepartmentTitle(String departmentId, String departmentTitle) {

        usermapper.updateDepartmentTitle(departmentId, departmentTitle);
    }
}
