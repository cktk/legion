package com.esmooc.legion.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.file.mapper.FileCategoryMapper;
import com.esmooc.legion.file.entity.FileCategory;
import com.esmooc.legion.file.service.FileCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件分类接口实现
 *
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class FileCategoryServiceImpl extends ServiceImpl<FileCategoryMapper, FileCategory>  implements FileCategoryService {

    @Autowired
    private FileCategoryMapper fileCategoryMapper;


    @Override
    public List<FileCategory> findByParentIdAndCreateBy(String parentId, String createBy) {

        return fileCategoryMapper.findByParentIdAndCreateByOrderBySortOrder(parentId, createBy);
    }

    @Override
    public List<FileCategory> findByTitleLikeAndCreateBy(String title, String createBy) {

        return fileCategoryMapper.findByTitleLikeAndCreateByOrderBySortOrder(title, createBy);
    }
}
