package com.esmooc.legion.core.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.mapper.MembeMapper;
import com.esmooc.legion.core.entity.Member;
import com.esmooc.legion.core.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员接口实现
 *
 * @author DaiMao
 */
@Slf4j
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MembeMapper, Member> implements MemberService {

    @Autowired
    private MembeMapper membeMapper;

    @Override
    public IPage<Member> findByCondition(Member member, SearchVo searchVo, PageVo pageable) {

     return this.page(PageUtil.initMpPage(pageable), Wrappers.query(member));

    }

    @Override
    public Member findByUsername(String username) {

        return membeMapper.findByUsername(username);
    }

    @Override
    public Member findByMobile(String mobile) {

        return membeMapper.findByMobile(mobile);
    }

}
