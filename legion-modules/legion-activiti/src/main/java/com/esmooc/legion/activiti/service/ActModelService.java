package com.esmooc.legion.activiti.service;

import com.esmooc.legion.activiti.entity.ActModel;
import com.esmooc.legion.core.base.LegionBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 模型管理接口
 * @author DaiMao
 */
public interface ActModelService extends LegionBaseService<ActModel, String> {

    /**
     * 多条件分页获取
     * @param actModel
     * @param pageable
     * @return
     */
    Page<ActModel> findByCondition(ActModel actModel, Pageable pageable);
}
