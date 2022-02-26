package com.esmooc.legion.activiti.service;

import com.esmooc.legion.activiti.entity.ActCategory;
import com.esmooc.legion.core.base.LegionBaseService;

import java.util.List;

/**
 * 流程分类接口
 *
 * @author DaiMao
 */
public interface ActCategoryService extends LegionBaseService<ActCategory, String> {

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
