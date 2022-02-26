package com.esmooc.legion.file.serviceimpl;

import com.esmooc.legion.file.dao.FileCategoryDao;
import com.esmooc.legion.file.entity.FileCategory;
import com.esmooc.legion.file.service.FileCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件分类接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class FileCategoryServiceImpl implements FileCategoryService {

    @Autowired
    private FileCategoryDao fileCategoryDao;

    @Override
    public FileCategoryDao getRepository() {
        return fileCategoryDao;
    }

    @Override
    public List<FileCategory> findByParentIdAndCreateBy(String parentId, String createBy) {

        return fileCategoryDao.findByParentIdAndCreateByOrderBySortOrder(parentId, createBy);
    }

    @Override
    public List<FileCategory> findByTitleLikeAndCreateBy(String title, String createBy) {

        return fileCategoryDao.findByTitleLikeAndCreateByOrderBySortOrder(title, createBy);
    }
}