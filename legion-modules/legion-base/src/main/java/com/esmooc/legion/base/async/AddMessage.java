package com.esmooc.legion.base.async;

import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.MessageSend;
import com.esmooc.legion.core.service.MessageSendService;
import com.esmooc.legion.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 异步添加消息
 *
 * @author Daimao
 */
@Component
public class AddMessage {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 初始化消息
     *
     * @param userId
     */
    @Async
    public void addSendMessage(String userId) {

        // 获取需要创建账号发送的消息
        List<Message> messages = messageService.findByCreateSend(true);
        List<MessageSend> messageSends = new ArrayList<>();
        messages.forEach(item -> {
            MessageSend ms = new MessageSend();
            ms.setUserId(userId);
            ms.setMessageId(item.getId());
            messageSends.add(ms);
        });
        messageSendService.saveOrUpdateAll(messageSends);
    }
}
