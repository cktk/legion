package com.esmooc.legion.base.dao;

import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.core.base.LegionBaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 字典数据数据处理层
 *
 * @author DaiMao
 */
public interface DictDataDao extends LegionBaseDao<DictData, String> {

    /**
     * 通过dictId和状态获取
     *
     * @param dictId
     * @param status
     * @return
     */
    List<DictData> findByDictIdAndStatusOrderBySortOrder(String dictId, Integer status);

    /**
     * 通过dictId删除
     *
     * @param dictId
     */
    @Modifying
    @Query("delete from DictData d where d.dictId = ?1")
    void deleteByDictId(String dictId);

    DictData findByTypeCodeAndStatusOrderBySortOrder(String typeCode, Integer statusNormal);


    List<DictData> findByTypeAndStatusOrderBySortOrder(String type, Integer statusNormal);


}
