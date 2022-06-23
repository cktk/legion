package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.AppMember;
import com.esmooc.legion.core.mapper.AppMemberMapper;
import com.esmooc.legion.core.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<AppMemberMapper, AppMember> implements MemberService {

    @Autowired
    private AppMemberMapper appMemberMapper;

    @Override
    public IPage<AppMember> findByCondition(AppMember appMember, SearchVo searchVo, PageVo pageable) {

     return this.page(PageUtil.initMpPage(pageable), Wrappers.query(appMember));

    }

    @Override
    public AppMember findByUsername(String username) {

        return appMemberMapper.findByUsername(username);
    }

    @Override
    public AppMember findByMobile(String mobile) {

        return appMemberMapper.findByMobile(mobile);
    }

}
