package com.esmooc.legion.activiti.serviceimpl.mybatis;

import com.esmooc.legion.activiti.dao.mapper.RunIdentityMapper;
import com.esmooc.legion.activiti.service.mybatis.IRunIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DaiMao
 */
@Service
public class IRunIdentityServiceImpl implements IRunIdentityService {

    @Autowired
    private RunIdentityMapper runIdentityMapper;

    @Override
    public List<String> selectByConditions(String taskId, String type) {
        return runIdentityMapper.selectByConditions(taskId, type);
    }
}
