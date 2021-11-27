package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 消息内容接口
 * @author Daimao
 */
public interface MessageService extends IService<Message> {


    /**
     * 通过创建发送标识获取
     * @param createSend
     * @return
     */
    List<Message> findByCreateSend(Boolean createSend);
}
