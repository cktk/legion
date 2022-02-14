package com.esmooc.legion.activiti.service;

import com.esmooc.legion.activiti.entity.ActNode;
import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.core.entity.User;

import java.util.List;

/**
 * 流程节点用户接口
 * @author DaiMao
 */
public interface ActNodeService extends LegionBaseService<ActNode, String> {

    /**
     * 通过nodeId获取用户
     * @param nodeId
     * @return
     */
    List<User> findUserByNodeId(String nodeId);

    /**
     * 通过nodeId获取角色
     * @param nodeId
     * @return
     */
    List<Role> findRoleByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门
     * @param nodeId
     * @return
     */
    List<Department> findDepartmentByNodeId(String nodeId);

    /**
     * 通过nodeId获取部门id
     * @param nodeId
     * @return
     */
    List<String> findDepartmentIdsByNodeId(String nodeId);

    /**
     * 是否多级连续主管
     * @param nodeId
     * @return
     */
    Boolean hasChooseDepHeader(String nodeId);

    /**
     * 是否自选用户
     * @param nodeId
     * @return
     */
    Boolean hasCustomUser(String nodeId);

    /**
     * 通过nodeId删除
     * @param nodeId
     */
    void deleteByNodeId(String nodeId);

    /**
     * 通过relateId删除
     * @param relateId
     */
    void deleteByRelateId(String relateId);
}
