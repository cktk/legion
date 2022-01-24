package com.esmooc.legion.base.service.impl;

import com.esmooc.legion.base.mapper.CityMapper;
import com.esmooc.legion.base.entity.City;
import com.esmooc.legion.base.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址接口实现
 * @author Daimao
 */
@Slf4j
@Service
@Transactional
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    private CityMapper cityMapper;
}
