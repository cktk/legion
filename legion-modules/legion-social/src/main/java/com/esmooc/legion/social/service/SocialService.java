package com.esmooc.legion.social.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.social.entity.Social;

import java.util.List;

/**
 * Github登录接口
 *
 * @author DaiMao
 */
public interface SocialService extends IService<Social> {

    /**
     * 通过openId和平台获取
     *
     * @param openId
     * @param platform
     * @return
     */
    Social findByOpenIdAndPlatform(String openId, Integer platform);

    /**
     * 通过userId和平台获取
     *
     * @param relateUsername
     * @param platform
     * @return
     */
    Social findByRelateUsernameAndPlatform(String relateUsername, Integer platform);

    /**
     * 通过relateUsername获取
     *
     * @param relateUsername
     * @return
     */
    List<Social> findByRelateUsername(String relateUsername);

    /**
     * 分页多条件获取
     *
     * @param social
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<Social> findByCondition(Social social, SearchVo searchVo, PageVo pageable);
}
