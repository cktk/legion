package com.esmooc.legion.your.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.esmooc.legion.your.entity.cervicalGynecological.CervicalGynecological;
import com.esmooc.legion.your.entity.cervicalGynecological.GynecologicalDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("screening2")
public interface CervicalGynecologicalMapper extends BaseMapper<CervicalGynecological> {
     GynecologicalDto getAllByUserId(int userId);
}