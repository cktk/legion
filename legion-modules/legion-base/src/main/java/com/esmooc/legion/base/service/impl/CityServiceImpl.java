
package com.esmooc.legion.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.base.entity.City;
import com.esmooc.legion.base.mapper.CityMapper;
import com.esmooc.legion.base.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/02/16/ 15:06
 * @Description:
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public City findByIdCard(String idcard) {
        QueryWrapper<City> cityQueryWrapper = new QueryWrapper<>();
        cityQueryWrapper.lambda().eq(City::getAreaCode,idcard.substring(0, 6));
        List<City> cities = this.list(cityQueryWrapper);
        if (cities!=null && cities.size()==1){
            return cities.get(0);
        }
        return null;
    }

    @Override
    public List<City> findByAllName(String allName) {
        QueryWrapper<City> cityQueryWrapper = new QueryWrapper<>();
        cityQueryWrapper.lambda().likeRight(City::getAreaName,allName);
        cityQueryWrapper.lambda().last("limit 5");
        return this.list(cityQueryWrapper);
    }
}
