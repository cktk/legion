package com.esmooc.legion.your.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.your.entity.UserUser;
import com.esmooc.legion.your.mapper.UserUserMapper;
import com.esmooc.legion.your.service.UserUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 呆猫
 *
 * @Date: 2022/02/10/ 15:38
 * @Description:
 */
@Service
@AllArgsConstructor
public class UserUserServiceImpl extends ServiceImpl<UserUserMapper, UserUser> implements UserUserService {

    private final UserUserMapper userUserMapper;

    @Override
    public UserUser getByIdCardMax(String idCard) {
        return userUserMapper.getByIdCardMax(idCard);
    }

    @Override
    public String getCurrentVal() {
        return userUserMapper.getCurrentVal();
    }

    @Override
    public void updateCurrentVal() {
        userUserMapper.updateCurrentVal();
    }

    @Override
    public IPage<UserUser> getGynecologicalUser(String startTime, String endTime, String input, String diagnose, Page page) {
        return userUserMapper.getGynecologicalUser(startTime,endTime,input,diagnose,page);
    }

    @Override
    public IPage<UserUser> getTctUser(String startTime, String endTime, String input, String tct, Page page) {
        return userUserMapper.getTctUser(startTime,endTime,input,tct,page);
    }

    @Override
    public IPage<UserUser> getMuBaAndBreastUserNew(String startTime, String endTime, String input,Page page) {
        return userUserMapper.getMuBaAndBreastUserNew(startTime,endTime,input,page);
    }

    @Override
    public IPage<UserUser> getMuBaUserNew(String startTime, String endTime, String input, String mubale, String colorle, Page page) {
        return userUserMapper.getMuBaUserNew(startTime,endTime,input,mubale,colorle,page);
    }

    @Override
    public IPage<UserUser> getBreastUserNew(String startTime, String endTime, String input, String mubale, String colorle, Page page) {
        return userUserMapper.getBreastUserNew( startTime,  endTime,  input,  mubale,  colorle,  page);
    }
}
