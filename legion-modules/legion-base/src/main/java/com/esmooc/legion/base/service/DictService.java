package com.esmooc.legion.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.base.entity.Dict;

import java.util.List;

/**
 * 字典接口
 * @author Daimao
 */
public interface DictService extends IService<Dict> {

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
