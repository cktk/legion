package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门数据处理层
 *
 * @author DaiMao
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 通过父id获取 升序
     *
     * @param parentId
     * @return
     */
    @Select("select * from t_department where  parent_id =#{parentId} order by sort_order")
    List<Department> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过父id获取 升序 数据权限
     *
     * @param parentId
     * @param departmentIds
     * @return
     */
    @Select("select * from t_department where  parent_id =#{parentId} and id in #{departmentIds} order by sort_order")
    List<Department> findByParentIdAndIdInOrderBySortOrder(@Param("parentId") String parentId, @Param("departmentIds") List<String> departmentIds);

    /**
     * 通过父id和状态获取 升序
     *
     * @param parentId
     * @param status
     * @return
     */

    @Select("select * from t_department parent_id =#{parentId} and status =#{status} order by sort_order")
    List<Department> findByParentIdAndStatusOrderBySortOrder(@Param("parentId") String parentId, @Param("status") Integer status);

    /**
     * 部门名模糊搜索 升序
     *
     * @param title
     * @return
     */
    @Select("select * from t_department where  title like CONCAT('%',#{title},'%')    order by sort_order")
    List<Department> findByTitleLikeOrderBySortOrder(@Param("title") String title);

    /**
     * 部门名模糊搜索 升序 数据权限
     *
     * @param title
     * @param departmentIds
     * @return
     */
    @Select("select * from t_department where  title like CONCAT('%',#{title},'%')   and id in #{departmentIds} order by sort_order")
    List<Department> findByTitleLikeAndIdInOrderBySortOrder(@Param("title") String title, @Param("departmentIds") List<String> departmentIds);

}
