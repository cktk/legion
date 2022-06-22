package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.mapper.RoleDepartmentMapper;
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
public class RoleDepartmentServiceImpl extends ServiceImpl<RoleDepartmentMapper , RoleDepartment> implements RoleDepartmentService {

    @Autowired
    private RoleDepartmentMapper roleDepartmentMapper;


    @Override
    public List<RoleDepartment> findByRoleId(String roleId) {

        return roleDepartmentMapper.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(String roleId) {

        roleDepartmentMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByDepartmentId(String departmentId) {

        roleDepartmentMapper.deleteByDepartmentId(departmentId);
    }
}
