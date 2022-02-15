package com.esmooc.legion.your.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.your.entity.UserDeadline;

/**
 * @Author 呆猫
 * @Date: 2022/02/10/ 16:25
 * @Description:
 */
public interface UserDeadlineService extends IService<UserDeadline> {


    UserDeadline getByUserId(int userId);

}
