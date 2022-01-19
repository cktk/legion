package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.DictionariesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionariesMapper extends BaseMapper<DictionariesEntity> {
    DictionariesEntity selectByFieldNameAndFieldValue(String fieldName, String fieldValue);
}
