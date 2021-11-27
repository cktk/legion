package com.esmooc.legion.autochat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.core.common.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
