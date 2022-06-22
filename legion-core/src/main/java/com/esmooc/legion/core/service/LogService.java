package com.esmooc.legion.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Log;


/**
 * 日志接口
 *
 * @author DaiMao
 */
public interface LogService extends IService<Log> {

    /**
     * 分页搜索获取日志
     *
     * @param type
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<Log> findByConfition(Integer type, String key, SearchVo searchVo, PageVo pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
