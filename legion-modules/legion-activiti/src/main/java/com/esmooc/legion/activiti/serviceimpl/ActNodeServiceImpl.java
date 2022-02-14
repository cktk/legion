package com.esmooc.legion.activiti.serviceimpl;

import com.esmooc.legion.activiti.dao.ActNodeDao;
import com.esmooc.legion.activiti.entity.ActNode;
import com.esmooc.legion.activiti.service.ActNodeService;
import com.esmooc.legion.core.common.constant.ActivitiConstant;
import com.esmooc.legion.core.dao.DepartmentDao;
import com.esmooc.legion.core.dao.RoleDao;
import com.esmooc.legion.core.dao.UserDao;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程节点用户接口实现
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class ActNodeServiceImpl implements ActNodeService {

    @Autowired
    private ActNodeDao actNodeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public ActNodeDao getRepository() {
        return actNodeDao;
    }

    @Override
    public List<User> findUserByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_USER);
        List<User> list = new ArrayList<>();
        listNode.forEach(e -> {
            User u = userDao.findById(e.getRelateId()).orElse(null);
            if (u != null) {
                list.add(u);
            }
        });
        return list;
    }

    @Override
    public List<Role> findRoleByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_ROLE);
        List<Role> list = new ArrayList<>();
        listNode.forEach(e -> {
            Role r = roleDao.findById(e.getRelateId()).orElse(null);
            if (r != null) {
                list.add(r);
            }
        });
        return list;
    }

    @Override
    public List<Department> findDepartmentByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<Department> list = new ArrayList<>();
        listNode.forEach(e -> {
            Department d = departmentDao.findById(e.getRelateId()).orElse(null);
            if (d != null) {
                list.add(d);
            }
        });
        return list;
    }

    @Override
    public List<String> findDepartmentIdsByNodeId(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEPARTMENT);
        List<String> list = new ArrayList<>();
        listNode.forEach(e -> {
            list.add(e.getRelateId());
        });
        return list;
    }

    @Override
    public Boolean hasChooseDepHeader(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_DEP_HEADER);
        if (listNode != null && listNode.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean hasCustomUser(String nodeId) {

        List<ActNode> listNode = actNodeDao.findByNodeIdAndType(nodeId, ActivitiConstant.NODE_CUSTOM);
        if (listNode != null && listNode.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteByNodeId(String nodeId) {

        actNodeDao.deleteByNodeId(nodeId);
    }

    @Override
    public void deleteByRelateId(String relateId) {

        actNodeDao.deleteByRelateId(relateId);
    }
}
