package com.esmooc.legion.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.User;

import java.util.List;

/**
 * 用户接口
 *
 * @author DaiMao
 */
//@CacheConfig(cacheNames = "user")
public interface UserService extends IService<User> {

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 通过邮件和状态获取用户
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 多条件分页获取用户
     *
     * @param user
     * @param searchVo
     * @param pageable
     * @return
     */
    IPage<User> findByCondition(User user, SearchVo searchVo, PageVo pageable);

    /**
     * 通过部门id获取
     *
     * @param departmentId
     * @return
     */
    List<User> findByDepartmentId(String departmentId);

    /**
     * 通过用户名模糊搜索
     *
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameLikeAndStatus(String username, Integer status);

    /**
     * 更新部门名称
     *
     * @param departmentId
     * @param departmentTitle
     */
    void updateDepartmentTitle(String departmentId, String departmentTitle);
}
