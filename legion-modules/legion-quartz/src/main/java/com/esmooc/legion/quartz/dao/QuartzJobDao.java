package com.esmooc.legion.quartz.dao;

import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.quartz.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务数据处理层
 * @author DaiMao
 */
public interface QuartzJobDao extends LegionBaseDao<QuartzJob, String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}
