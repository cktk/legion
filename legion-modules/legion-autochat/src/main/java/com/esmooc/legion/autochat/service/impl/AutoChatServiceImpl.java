package com.esmooc.legion.autochat.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.autochat.mapper.AutoChatMapper;
import com.esmooc.legion.autochat.service.AutoChatService;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 问答助手客服接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class AutoChatServiceImpl extends ServiceImpl<AutoChatMapper, AutoChat>  implements AutoChatService {

    @Autowired
    private AutoChatMapper autoChatMapper;

    @Override
    public IPage<AutoChat> findByCondition(AutoChat autoChat, SearchVo searchVo, PageVo pageable) {


        QueryWrapper<AutoChat> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(autoChat.getTitle()),AutoChat::getTitle ,autoChat.getTitle() ).or()
                .like(StrUtil.isNotBlank(autoChat.getKeywords()),AutoChat::getKeywords ,autoChat.getKeywords() ).or()
                .like(StrUtil.isNotBlank(autoChat.getContent()),AutoChat::getContent ,autoChat.getContent() );

        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(AutoChat::getCreateTime, start, end);
        }

        return this.page(PageUtil.initMpPage(pageable),queryWrapper);


    }

    @Override
    public AutoChat findByTitle(String title) {

        List<AutoChat> list = autoChatMapper.findByTitle(title);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
