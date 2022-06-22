package com.esmooc.legion.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.base.entity.DictData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * 字典数据数据处理层
 *
 * @author DaiMao
 */
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 通过dictId和状态获取
     *
     * @param dictId
     * @param status
     * @return
     */
    @Select("select * from t_dict_data where dict_id =#{dictId} and status =#{status} order by sort_order")
    List<DictData> findByDictIdAndStatusOrderBySortOrder(@Param("dictId") String dictId,@Param("status") Integer status);

    /**
     * 通过dictId删除
     *
     * @param dictId
     */
    @Delete("delete from DictData d where d.dictId = ?1")
    void deleteByDictId(String dictId);

    @Select("select * from t_dict_data where type_code =#{typeCode} and status_normal =#{statusNormal} order by sort_order")
    DictData findByTypeCodeAndStatusOrderBySortOrder(@Param("typeCode")  String typeCode,@Param("statusNormal")  Integer statusNormal);


    @Select("select * from t_dict_data where type =#{type} and status =#{statusNormal} order by sort_order")
    List<DictData> findByTypeAndStatusOrderBySortOrder(@Param("type")  String type, @Param("statusNormal") Integer statusNormal);


}
