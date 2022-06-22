package com.esmooc.legion.autochat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;



/**
 * 问答助手客服接口
 *
 * @author DaiMao
 */
public interface AutoChatService extends IService<AutoChat> {

    /**
     * 多条件分页获取
     *
     * @param autoChat
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<AutoChat> findByCondition(AutoChat autoChat, SearchVo searchVo, PageVo pageable);

    /**
     * 完全匹配
     *
     * @param title
     * @return
     */
    AutoChat findByTitle(String title);
}
