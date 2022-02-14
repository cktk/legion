package com.esmooc.legion.autochat.service;

import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 问答助手客服接口
 * @author DaiMao
 */
public interface AutoChatService extends LegionBaseService<AutoChat, String> {

    /**
     * 多条件分页获取
     * @param autoChat
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<AutoChat> findByCondition(AutoChat autoChat, SearchVo searchVo, Pageable pageable);

    /**
     * 完全匹配
     * @param title
     * @return
     */
    AutoChat findByTitle(String title);
}
