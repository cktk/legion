package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.entity.Department;

import java.util.List;

/**
 * 部门接口
 * @author Daimao
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 通过id 查询下面所有的id
     * @param id
     * @return
     */
    List<Department> findById(String id);

    /**
     * 通过父id获取 升序
     * @param parentId
     * @param openDataFilter 是否开启数据权限
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId, Boolean openDataFilter,List<String> depIds);

    /**
     * 通过父id和状态获取
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);

    /**
     * 部门名模糊搜索 升序
     * @param title
     * @param openDataFilter 是否开启数据权限
     * @return
     */
    List<Department> findByTitleLikeOrderBySortOrder(String title, Boolean openDataFilter, List<String> depIds );
}
