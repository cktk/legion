package com.esmooc.legion.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.base.mapper.DictDataMapper;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.core.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典数据接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class DictDataServiceImpl  extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

    @Autowired
    private DictDataMapper dictDataMapper;



    @Override
    public List<DictData> findByDictId(String dictId) {

        return dictDataMapper.findByDictIdAndStatusOrderBySortOrder(dictId, CommonConstant.STATUS_NORMAL);
    }

    @Override
    public void deleteByDictId(String dictId) {

        dictDataMapper.deleteById(dictId);
    }
}
