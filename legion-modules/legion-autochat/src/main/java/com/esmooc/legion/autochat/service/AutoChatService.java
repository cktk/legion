package com.esmooc.legion.autochat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.autochat.entity.AutoChat;

/**
 * 问答助手客服接口
 * @author Daimao
 */
public interface AutoChatService  extends IService<AutoChat>  {

    /**
     * 完全匹配
     * @param title
     * @return
     */
    AutoChat findByTitle(String title);
}
