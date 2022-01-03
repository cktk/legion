package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.mapper.DepartmentMapper;
import com.esmooc.legion.core.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl  extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;



    @Override
    public List<Department> findByParentIdOrderBySortOrder(String parentId, Boolean openDataFilter,List<String> depIds ) {
        if (depIds != null && depIds.size() > 0 && openDataFilter) {
            return departmentMapper.findByParentIdAndIdInOrderBySortOrder(parentId, depIds);
        }
        return departmentMapper.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status) {

        return departmentMapper.findByParentIdAndStatusOrderBySortOrder(parentId, status);
    }

    @Override
    public List<Department> findByTitleLikeOrderBySortOrder(String title, Boolean openDataFilter, List<String> depIds ) {

        if (depIds != null && depIds.size() > 0 && openDataFilter) {
            return departmentMapper.findByTitleLikeAndIdInOrderBySortOrder(title, depIds);
        }
        return departmentMapper.findByTitleLikeOrderBySortOrder(title);
    }
}
