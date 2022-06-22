package com.esmooc.legion.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.base.entity.Dict;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 字典数据处理层
 *
 * @author DaiMao
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 排序获取全部
     *
     * @return
     */
    @Select(value = "select d from Dict d order by d.sortOrder")
    List<Dict> findAllOrderBySortOrder();

    /**
     * 通过type获取
     *
     * @param type
     * @return
     */
    @Select(" select d from Dict d where type =#{type }")
    Dict findByType(String type);

    /**
     * 模糊搜索
     *
     * @param key
     * @return
     */
    @Select(value = "select d from Dict d where d.title like CONCAT('%',#{key},'%')  or d.type like  CONCAT('%',#{key},'%') order by d.sortOrder")
    List<Dict> findByTitleOrTypeLike(@Param("key") String key);
}
