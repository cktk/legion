package com.esmooc.legion.file.service;


import com.esmooc.legion.core.base.LegionBaseService;
import com.esmooc.legion.file.entity.FileCategory;

import java.util.List;

/**
 * 文件分类接口
 *
 * @author Exrick
 */
public interface FileCategoryService extends LegionBaseService<FileCategory, String> {


    /**
     * 通过父id和创建人获取
     *
     * @param parentId
     * @param createBy
     * @return
     */
    List<FileCategory> findByParentIdAndCreateBy(String parentId, String createBy);

    /**
     * 通过名称和创建人模糊搜索
     *
     * @param title
     * @param createBy
     * @return
     */
    List<FileCategory> findByTitleLikeAndCreateBy(String title, String createBy);
}