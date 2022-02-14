package com.esmooc.legion.core.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 消息内容接口
 * @author DaiMao
 */
public interface MessageService extends LegionBaseService<Message, String> {

    /**
     * 多条件分页获取
     * @param message
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Message> findByCondition(Message message, SearchVo searchVo, Pageable pageable);

    /**
     * 通过创建发送标识获取
     * @param createSend
     * @return
     */
    List<Message> findByCreateSend(Boolean createSend);
}
