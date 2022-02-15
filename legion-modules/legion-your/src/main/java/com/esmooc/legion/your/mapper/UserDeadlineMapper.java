package com.esmooc.legion.your.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esmooc.legion.your.entity.UserDeadline;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 呆猫
 *
 * @Date: 2022/02/10/ 16:25
 * @Description: 
 */
@Mapper
@DS("screening2")
public interface UserDeadlineMapper extends BaseMapper<UserDeadline> {
}