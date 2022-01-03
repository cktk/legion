package com.esmooc.legion.file.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.file.mapper.FileMapper;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 文件管理接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "file")
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {


    @Autowired
    private RedisTemplateHelper redisTemplate;


    @Cacheable(key = "#id")
    public File getFile(String id) {

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


}
