package com.esmooc.legion.core.dao;

import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.core.entity.RoleDepartment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 角色部门数据处理层
 *
 * @author DaiMao
 */
public interface RoleDepartmentDao extends LegionBaseDao<RoleDepartment, String> {

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
    @Modifying
    @Query("delete from RoleDepartment r where r.roleId = ?1")
    void deleteByRoleId(String roleId);

    /**
     * 通过角色id删除
     *
     * @param departmentId
     */
    @Modifying
    @Query("delete from RoleDepartment r where r.departmentId = ?1")
    void deleteByDepartmentId(String departmentId);
}
