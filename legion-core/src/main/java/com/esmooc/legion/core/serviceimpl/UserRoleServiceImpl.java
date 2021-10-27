package com.esmooc.legion.core.serviceimpl;

import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.dao.UserDao;
import com.esmooc.legion.core.dao.UserRoleDao;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.UserRole;
import com.esmooc.legion.core.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserRoleDao getRepository() {
        return userRoleDao;
    }

    @Override
    public List<UserRole> findByRoleId(String roleId) {
        return userRoleDao.findByRoleId(roleId);
    }

    @Override
    public List<User> findUserByRoleId(String roleId) {

        List<UserRole> userRoleList = userRoleDao.findByRoleId(roleId);
        List<User> list = new ArrayList<>();
        for (UserRole ur : userRoleList) {
            User u = userDao.findById(ur.getUserId()).orElse(null);
            if (u != null && CommonConstant.USER_STATUS_NORMAL.equals(u.getStatus())) {
                list.add(u);
            }
        }
        return list;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRoleDao.deleteByUserId(userId);
    }
}
