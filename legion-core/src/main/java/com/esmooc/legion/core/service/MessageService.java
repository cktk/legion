package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;

import java.util.List;

/**
 * 消息内容接口
 *
 * @author DaiMao
 */
public interface MessageService extends IService<Message> {

    /**
     * 多条件分页获取
     *
     * @param message
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<Message> findByCondition(Message message, SearchVo searchVo, PageVo pageable);

    /**
     * 通过创建发送标识获取
     *
     * @param createSend
     * @return
     */
    List<Message> findByCreateSend(Boolean createSend);
}
