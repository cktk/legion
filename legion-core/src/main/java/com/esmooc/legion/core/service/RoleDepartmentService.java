package com.esmooc.legion.core.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.entity.RoleDepartment;

import java.util.List;

/**
 * 角色部门接口
 *
 * @author DaiMao
 */
public interface RoleDepartmentService extends LegionBaseService<RoleDepartment, String> {

    /**
     * 通过roleId获取
     *
     * @param roleId
     * @return
     */
    List<RoleDepartment> findByRoleId(String roleId);

    /**
     * 通过角色id删除
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     * 通过角色id删除
     *
     * @param departmentId
     */
    void deleteByDepartmentId(String departmentId);
}
