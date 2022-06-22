package com.esmooc.legion.file.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.file.mapper.FileMapper;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 文件管理接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "file")
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private RedisTemplateHelper redisTemplate;



    @Cacheable(key = "#id")
    public File get(String id) {

        // 避免缓存穿透
        String result = redisTemplate.get("file::" + id);
        if ("null".equals(result)) {
            return null;
        }
        File file = this.getById(id);
        if (file == null) {
            redisTemplate.set("file::" + id, "null", 5L, TimeUnit.MINUTES);
        }
        return file;
    }

    @Override
    public IPage<File> findByCondition(File file, SearchVo searchVo, PageVo pageable) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(file.getName()),File::getName ,file.getName() ).or()
                .like(StrUtil.isNotBlank(file.getFKey()),File::getFKey ,file.getFKey() ).or()
                .like(StrUtil.isNotBlank(file.getType()),File::getType ,file.getType() ).or()
                .like(StrUtil.isNotBlank(file.getCreateBy()),File::getCreateBy ,file.getCreateBy() ).or()
                .eq(file.getLocation()!=null,File::getLocation ,file.getLocation() ).or()
                .like(StrUtil.isNotBlank(file.getCategoryId()),File::getCategoryId ,file.getCategoryId() );

        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(File::getCreateTime, start, end);
        }
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);



    }

    @Override
    public void deleteByCategoryId(String categoryId) {

        fileMapper.deleteByCategoryId(categoryId);
    }
}
