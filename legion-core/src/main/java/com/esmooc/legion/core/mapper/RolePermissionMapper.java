package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.esmooc.legion.core.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;



import java.util.List;

/**
 * 角色权限数据处理层
 *
 * @author DaiMao
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 通过permissionId获取
     *
     * @param permissionId
     * @return
     */
    @Select("select * from t_role_permission where permission_id=#{permissionId}")
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId获取
     *
     * @param roleId
     */
    @Select("select * from t_role_permission where role_id=#{roleId}")
    List<RolePermission> findByRoleId(String roleId);

    /**
     * 通过roleId删除
     *
     * @param roleId
     */
    @Delete("delete from t_role_permission r where r.roleId = #{roleId}")
    Boolean deleteByRoleId(String roleId);
}
