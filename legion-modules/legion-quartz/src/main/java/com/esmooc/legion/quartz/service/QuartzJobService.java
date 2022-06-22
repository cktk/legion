package com.esmooc.legion.quartz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.quartz.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务接口
 *
 * @author DaiMao
 */
public interface QuartzJobService extends IService<QuartzJob> {

    /**
     * 通过类名获取
     *
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);

    /**
     * 分页获取
     *
     * @param key
     * @param pageable
     * @return
     */
    IPage<QuartzJob> findByCondition(String key, PageVo pageable);
}
