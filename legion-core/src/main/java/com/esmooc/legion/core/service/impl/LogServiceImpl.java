package com.esmooc.legion.core.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.mapper.LogMapper;
import com.esmooc.legion.core.entity.Log;
import com.esmooc.legion.core.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 日志接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public IPage<Log> findByConfition(Integer type, String key, SearchVo searchVo, PageVo page) {


        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(type!=null,Log::getLogType,type);

        if (StrUtil.isNotBlank(key)) {
            queryWrapper.lambda().like(Log::getName,key ).or()
                    .like(Log::getRequestUrl,key).or()
                    .like(Log::getRequestType,key).or()
                    .like(Log::getRequestParam,key).or()
                    .like(Log::getUsername,key).or()
                    .like(Log::getIp,key).or()
                    .like(Log::getIpInfo,key).or()
                    .like(Log::getDevice,key).or();
        }

        // 创建时间
        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().gt(Log::getCreateTime, start).lt(Log::getCreateTime, DateUtil.endOfDay(end));
        }


       return  this.page(PageUtil.initMpPage(page) , queryWrapper);
    }

    @Override
    public void deleteAll() {
        logMapper.deleteAll();
    }
}
