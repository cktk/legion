
package com.esmooc.legion.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.base.entity.City;

import java.util.List;

/**
 * @Author 呆猫
 *
 * @Date: 2022/02/16/ 15:06
 * @Description:
 */
public interface CityService extends IService<City>{


    City findByIdCard(String idcard);

    List<City> findByAllName(String allName);

}
