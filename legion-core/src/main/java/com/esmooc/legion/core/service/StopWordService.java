package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.StopWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 禁用词管理接口
 * @author Daimao
 */
public interface StopWordService extends IService<StopWord> {
}
