package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.mapper.MessageMapper;
import com.esmooc.legion.core.mapper.MessageSendMapper;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.MessageSend;
import com.esmooc.legion.core.service.MessageSendService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息发送接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class MessageSendServiceImpl  extends ServiceImpl<MessageSendMapper, MessageSend> implements MessageSendService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageSendMapper messageSendMapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;



    @Override
    public MessageSend send(MessageSend messageSend) {
        int insert = messageSendMapper.insert(messageSend);
        messagingTemplate.convertAndSendToUser(messageSend.getUserId(), "/queue/subscribe", "您收到了新的消息");
        return messageSend;
    }

    @Override
    public void deleteByMessageId(String messageId) {

        messageSendMapper.deleteByMessageId(messageId);
    }

    @Override
    public IPage<MessageSend> findByCondition(MessageSend messageSend, PageVo pageable) {

        return this.page(PageUtil.initMpPage(pageable), Wrappers.query(messageSend));

    }

    @Override
    public void updateStatusByUserId(String userId, Integer status) {

        messageSendMapper.updateStatusByUserId(userId, status);
    }

    @Override
    public void deleteByUserId(String userId) {

        messageSendMapper.deleteByUserId(userId, CommonConstant.MESSAGE_STATUS_READ);
    }

    @Override
    public Message getTemplateMessage(String messageId, Map<String, String> params) {

        Message message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new LegionException("消息ID：" + messageId + "不存在");
        }
        // 放入变量
        Message newMessage = new Message();
        newMessage.setTitle(changeParams(message.getTitle(), params));
        newMessage.setContent(changeParams(message.getContent(), params));
        return newMessage;
    }

    @Override
    public void sendTemplateMessage(List<String> userIds, String messageId, Map<String, String> params) {

        Message message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new LegionException("消息ID：" + messageId + "不存在");
        }
        List<MessageSend> messageSends = new ArrayList<>();
        for (String userId : userIds) {
            MessageSend ms = new MessageSend();
            ms.setUserId(userId).setMessageId(messageId);
            // 放入变量
            ms.setTitle(changeParams(message.getTitle(), params));
            ms.setContent(changeParams(message.getContent(), params));
            ms.setParams(new Gson().toJson(params));
            messageSends.add(ms);
            messagingTemplate.convertAndSendToUser(userId, "/queue/subscribe", "您收到了新的消息");
        }
        this.saveBatch(messageSends);
    }

    @Override
    public void sendTemplateMessage(String userId, String messageId, Map<String, String> params) {

        List<String> users = new ArrayList<>();
        users.add(userId);
        this.sendTemplateMessage(users, messageId, params);
    }

    public String changeParams(String v, Map<String, String> params) {

        if (v == null || params == null) {
            return "";
        }
        for (Map.Entry entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            v = v.replaceAll("\\$\\{" + key + "\\}", value);
        }
        return v;
    }
}
