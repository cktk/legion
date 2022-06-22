package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色数据处理层
 *
 * @author DaiMao
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取默认角色
     *
     * @param defaultRole
     * @return
     */
    @Select("select * from t_role where default_role=#{defaultRole} ")
    List<Role> findByDefaultRole(Boolean defaultRole);
}
