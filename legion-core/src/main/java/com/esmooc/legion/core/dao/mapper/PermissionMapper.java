package com.esmooc.legion.core.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Daimao
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过用户id获取
     *
     * @param userId
     * @return
     */
    List<Permission> findByUserId(@Param("userId") String userId);
}
