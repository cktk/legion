package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户数据处理层
 *
 * @author DaiMao
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    @Select("select * from t_user where username =#{username}")
    User findByUsername(String username);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    @Select("select * from t_user where mobile =#{mobile}")
    User findByMobile(String mobile);

    /**
     * 通过邮件获取用户
     *
     * @param email
     * @return
     */
    @Select("select * from t_user where email =#{email}")
    User findByEmail(String email);

    /**
     * 通过部门id获取
     *
     * @param departmentId
     * @return
     */
    @Select("select * from t_user where department_id =#{departmentId}")
    List<User> findByDepartmentId(String departmentId);

    /**
     * 通过用户名模糊搜索
     *
     * @param key
     * @param status
     * @return
     */
    @Select("select u from User u where u.username like CONCAT('%',#{param1},'%')   or u.nickname like %?1% and u.status = ?2")
    List<User> findByUsernameLikeAndStatus(String key, Integer status);

    /**
     * 更新部门名称
     *
     * @param departmentId
     * @param departmentTitle
     */
    @Update("update User u set u.departmentTitle=#{departmentTitle} where u.departmentId=#{departmentId}")
    void updateDepartmentTitle(@Param("departmentId") String departmentId, @Param("departmentTitle") String departmentTitle);
}
