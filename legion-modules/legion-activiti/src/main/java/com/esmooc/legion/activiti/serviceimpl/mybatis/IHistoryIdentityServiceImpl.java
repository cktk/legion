package com.esmooc.legion.activiti.serviceimpl.mybatis;

import com.esmooc.legion.activiti.dao.mapper.HistoryIdentityMapper;
import com.esmooc.legion.activiti.service.mybatis.IHistoryIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DaiMao
 */
@Service
public class IHistoryIdentityServiceImpl implements IHistoryIdentityService {

    @Autowired
    private HistoryIdentityMapper historyIdentityMapper;

    @Override
    public Integer insert(String id, String type, String userId, String taskId, String procInstId) {

        return historyIdentityMapper.insert(id, type, userId, taskId, procInstId);
    }

    @Override
    public String findUserIdByTypeAndTaskId(String type, String taskId) {

        return historyIdentityMapper.findUserIdByTypeAndTaskId(type, taskId);
    }
}
