package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.Log;
import com.esmooc.legion.core.mapper.LogMapper;
import com.esmooc.legion.core.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 日志接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {


}
