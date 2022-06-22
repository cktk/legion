package com.esmooc.legion.quartz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 定时任务数据处理层
 *
 * @author DaiMao
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

    /**
     * 通过类名获取
     *
     * @param jobClassName
     * @return
     */
    @Select("select * from t_quartz_job  where job_class_name=#{jobClassName}")
    List<QuartzJob> findByJobClassName(String jobClassName);
}
