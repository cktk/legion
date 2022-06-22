package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限数据处理层
 *
 * @author DaiMao
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByUserId(@Param("userId") String userId);
}
