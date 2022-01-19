package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.utils.PageUtils;
import com.esmooc.legion.core.entity.DictionariesEntity;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * @Author zqj
 * @Create: 2021-11-16 13:44
 * @Description: 查询接口
 */
public interface DictionariesService extends IService<DictionariesEntity> {

    /**
     * 列表查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 将查询出来的字段存放在缓存中
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Cacheable(value = "dictEhcache") //把数据缓存到本地
    String selectByFieldNameAndFieldValue(String fieldName,String fieldValue);

}
