package com.esmooc.legion.base.service;

import com.esmooc.legion.base.entity.Dict;
import com.esmooc.legion.core.base.LegionBaseService;

import java.util.List;

/**
 * 字典接口
 * @author DaiMao
 */
public interface DictService extends LegionBaseService<Dict, String> {

    /**
     * 排序获取全部
     * @return
     */
    List<Dict> findAllOrderBySortOrder();

    /**
     * 通过type获取
     * @param type
     * @return
     */
    Dict findByType(String type);

    /**
     * 模糊搜索
     * @param key
     * @return
     */
    List<Dict> findByTitleOrTypeLike(String key);
}
