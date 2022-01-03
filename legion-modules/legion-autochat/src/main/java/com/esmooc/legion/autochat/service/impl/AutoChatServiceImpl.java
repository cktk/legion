package com.esmooc.legion.autochat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.autochat.mapper.AutoChatMapper;
import com.esmooc.legion.autochat.service.AutoChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 问答助手客服接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class AutoChatServiceImpl extends ServiceImpl<AutoChatMapper, AutoChat>  implements AutoChatService {

    @Autowired
    private AutoChatMapper autoChatMapper;


    @Override
    public AutoChat findByTitle(String title) {

        List<AutoChat> list = autoChatMapper.findByTitle(title);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
