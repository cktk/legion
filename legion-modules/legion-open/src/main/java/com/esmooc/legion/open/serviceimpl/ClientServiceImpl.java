package com.esmooc.legion.open.serviceimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.open.dao.ClientMapper;
import com.esmooc.legion.open.entity.Client;
import com.esmooc.legion.open.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 客户端接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames = "client")
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

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
        Client client = this.getById(id);
        if (client == null) {
            redisTemplate.set("client::" + id, "null", 5L, TimeUnit.MINUTES);
        }
        return client;
    }

    @CacheEvict(key = "#client.id")
    public Client update(Client client) {
        if (this.updateById(client)){
            return client;
        }
        return null;
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {

        clientMapper.deleteById(id);
    }

    @Override
    public IPage<Client> findByCondition(Client client, SearchVo searchVo, PageVo pageable) {

        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(client.getName()),Client::getName ,client.getName() ).or()
                .like(StrUtil.isNotBlank(client.getHomeUri()),Client::getHomeUri ,client.getHomeUri() );
        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(Client::getCreateTime, start, end);
        }
        return this.page(PageUtil.initMpPage(pageable),queryWrapper);

    }

}
