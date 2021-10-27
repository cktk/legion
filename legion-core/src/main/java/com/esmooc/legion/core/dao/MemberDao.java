package com.esmooc.legion.core.dao;


import com.esmooc.legion.core.base.LegionBaseDao;
import com.esmooc.legion.core.entity.Member;

/**
 * 会员数据处理层
 * @author Daimao
 */
public interface MemberDao extends LegionBaseDao<Member, String> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    Member findByUsername(String username);

    /**
     * 通过手机获取用户
     * @param mobile
     * @return
     */
    Member findByMobile(String mobile);
}