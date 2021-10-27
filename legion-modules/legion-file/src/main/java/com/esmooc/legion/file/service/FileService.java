package com.esmooc.legion.file.service;

import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.file.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 文件管理接口
 *
 * @author Daimao
 */
public interface FileService extends LegionBaseService<File, String> {

    /**
     * 多条件获取列表
     *
     * @param file
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<File> findByCondition(File file, SearchVo searchVo, Pageable pageable);
}