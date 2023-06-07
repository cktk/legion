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
import com.esmooc.legion.file.entity.LegionFile;
import com.esmooc.legion.file.mapper.FileMapper;
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
public class FileServiceImpl extends ServiceImpl<FileMapper, LegionFile> implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private RedisTemplateHelper redisTemplate;



    @Cacheable(key = "#id")
    public LegionFile get(String id) {

        // 避免缓存穿透
        String result = redisTemplate.get("LegionFile::" + id);
        if ("null".equals(result)) {
            return null;
        }
        LegionFile legionFile = this.getById(id);
        if (legionFile == null) {
            redisTemplate.set("LegionFile::" + id, "null", 5L, TimeUnit.MINUTES);
        }
        return legionFile;
    }

    @Override
    public IPage<LegionFile> findByCondition(LegionFile legionFile, SearchVo searchVo, PageVo pageable) {
        QueryWrapper<LegionFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(legionFile.getName()), LegionFile::getName , legionFile.getName() ).or()
                .like(StrUtil.isNotBlank(legionFile.getFKey()), LegionFile::getFKey , legionFile.getFKey() ).or()
                .like(StrUtil.isNotBlank(legionFile.getType()), LegionFile::getType , legionFile.getType() ).or()
                .like(StrUtil.isNotBlank(legionFile.getCreateBy()), LegionFile::getCreateBy , legionFile.getCreateBy() ).or()
                .eq(legionFile.getLocation()!=null, LegionFile::getLocation , legionFile.getLocation() ).or()
                .like(StrUtil.isNotBlank(legionFile.getCategoryId()), LegionFile::getCategoryId , legionFile.getCategoryId() );

        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(LegionFile::getCreateTime, start, end);
        }
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);



    }

    @Override
    public void deleteByCategoryId(String categoryId) {

        fileMapper.deleteByCategoryId(categoryId);
    }
}
