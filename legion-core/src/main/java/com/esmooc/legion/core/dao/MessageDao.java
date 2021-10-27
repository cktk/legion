package com.esmooc.legion.core.dao;

import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.core.entity.Message;

import java.util.List;

/**
 * 消息内容数据处理层
 *
 * @author Daimao
 */
public interface MessageDao extends LegionBaseDao<Message, String> {

    /**
     * 通过创建发送标识获取
     *
     * @param createSend
     * @return
     */
    List<Message> findByCreateSend(Boolean createSend);
}