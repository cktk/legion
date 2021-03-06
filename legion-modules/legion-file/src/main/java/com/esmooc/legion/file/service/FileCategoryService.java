package com.esmooc.legion.file.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.file.entity.FileCategory;

import java.util.List;

/**
 * 文件分类接口
 *
 * @author Exrick
 */
public interface FileCategoryService extends IService<FileCategory> {


    /**
     * 通过父id和创建人获取
     *
     * @param parentId
     * @param createById
     * @return
     */
    List<FileCategory> findByParentIdAndCreateBy(String parentId, String createById);

    /**
     * 通过名称和创建人模糊搜索
     *
     * @param title
     * @param createById
     * @return
     */
    List<FileCategory> findByTitleLikeAndCreateBy(String title, String createById);
}
