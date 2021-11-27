package com.esmooc.legion.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员数据处理层
 * @author Daimao
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Select("select * from app_member where username=#{username} ")
    Member findByUsername(String username);

    /**
     * 通过手机获取用户
     * @param mobile
     * @return
     */
    @Select("select * from app_member where mobile=#{mobile} ")
    Member findByMobile(String mobile);
}
