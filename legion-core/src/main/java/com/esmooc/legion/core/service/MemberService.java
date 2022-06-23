package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.AppMember;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;



/**
 * 会员服务
 * 会员接口
 *
 * @author DaiMao
 * @date 2022/06/22
 */
@CacheConfig(cacheNames = "member")
public interface MemberService extends IService<AppMember> {

    /**
     * 多条件分页获取
     *
     * @param appMember
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<AppMember> findByCondition(AppMember appMember, SearchVo searchVo, PageVo pageable);

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    @Cacheable(key = "#username")
    AppMember findByUsername(String username);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    AppMember findByMobile(String mobile);
}
