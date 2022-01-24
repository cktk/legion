package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限数据处理层
 *
 * @author Daimao
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Permission> findByUserId(@Param("userId") String userId);


    /**
     * 通过层级查找
     * 默认升序
     * @param level
     * @return
     */
    @Select("select * from t_permission where Level=#{level} and del_flag=0 order by sort_order")
    List<Permission> findByLevelOrderBySortOrder(@Param("level") Integer level);

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    @Select("select * from t_permission where  parent_id = #{parentId} and del_flag=0 order by sort_order ")
    List<Permission> findByParentIdOrderBySortOrder(@Param("parentId") String parentId);

    /**
     * 通过类型和状态获取
     * @param type
     * @param status
     * @return
     */
    @Select("select * from t_permission where  type = #{type} and del_flag=0 and  status=#{status} order by sort_order ")
    List<Permission> findByTypeAndStatusOrderBySortOrder(@Param("type") Integer type, @Param("status") Integer status);

    /**
     * 通过名称获取
     * @param title
     * @return
     */
    @Select("select * from t_permission where  title = #{title} and del_flag=0")
    List<Permission> findByTitle(@Param("title") String title);

    /**
     * 模糊搜索
     * @param title
     * @return
     */
    @Select("select * from t_permission where  title like #{title} and del_flag=0 order by sort_order")
    List<Permission> findByTitleLikeOrderBySortOrder(@Param("title") String title);
}
