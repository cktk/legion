package com.esmooc.legion.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.base.entity.DictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典数据数据处理层
 * @author Daimao
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    @Select("select * from t_dict_data where  dict_id =#{dictId} and status =#{status} order by sort_order")
    List<DictData> findByDictIdAndStatusOrderBySortOrder(@Param("dictId") String dictId, @Param("status")  Integer status);
}
