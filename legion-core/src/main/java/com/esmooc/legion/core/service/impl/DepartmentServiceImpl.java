package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.mapper.DepartmentMapper;
import com.esmooc.legion.core.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public List<Department> findByParentIdOrderBySortOrder(String parentId, Boolean openDataFilter) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        // 数据权限
        List<String> depIds = SecurityUtil.getDeparmentIds();
        queryWrapper.lambda().eq(Department::getParentId,parentId).orderByAsc(Department::getSortOrder);
        if (depIds != null && depIds.size() > 0 && openDataFilter) {
            queryWrapper.lambda().in(Department::getId, depIds);
        }
        return this.list(queryWrapper);
    }

    @Override
    public List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Department::getParentId,parentId)
                .eq(Department::getStatus,status)
                .orderByAsc(Department::getSortOrder);
        return this.list(queryWrapper);
    }

    @Override
    public List<Department> findByTitleLikeOrderBySortOrder(String title, Boolean openDataFilter) {
        // 数据权限
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        // 数据权限
        List<String> depIds = SecurityUtil.getDeparmentIds();

        queryWrapper.lambda().like(Department::getTitle,title).orderByAsc(Department::getSortOrder);
        if (depIds != null && depIds.size() > 0 && openDataFilter) {
            queryWrapper.lambda().in(Department::getId, depIds);
        }
        return this.list(queryWrapper);



    }
}
