package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.DepartmentHeader;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.entity.vo.UserVo;
import com.esmooc.legion.core.mapper.DepartmentHeaderMapper;
import com.esmooc.legion.core.service.DepartmentHeaderService;
import com.esmooc.legion.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门负责人接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class DepartmentHeaderServiceImpl extends ServiceImpl<DepartmentHeaderMapper, DepartmentHeader> implements DepartmentHeaderService {

    @Autowired
    private DepartmentHeaderMapper departmentHeaderMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<UserVo> findHeaderByDepartmentId(String departmentId, Integer type) {

        List<UserVo> list = new ArrayList<>();
        List<DepartmentHeader> headers = departmentHeaderMapper.findByDepartmentIdAndType(departmentId, type);
        headers.forEach(e -> {
            User u = userService.getById(e.getUserId());
            if (u != null) {
                list.add(new UserVo().setId(u.getId()).setUsername(u.getUsername()).setNickname(u.getNickname()));
            }
        });
        return list;
    }

    @Override
    public List<DepartmentHeader> findByDepartmentIdIn(List<String> departmentIds) {

        return departmentHeaderMapper.findByDepartmentIdIn(departmentIds);
    }

    @Override
    public void deleteByDepartmentId(String departmentId) {

        departmentHeaderMapper.deleteByDepartmentId(departmentId);
    }

    @Override
    public void deleteByUserId(String userId) {

        departmentHeaderMapper.deleteByUserId(userId);
    }

    @Override
    public Boolean isDepartmentHeader(String userId, String departmentId) {

        List<DepartmentHeader> headers = departmentHeaderMapper.findByUserIdAndDepartmentId(userId, departmentId);
        if (headers != null && !headers.isEmpty()) {
            return true;
        }
        return false;
    }
}
