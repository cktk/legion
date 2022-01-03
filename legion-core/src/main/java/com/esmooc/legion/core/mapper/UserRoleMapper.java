package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色数据处理层
 *
 * @author Daimao
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过roleId查找
     *
     * @param roleId
     * @return
     */
    @Select("select * from t_user_role  where role_id=#{roleId} ")
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     *
     * @param userId
     */
    @Delete("delete from t_user_role u where u.user_id = #{userId} ")
    void deleteByUserId(String userId);

    /**
     * 通过用户id获取
     *
     * @param userId
     * @return
     */
    @Select("  SELECT r.id id, name, r.data_type" +
            "    FROM t_user_role ur" +
            "    LEFT JOIN t_role r" +
            "    ON ur.role_id = r.id" +
            "    WHERE user_Id = #{userId}")
    List<Role> findByUserId(@Param("userId") String userId);

    /**
     * 通过用户id获取用户角色关联的部门数据
     *
     * @param userId
     * @return
     */
    @Select("    SELECT DISTINCT rd.department_id" +
            "    FROM t_role_department rd" +
            "    WHERE role_id IN" +
            "            ( SELECT ur.role_id FROM t_user_role ur WHERE ur.user_id = #{userId} )")
    List<String> findDepIdsByUserId(@Param("userId") String userId);


}
