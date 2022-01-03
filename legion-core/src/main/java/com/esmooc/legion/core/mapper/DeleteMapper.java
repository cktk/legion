package com.esmooc.legion.core.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @author Daimao
 */
public interface DeleteMapper {
    /**
     * 关联删除社交账号
     * @param username
     */
    @Delete(" DELETE FROM t_social WHERE relate_username = #{username}")
    void deleteSocial(@Param("username") String username);

}
