package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.RoleDepartment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色部门数据处理层
 *
 * @author Daimao
 */
@Mapper
public interface RoleDepartmentMapper extends BaseMapper<RoleDepartment> {

    /**
     * 通过roleId获取
     * @param roleId
     * @return
     */
    @Select("select * from t_role_department where role_id=#{roleId} ")
    List<RoleDepartment> findByRoleId(String roleId);

    /**
     * 通过角色id删除
     * @param roleId
     */
    @Delete("delete  from  t_role_department where role_id=#{roleId} ")
    void deleteByRoleId(String roleId);

    /**
     * 通过角色id删除
     * @param departmentId
     */
    @Delete("delete from t_role_department r where r.department_id = #{departmentId} ")
    void deleteByDepartmentId(String departmentId);
}
