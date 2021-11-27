package com.esmooc.legion.core.service.impl;

import cn.hutool.dfa.WordTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.entity.StopWord;
import com.esmooc.legion.core.mapper.StopWordMapper;
import com.esmooc.legion.core.service.StopWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 禁用词管理接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class StopWordServiceImpl extends ServiceImpl<StopWordMapper, StopWord> implements StopWordService {

    @Autowired
    private StopWordMapper stopWordMapper;

    private static WordTree wordTree;

    public WordTree getInstance() {

        if (wordTree == null) {
            // 初始加载数据
            wordTree = new WordTree();
            stopWordMapper.selectList(new QueryWrapper<>()).forEach(e -> wordTree.addWord(e.getTitle()));
        }
        return wordTree;
    }

}
