package com.esmooc.legion.core.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.entity.Permission;

import java.util.List;

/**
 * 权限接口
 * @author DaiMao
 */
public interface PermissionService extends LegionBaseService<Permission, String> {

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    List<Permission> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过类型和状态获取
     * @param type
     * @param status
     * @return
     */
    List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status);

    /**
     * 通过名称获取
     * @param title
     * @return
     */
    List<Permission> findByTitle(String title);

    /**
     * 模糊搜索
     * @param title
     * @return
     */
    List<Permission> findByTitleLikeOrderBySortOrder(String title);
}
