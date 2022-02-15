package com.esmooc.legion.your.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.mapper.UserDeadlineMapper;
import com.esmooc.legion.your.entity.UserDeadline;
import com.esmooc.legion.your.service.UserDeadlineService;
/**
 * @Author 呆猫
 *
 * @Date: 2022/02/10/ 16:25
 * @Description:
 */
@Service
public class UserDeadlineServiceImpl extends ServiceImpl<UserDeadlineMapper, UserDeadline> implements UserDeadlineService{


    @Override
    public UserDeadline getByUserId(int userId) {
        return this.getOne(new QueryWrapper<UserDeadline>().lambda().eq(UserDeadline::getUserId, userId),false);
    }
}
