package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.StopWord;



/**
 * 禁用词管理接口
 *
 * @author DaiMao
 */
public interface StopWordService extends IService<StopWord> {

    /**
     * 多条件分页获取
     *
     * @param stopWord
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<StopWord> findByCondition(StopWord stopWord, SearchVo searchVo, PageVo pageable);

}
