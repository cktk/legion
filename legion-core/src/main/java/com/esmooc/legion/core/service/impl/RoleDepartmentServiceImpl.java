package com.esmooc.legion.core.service.impl;

import com.esmooc.legion.core.dao.RoleDepartmentDao;
import com.esmooc.legion.core.entity.RoleDepartment;
import com.esmooc.legion.core.service.RoleDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色部门接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class RoleDepartmentServiceImpl implements RoleDepartmentService {

    @Autowired
    private RoleDepartmentDao roleDepartmentDao;

    @Override
    public RoleDepartmentDao getRepository() {
        return roleDepartmentDao;
    }

    @Override
    public List<RoleDepartment> findByRoleId(String roleId) {

        return roleDepartmentDao.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(String roleId) {

        roleDepartmentDao.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByDepartmentId(String departmentId) {

        roleDepartmentDao.deleteByDepartmentId(departmentId);
    }
}
