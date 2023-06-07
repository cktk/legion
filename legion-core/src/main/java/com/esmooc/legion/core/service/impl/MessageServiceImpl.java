package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.mapper.MessageMapper;
import com.esmooc.legion.core.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息内容接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public IPage<Message> findByCondition(Message message, SearchVo searchVo, PageVo pageable) {
        return this.page(PageUtil.initMpPage(pageable), Wrappers.query(message));


    }

    @Override
    public List<Message> findByCreateSend(Boolean createSend) {

        return messageMapper.findByCreateSend(createSend);
    }
}
