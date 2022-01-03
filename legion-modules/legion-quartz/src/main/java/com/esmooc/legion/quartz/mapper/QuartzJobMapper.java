package com.esmooc.legion.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务数据处理层
 * @author Daimao
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

}
