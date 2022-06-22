package com.esmooc.legion.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.base.mapper.DictMapper;
import com.esmooc.legion.base.entity.Dict;
import com.esmooc.legion.base.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;


    @Override
    public List<Dict> findAllOrderBySortOrder() {

        return dictMapper.findAllOrderBySortOrder();
    }

    @Override
    public Dict findByType(String type) {

        return dictMapper.findByType(type);
    }

    @Override
    public List<Dict> findByTitleOrTypeLike(String key) {

        return dictMapper.findByTitleOrTypeLike(key);
    }
}
