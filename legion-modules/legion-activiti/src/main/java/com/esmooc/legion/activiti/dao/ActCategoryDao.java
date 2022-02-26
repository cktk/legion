package com.esmooc.legion.activiti.dao;

import com.esmooc.legion.activiti.entity.ActCategory;
import com.esmooc.legion.core.base.LegionBaseDao;

import java.util.List;

/**
 * 流程分类数据处理层
 *
 * @author DaiMao
 */
public interface ActCategoryDao extends LegionBaseDao<ActCategory, String> {

    /**
     * 通过父id获取
     *
     * @param parentId
     * @return
     */
    List<ActCategory> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过名称模糊搜索
     *
     * @param title
     * @return
     */
    List<ActCategory> findByTitleLikeOrderBySortOrder(String title);
}
