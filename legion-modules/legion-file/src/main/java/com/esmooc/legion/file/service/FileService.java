package com.esmooc.legion.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.file.entity.File;



/**
 * 文件管理接口
 *
 * @author DaiMao
 */
public interface FileService extends IService<File> {

    /**
     * 多条件获取列表
     *
     * @param file
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<File> findByCondition(File file, SearchVo searchVo, PageVo pageable);

    /**
     * 通过categoryId删除
     *
     * @param categoryId
     */
    void deleteByCategoryId(String categoryId);
}
