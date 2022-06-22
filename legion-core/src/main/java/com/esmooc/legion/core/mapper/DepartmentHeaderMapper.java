package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.DepartmentHeader;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门负责人数据处理层
 *
 * @author DaiMao
 */
public interface DepartmentHeaderMapper extends BaseMapper<DepartmentHeader> {

    /**
     * 通过部门和负责人类型获取
     *
     * @param departmentId
     * @param type
     * @return
     */
    @Select("select  * from t_department_header where department_id =#{departmentId} and type =#{type}")
    List<DepartmentHeader> findByDepartmentIdAndType(@Param("departmentId") String departmentId, @Param("type") Integer type);

    /**
     * 通过部门获取
     *
     * @param departmentIds
     * @return
     */
    @Select("select  * from t_department_header where  department_id in ( #{departmentIds})")
    List<DepartmentHeader> findByDepartmentIdIn(@Param("departmentIds") List<String> departmentIds);

    /**
     * 通过部门id删除
     *
     * @param departmentId
     */
    @Select("delete from t_department_header d where d.departmentId =  #{departmentId}")
    void deleteByDepartmentId( @Param("departmentId") String departmentId);

    /**
     * 通过userId删除
     *
     * @param userId
     */
    @Select("delete from t_department_header d where d.userId = #{userId}")
    void deleteByUserId(@Param("userId") String userId);

    /**
     * 通过部门id和userId类型获取
     *
     * @param userId
     * @param departmentId
     * @return
     */
    @Select("select  * from t_department_header where user_id =#{userId} and department_id =#{departmentId}")
    List<DepartmentHeader> findByUserIdAndDepartmentId(@Param("userId") String userId, @Param("departmentId")String departmentId);
}
