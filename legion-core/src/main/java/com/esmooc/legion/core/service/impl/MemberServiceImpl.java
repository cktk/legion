package com.esmooc.legion.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Member;
import com.esmooc.legion.core.mapper.MemberMapper;
import com.esmooc.legion.core.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员接口实现
 *
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member findByUsername(String username) {

        return memberMapper.findByUsername(username);
    }

    @Override
    public Member findByMobile(String mobile) {

        return memberMapper.findByMobile(mobile);
    }

}
