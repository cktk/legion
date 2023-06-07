package com.esmooc.legion.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.quartz.entity.QuartzJob;
import com.esmooc.legion.quartz.mapper.QuartzJobMapper;
import com.esmooc.legion.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {

        return quartzJobMapper.findByJobClassName(jobClassName);
    }

    @Override
    public IPage<QuartzJob> findByCondition(String key, PageVo pageable) {
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda()
                .like(QuartzJob::getJobClassName ,key ).or()
                .like(QuartzJob::getParameter ,key ).or()
                .like(QuartzJob::getDescription ,key );
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);
    }
}
