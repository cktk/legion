package com.esmooc.legion.open.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.open.mapper.ClientMapper;
import com.esmooc.legion.open.entity.Client;
import com.esmooc.legion.open.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 客户端接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "client")
public class ClientServiceImpl  extends ServiceImpl<ClientMapper, Client> implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private RedisTemplateHelper redisTemplate;


    @Cacheable(key = "#id")
    public Client get(String id) {

        // 避免缓存穿透
        String result = redisTemplate.get("client::" + id);
        if ("null".equals(result)) {
            return null;
        }
        Client client = clientMapper.selectById(id);
        if (client == null) {
            redisTemplate.set("client::" + id, "null", 5L, TimeUnit.MINUTES);
        }
        return client;
    }



}
