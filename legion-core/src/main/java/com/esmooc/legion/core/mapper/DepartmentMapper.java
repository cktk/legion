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
     * 通过父id和状态获取 升序
     *
     * @param parentId
     * @param status
     * @return
     */



}
