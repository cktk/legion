package com.esmooc.legion.social.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.social.entity.Social;
import com.esmooc.legion.social.mapper.SocialMapper;
import com.esmooc.legion.social.service.SocialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Github登录接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class SocialServiceImpl extends ServiceImpl<SocialMapper, Social> implements SocialService {

    @Autowired
    private SocialMapper socialMapper;


    @Override
    public Social findByOpenIdAndPlatform(String openId, Integer platform) {

        return socialMapper.findByOpenIdAndPlatform(openId, platform);
    }

    @Override
    public Social findByRelateUsernameAndPlatform(String relateUsername, Integer platform) {

        return socialMapper.findByRelateUsernameAndPlatform(relateUsername, platform);
    }

    @Override
    public List<Social> findByRelateUsername(String relateUsername) {

        return socialMapper.findByRelateUsername(relateUsername);
    }


}
