package com.esmooc.legion.core.service;


import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author DaiMao
 */
public interface LogService extends LegionBaseService<Log, String> {

    /**
     * 分页搜索获取日志
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> findByConfition(Integer type, String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
