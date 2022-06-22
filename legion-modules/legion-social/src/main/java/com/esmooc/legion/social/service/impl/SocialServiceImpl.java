package com.esmooc.legion.social.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.social.entity.Social;
import com.esmooc.legion.social.mapper.SocialMapper;
import com.esmooc.legion.social.service.SocialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Github登录接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class SocialServiceImpl extends ServiceImpl<SocialMapper, Social>  implements SocialService {

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

    @Override
    public IPage<Social> findByCondition(Social social, SearchVo searchVo, PageVo pageable) {


        QueryWrapper<Social> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda()
                .like(StrUtil.isNotBlank(social.getUsername()),Social::getUsername ,social.getUsername() ).or()
                .like(StrUtil.isNotBlank(social.getRelateUsername()),Social::getRelateUsername ,social.getRelateUsername() ).or()
                .eq(social.getPlatform()!=null,Social::getPlatform ,social.getPlatform() );

        if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
            Date start = DateUtil.parse(searchVo.getStartDate());
            Date end = DateUtil.parse(searchVo.getEndDate());
            queryWrapper.lambda().between(Social::getCreateTime, start, end);
        }

        return this.page(PageUtil.initMpPage(pageable),queryWrapper);

    }
}
