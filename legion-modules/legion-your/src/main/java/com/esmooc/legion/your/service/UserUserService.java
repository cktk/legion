package com.esmooc.legion.your.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.your.entity.UserUser;

import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/02/10/ 15:38
 * @Description:
 */
public interface UserUserService extends IService<UserUser> {

    UserUser getByIdCardMax(String idCard);

    String getCurrentVal();
    void updateCurrentVal();

    IPage<UserUser> getGynecologicalUser(String startTime, String endTime, String input, String diagnose, Page page);

    IPage<UserUser> getTctUser(String startTime, String endTime, String input, String tct, Page initMpPage);


    IPage<UserUser> getMuBaUserNew(String startTime, String endTime, String input, String mubale, String colorle, Page initMpPage);

    IPage<UserUser> getBreastUserNew(String startTime, String endTime, String input, String mubale, String colorle, Page initMpPage);

    IPage<UserUser> getMuBaAndBreastUserNew(String startTime, String endTime, String input,Page page);

}
