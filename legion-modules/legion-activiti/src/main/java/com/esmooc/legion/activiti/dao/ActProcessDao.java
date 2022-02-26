package com.esmooc.legion.activiti.dao;

import com.esmooc.legion.activiti.entity.ActProcess;
import com.esmooc.legion.core.base.LegionBaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 流程管理数据处理层
 *
 * @author DaiMao
 */
public interface ActProcessDao extends LegionBaseDao<ActProcess, String> {

    /**
     * 通过key获取
     *
     * @param processKey
     * @return
     */
    List<ActProcess> findByProcessKey(String processKey);

    /**
     * 通过key和latest获取
     *
     * @param processKey
     * @return
     */
    ActProcess findByProcessKeyAndLatest(String processKey, Boolean latest);

    /**
     * 通过key获取按版本排序
     *
     * @param processKey
     * @return
     */
    ActProcess findTopByProcessKeyOrderByVersionDesc(String processKey);

    /**
     * 通过分类获取
     *
     * @param categoryId
     * @return
     */
    List<ActProcess> findByCategoryId(String categoryId);

    /**
     * 更新分类名称
     *
     * @param categoryId
     * @param categoryTitle
     */
    @Modifying
    @Query("update ActProcess p set p.categoryTitle=?2 where p.categoryId=?1")
    void updateCategoryTitle(String categoryId, String categoryTitle);
}
