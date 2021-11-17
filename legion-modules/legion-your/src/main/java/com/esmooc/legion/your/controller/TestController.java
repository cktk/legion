package com.esmooc.legion.your.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.your.entity.Test;
import com.esmooc.legion.your.service.ITestService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "测试管理接口")
@RequestMapping("/test/v1")
@Transactional
public class TestController {

    @Autowired
    private ITestService iTestService;

    @GetMapping()
    @ApiOperation(value = "获取全部数据")
    public Result<List<Test>> getAll() {

        List<Test> list = iTestService.list();
        return new ResultUtil<List<Test>>().setData(list);
    }

    @PostMapping()
    @ApiOperation(value = "添加数据")
    public Result<Test> save(Test test) {

        if (iTestService.saveOrUpdate(test)) {
            return new ResultUtil<Test>().setData(test);
        }
        return new ResultUtil<Test>().setErrorMsg("操作失败");
    }

    @PutMapping()
    @ApiOperation(value = "更新数据")
    public Result<Test> updateById(Test test) {

        if (iTestService.updateById(test)) {
            return new ResultUtil<Test>().setData(test);
        }
        return new ResultUtil<Test>().setErrorMsg("操作失败");
    }

    @DeleteMapping()
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iTestService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }


    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<Test>> getByCondition(Test test,
                                                 PageVo page){
        IPage<Test> data = iTestService.page(PageUtil.initMpPage(page), Wrappers.query(test));
        return new ResultUtil<IPage<Test>>().setData(data);
    }
}
