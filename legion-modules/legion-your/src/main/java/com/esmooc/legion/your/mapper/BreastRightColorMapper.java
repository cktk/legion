package com.esmooc.legion.your.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.your.entity.breastleftandrightcolor.BreastRightColor;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("screening2")
public interface BreastRightColorMapper extends BaseMapper<BreastRightColor> {

}