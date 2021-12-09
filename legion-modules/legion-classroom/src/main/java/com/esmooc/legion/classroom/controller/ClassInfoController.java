package com.esmooc.legion.classroom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.classroom.entity.ClassInfo;
import com.esmooc.legion.classroom.service.IClassInfoService;
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
@Api(tags = "班级信息管理接口")
@RequestMapping("/classInfo")
@Transactional
public class ClassInfoController {

    @Autowired
    private IClassInfoService iClassInfoService;

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<ClassInfo> get(@PathVariable String id) {

        ClassInfo classInfo = iClassInfoService.getById(id);
        return new ResultUtil<ClassInfo>().setData(classInfo);
    }

    @PostMapping("/saveBatch")
    @ApiOperation(value = "批量添加数据")
    public Result<List<ClassInfo>> saveBatch(List<ClassInfo> ClassInfos) {

        if (iClassInfoService.saveBatch(ClassInfos)) {
            return new ResultUtil<List<ClassInfo>>().setData(ClassInfos);
        }
        return new ResultUtil<List<ClassInfo>>().setErrorMsg("操作失败");
    }


    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<ClassInfo>> getAll() {

        List<ClassInfo> list = iClassInfoService.list();
        return new ResultUtil<List<ClassInfo>>().setData(list);
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<IPage<ClassInfo>> getByPage(PageVo page) {

        IPage<ClassInfo> data = iClassInfoService.page(PageUtil.initPage(page));
        return new ResultUtil<IPage<ClassInfo>>().setData(data);
    }

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "编辑或更新数据")
    public Result<ClassInfo> saveOrUpdate(ClassInfo classInfo) {

        if (iClassInfoService.saveOrUpdate(classInfo)) {
            return new ResultUtil<ClassInfo>().setData(classInfo);
        }
        return new ResultUtil<ClassInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加数据")
    public Result<ClassInfo> save(ClassInfo classInfo) {

        if (iClassInfoService.saveOrUpdate(classInfo)) {
            return new ResultUtil<ClassInfo>().setData(classInfo);
        }
        return new ResultUtil<ClassInfo>().setErrorMsg("操作失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新数据")
    public Result<ClassInfo> updateById(ClassInfo classInfo) {

        if (iClassInfoService.updateById(classInfo)) {
            return new ResultUtil<ClassInfo>().setData(classInfo);
        }
        return new ResultUtil<ClassInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iClassInfoService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }




    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<ClassInfo>> getByCondition(ClassInfo classInfo,
                                                 PageVo page){
        classInfo.setId(null);
        IPage<ClassInfo> data = iClassInfoService.page(PageUtil.initPage(page), Wrappers.query(classInfo));
        return new ResultUtil<IPage<ClassInfo>>().setData(data);
    }
}
