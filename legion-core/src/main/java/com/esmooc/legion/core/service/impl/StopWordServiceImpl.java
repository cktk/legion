package com.esmooc.legion.core.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.WordTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.mapper.StopWordMapper;
import com.esmooc.legion.core.entity.StopWord;
import com.esmooc.legion.core.service.StopWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 禁用词管理接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class StopWordServiceImpl  extends ServiceImpl<StopWordMapper,StopWord > implements StopWordService {

    private static WordTree wordTree;
    @Autowired
    private StopWordService stopWordService;


    public WordTree getInstance() {

        if (wordTree == null) {
            // 初始加载数据
            wordTree = new WordTree();
            stopWordService.list().forEach(e -> wordTree.addWord(e.getTitle()));
        }
        return wordTree;
    }

    @Override
    public IPage<StopWord> findByCondition(StopWord stopWord, SearchVo searchVo, PageVo pageable) {

        QueryWrapper<StopWord> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda()
           .like(StrUtil.isNotBlank(stopWord.getTitle()),StopWord::getTitle ,stopWord.getTitle() );

        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(StopWord::getCreateTime, start, end);
        }
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);
    }

}
