package com.esmooc.legion.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Member;
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
public interface MemberService extends IService<Member> {

    /**
     * 多条件分页获取
     *
     * @param member
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<Member> findByCondition(Member member, SearchVo searchVo, PageVo pageable);

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    @Cacheable(key = "#username")
    Member findByUsername(String username);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    Member findByMobile(String mobile);
}
