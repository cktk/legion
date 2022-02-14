package com.esmooc.legion.activiti.dao;

import com.esmooc.legion.activiti.entity.ActNode;
import com.esmooc.legion.core.base.LegionBaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 流程节点用户数据处理层
 * @author DaiMao
 */
public interface ActNodeDao extends LegionBaseDao<ActNode, String> {

    /**
     * 通过nodeId获取
     * @param nodeId
     * @param type
     * @return
     */
    List<ActNode> findByNodeIdAndType(String nodeId, Integer type);

    /**
     * 通过nodeId删除
     * @param nodeId
     */
    @Modifying
    @Query("delete from ActNode a where a.nodeId = ?1")
    void deleteByNodeId(String nodeId);

    /**
     * 通过relateId删除
     * @param relateId
     */
    @Modifying
    @Query("delete from ActNode a where a.relateId = ?1")
    void deleteByRelateId(String relateId);
}
