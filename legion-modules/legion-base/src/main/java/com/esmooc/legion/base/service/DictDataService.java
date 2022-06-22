package com.esmooc.legion.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.core.common.vo.PageVo;


import java.util.List;

/**
 * 字典数据接口
 *
 * @author DaiMao
 */
public interface DictDataService extends IService<DictData> {

    /**
     * 多条件获取
     *
     * @param dictDaIa
     * @param pageable
     * @return
     */
    IPage<DictData> findByCondition(DictData dictData, PageVo pageable);

    /**
     * 通过dictId获取启用字典 已排序
     *
     * @param dictId
     * @return
     */
    List<DictData> findByDictId(String dictId);

    /**
     * 通过dictId删除
     *
     * @param dictId
     */
    void deleteByDictId(String dictId);

    DictData findByTypeCode(String typeCode);

    List<DictData> findByType(String type);

}
