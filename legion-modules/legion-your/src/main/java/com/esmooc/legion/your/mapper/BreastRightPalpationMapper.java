package com.esmooc.legion.your.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.your.entity.breastleftandrightpalpation.BreastRightPalpation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("screening2")
public interface BreastRightPalpationMapper extends BaseMapper<BreastRightPalpation> {
}