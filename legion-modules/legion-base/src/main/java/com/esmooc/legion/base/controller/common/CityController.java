package com.esmooc.legion.base.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.base.common.utils.Address;
import com.esmooc.legion.base.common.utils.AddressResolutionUtil;
import com.esmooc.legion.base.entity.City;
import com.esmooc.legion.base.service.CityService;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/02/16/ 16:46
 * @Description:
 */


@Slf4j
@RestController
@Api(tags = "地址")
@RequestMapping("/legion/city")
public class CityController {

    @Autowired
    private CityService cityService;


    @GetMapping("/{pid}")
    @ApiOperation(value = "通过地址父id获取地址列表")
    public Result<List<City>> getByPid( @PathVariable String pid) {
        return ResultUtil.data(cityService.list(new QueryWrapper<City>().lambda().eq(City::getParentId, pid)));
    }

    @GetMapping()
    @ApiOperation(value = "id获取详情")
    public Result<City> getById(String id) {
        return ResultUtil.data(cityService.getById(id));
    }

    @PostMapping()
    @ApiOperation(value = "保存")
    public Result<Boolean> save(City city) {
        return ResultUtil.data(cityService.save(city));
    }

    @PutMapping()
    @ApiOperation(value = "通过id修改")
    public Result<Boolean> updateById(City city) {
        return ResultUtil.data(cityService.updateById(city));
    }




}
