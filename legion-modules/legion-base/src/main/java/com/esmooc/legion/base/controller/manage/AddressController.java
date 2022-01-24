package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.base.entity.City;
import com.esmooc.legion.base.service.ICityService;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/20/ 14:00
 * @Description:
 */

@Slf4j
@RestController
@Api(tags = "地址")
@RequestMapping("/legion/address")
@CacheConfig(cacheNames = "address")
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    @Autowired
    private ICityService iCityService;

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<City> get(@PathVariable Long id) {
        City city = iCityService.getById(id);
        return new ResultUtil<City>().setData(city);
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "通过父id获取")
    public Result<List<City>> list(@PathVariable Long id) {
        return  ResultUtil.data(iCityService.list(new QueryWrapper<City>().lambda().eq(City::getParentId, id)));
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<City>> getAll() {

        List<City> list = iCityService.list();
        return new ResultUtil<List<City>>().setData(list);
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<IPage<City>> getByPage(PageVo page) {

        IPage<City> data = iCityService.page(PageUtil.initPage(page));
        return new ResultUtil<IPage<City>>().setData(data);
    }

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "编辑或更新数据")
    public Result<City> saveOrUpdate(City city) {

        if (iCityService.saveOrUpdate(city)) {
            return new ResultUtil<City>().setData(city);
        }
        return new ResultUtil<City>().setErrorMsg("操作失败");
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加数据")
    public Result<City> save(City city) {

        if (iCityService.saveOrUpdate(city)) {
            return new ResultUtil<City>().setData(city);
        }
        return new ResultUtil<City>().setErrorMsg("操作失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新数据")
    public Result<City> updateById(City city) {

        if (iCityService.updateById(city)) {
            return new ResultUtil<City>().setData(city);
        }
        return new ResultUtil<City>().setErrorMsg("操作失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam Long[] ids) {

        for (Long id : ids) {
            iCityService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }


    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<City>> getByCondition(City city,
                                              PageVo page) {
        IPage<City> list = iCityService.page(PageUtil.initPage(page), Wrappers.query(city));
        return new ResultUtil<IPage<City>>().setData(list);
    }


}
