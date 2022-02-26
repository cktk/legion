package com.esmooc.legion.core.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.StopWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 禁用词管理接口
 *
 * @author DaiMao
 */
public interface StopWordService extends LegionBaseService<StopWord, String> {

    /**
     * 多条件分页获取
     *
     * @param stopWord
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<StopWord> findByCondition(StopWord stopWord, SearchVo searchVo, Pageable pageable);

}
