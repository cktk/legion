package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.DepartmentHeader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门负责人数据处理层
 * @author Daimao
 */
@Mapper
public interface DepartmentHeaderMapper extends BaseMapper<DepartmentHeader> {

    /**
     * 通过部门和负责人类型获取
     * @param departmentId
     * @param type
     * @return
     */
    List<DepartmentHeader> findByDepartmentIdAndType(@Param("departmentId") String departmentId, @Param("type") Integer type);

    /**
     * 通过部门获取
     * @param departmentIds
     * @return
     */
    List<DepartmentHeader> findByDepartmentIdIn(@Param("departmentIds") List<String> departmentIds);

    /**
     * 通过部门id删除
     * @param departmentId
     */
    void deleteByDepartmentId(String departmentId);

    /**
     * 通过userId删除
     * @param userId
     */
    void deleteByUserId(String userId);

    /**
     * 通过部门id和userId类型获取
     * @param userId
     * @param departmentId
     * @return
     */
    List<DepartmentHeader> findByUserIdAndDepartmentId(@Param("userId") String userId, @Param("departmentId") String departmentId);
}
