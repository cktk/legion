package com.esmooc.legion.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.quartz.entity.QuartzJob;
import com.esmooc.legion.quartz.mapper.QuartzJobMapper;
import com.esmooc.legion.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {


}
