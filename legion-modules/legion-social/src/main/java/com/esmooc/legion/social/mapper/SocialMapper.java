package com.esmooc.legion.social.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.social.entity.Social;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Github登录数据处理层
 *
 * @author Daimao
 */
@Mapper
public interface SocialMapper extends BaseMapper<Social> {

    /**
     * 通过openId和平台获取
     * @param openId
     * @param platform
     * @return
     */
    @Select("select * from t_social where open_id =#{openId} and platform=#{platform} ")
    Social findByOpenIdAndPlatform(String openId, Integer platform);

    /**
     * 通过userId和平台获取
     * @param relateUsername
     * @param platform
     * @return
     */

    @Select("select * from t_social where relate_username =#{relateUsername}  and platform =#{platform}")
    Social findByRelateUsernameAndPlatform(String relateUsername, Integer platform);

    /**
     * 通过relateUsername获取
     * @param relateUsername
     * @return
     */
    @Select("select * from t_social where relate_username =#{relateUsername} ")
    List<Social> findByRelateUsername(String relateUsername);
}
