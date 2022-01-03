package com.esmooc.legion.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户数据处理层
 *
 * @author Daimao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Select("select * from t_user where  username =#{username} ")
    User findByUsername(String username);

    /**
     * 通过手机获取用户
     * @param mobile
     * @return
     */
    @Select("select * from t_user where  mobile =#{mobile} ")
    User findByMobile(String mobile);

    /**
     * 通过邮件获取用户
     * @param email
     * @return
     */
    @Select("select * from t_user where  email =#{email} ")
    User findByEmail(String email);

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    @Select("select * from t_user where  department_id =#{departmentId} ")
    List<User> findByDepartmentId(String departmentId);

    /**
     * 通过用户名模糊搜索
     * @param key
     * @param status
     * @return
     */
    @Select("select * from t_user u where u.username like '%'#{key}'%' or u.nickname like '%'#{key}'%' and u.status = #{status}")
    List<User> findByUsernameLikeAndStatus(String key, Integer status);

    /**
     * 更新部门名称
     * @param departmentId
     * @param departmentTitle
     */
    @Update("update t_user u set u.department_title=#{departmentTitle} where u.department_id=#{departmentId}")
    void updateDepartmentTitle(@Param("departmentId") String departmentId, @Param("departmentTitle") String departmentTitle);
}
