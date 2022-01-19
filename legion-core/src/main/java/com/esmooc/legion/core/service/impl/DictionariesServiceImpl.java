package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtils;
import com.esmooc.legion.core.entity.DictionariesEntity;
import com.esmooc.legion.core.mapper.DictionariesMapper;
import com.esmooc.legion.core.service.DictionariesService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author zqj
 * @Create: 2021-11-16 14:01
 * @Description TODO
 */
@Service
public class DictionariesServiceImpl extends ServiceImpl<DictionariesMapper, DictionariesEntity> implements DictionariesService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public String selectByFieldNameAndFieldValue(String fieldName, String fieldValue) {
        DictionariesEntity entity = this.baseMapper.selectByFieldNameAndFieldValue(fieldName, fieldValue);
        if(entity ==null){
            return "无翻译词汇";
        }
        return entity.getDes();
    }
}
