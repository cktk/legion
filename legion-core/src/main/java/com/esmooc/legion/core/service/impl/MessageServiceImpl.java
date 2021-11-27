package com.esmooc.legion.core.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.mapper.MessageMapper;
import com.esmooc.legion.core.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息内容接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class MessageServiceImpl  extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;



    @Override
    public List<Message> findByCreateSend(Boolean createSend) {

        return messageMapper.findByCreateSend(createSend);
    }
}
