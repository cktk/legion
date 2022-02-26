package com.esmooc.legion.activiti.dao;

import com.esmooc.legion.activiti.entity.ActBusiness;
import com.esmooc.legion.core.base.LegionBaseDao;

import java.util.List;

/**
 * 申请业务数据处理层
 *
 * @author DaiMao
 */
public interface ActBusinessDao extends LegionBaseDao<ActBusiness, String> {

    /**
     * 通过流程定义id获取
     *
     * @param procDefId
     * @return
     */
    List<ActBusiness> findByProcDefId(String procDefId);
}
