package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.RoleDepartment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色部门数据处理层
 *
 * @author DaiMao
 */
public interface RoleDepartmentMapper extends BaseMapper<RoleDepartment> {

    /**
     * 通过roleId获取
     *
     * @param roleId
     * @return
     */
    @Select("select * from t_role_department  where role_id =#{roleId}")
    List<RoleDepartment> findByRoleId(String roleId);

    /**
     * 通过角色id删除
     *
     * @param roleId
     */
    @Delete("delete from RoleDepartment r where r.roleId = ?1")
    boolean deleteByRoleId(String roleId);

    /**
     * 通过角色id删除
     *
     * @param departmentId
     */
    @Delete("delete from RoleDepartment r where r.departmentId = ?1")
    boolean deleteByDepartmentId(String departmentId);
}
