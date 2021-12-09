package com.esmooc.legion.classroom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.classroom.entity.TeacherInfo;
import com.esmooc.legion.classroom.service.ITeacherInfoService;
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
@Api(tags = "老师信息管理接口")
@RequestMapping("/teacherInfo")
@Transactional
public class TeacherInfoController {

    @Autowired
    private ITeacherInfoService iTeacherInfoService;

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<TeacherInfo> get(@PathVariable String id) {

        TeacherInfo teacherInfo = iTeacherInfoService.getById(id);
        return new ResultUtil<TeacherInfo>().setData(teacherInfo);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<TeacherInfo>> getAll() {

        List<TeacherInfo> list = iTeacherInfoService.list();
        return new ResultUtil<List<TeacherInfo>>().setData(list);
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<IPage<TeacherInfo>> getByPage(PageVo page) {

        IPage<TeacherInfo> data = iTeacherInfoService.page(PageUtil.initPage(page));
        return new ResultUtil<IPage<TeacherInfo>>().setData(data);
    }

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "编辑或更新数据")
    public Result<TeacherInfo> saveOrUpdate(TeacherInfo teacherInfo) {

        if (iTeacherInfoService.saveOrUpdate(teacherInfo)) {
            return new ResultUtil<TeacherInfo>().setData(teacherInfo);
        }
        return new ResultUtil<TeacherInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加数据")
    public Result<TeacherInfo> save(TeacherInfo teacherInfo) {

        if (iTeacherInfoService.saveOrUpdate(teacherInfo)) {
            return new ResultUtil<TeacherInfo>().setData(teacherInfo);
        }
        return new ResultUtil<TeacherInfo>().setErrorMsg("操作失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新数据")
    public Result<TeacherInfo> updateById(TeacherInfo teacherInfo) {

        if (iTeacherInfoService.updateById(teacherInfo)) {
            return new ResultUtil<TeacherInfo>().setData(teacherInfo);
        }
        return new ResultUtil<TeacherInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iTeacherInfoService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }




    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<TeacherInfo>> getByCondition(TeacherInfo teacherInfo,
                                                 PageVo page){

        teacherInfo.setId(null);
        IPage<TeacherInfo> data = iTeacherInfoService.page(PageUtil.initPage(page), Wrappers.query(teacherInfo));
        return new ResultUtil<IPage<TeacherInfo>>().setData(data);
    }
}
