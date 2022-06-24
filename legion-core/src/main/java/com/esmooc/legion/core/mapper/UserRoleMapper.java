package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色数据处理层
 *
 * @author DaiMao
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过roleId查找
     *
     * @param roleId
     * @return
     */
    @Select("select * from t_user_role where role_id =#{roleId} ")
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     *
     * @param userId
     */
    @Delete("delete from t_user_role u where u.user_id = #{userId}")
    boolean deleteByUserId(String userId);

    /**
     * 通过用户id获取
     *
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);

    /**
     * 通过用户id获取用户角色关联的部门数据
     *
     * @param userId
     * @return
     */
    List<String> findDepIdsByUserId(@Param("userId") String userId);
}
