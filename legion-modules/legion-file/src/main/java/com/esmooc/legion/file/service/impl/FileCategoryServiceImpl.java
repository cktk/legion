package com.esmooc.legion.file.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.file.entity.FileCategory;
import com.esmooc.legion.file.mapper.FileCategoryMapper;
import com.esmooc.legion.file.service.FileCategoryService;
import lombok.extern.slf4j.Slf4j;
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



    @Override
    public List<FileCategory> findByParentIdAndCreateBy(String parentId, String createBy) {
        QueryWrapper<FileCategory> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(FileCategory::getParentId,parentId)
                .eq(StrUtil.isNotBlank(createBy),FileCategory::getCreateId,createBy).orderByAsc(FileCategory::getSortOrder);
        return this.list(queryWrapper);
    }

    @Override
    public List<FileCategory> findByTitleLikeAndCreateBy(String title, String createBy) {


        QueryWrapper<FileCategory> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().like(FileCategory::getParentId,title)
                .eq(StrUtil.isNotBlank(createBy),FileCategory::getCreateId,createBy).orderByAsc(FileCategory::getSortOrder);
        return this.list(queryWrapper);
    }
}
