package com.esmooc.legion.classroom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.classroom.entity.StudentInfo;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.classroom.entity.CourseInfo;
import com.esmooc.legion.classroom.service.ICourseInfoService;
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
@Api(tags = "课程信息管理接口")
@RequestMapping("/courseInfo")
@Transactional
public class CourseInfoController {

    @Autowired
    private ICourseInfoService iCourseInfoService;

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<CourseInfo> get(@PathVariable String id) {

        CourseInfo courseInfo = iCourseInfoService.getById(id);
        return new ResultUtil<CourseInfo>().setData(courseInfo);
    }
    @PostMapping("/saveBatch")
    @ApiOperation(value = "批量添加数据")
    public Result<List<CourseInfo>> saveBatch(List<CourseInfo> CourseInfos) {

        if (iCourseInfoService.saveBatch(CourseInfos)) {
            return new ResultUtil<List<CourseInfo>>().setData(CourseInfos);
        }
        return new ResultUtil<List<CourseInfo>>().setErrorMsg("操作失败");
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<CourseInfo>> getAll() {

        List<CourseInfo> list = iCourseInfoService.list();
        return new ResultUtil<List<CourseInfo>>().setData(list);
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<IPage<CourseInfo>> getByPage(PageVo page) {

        IPage<CourseInfo> data = iCourseInfoService.page(PageUtil.initPage(page));
        return new ResultUtil<IPage<CourseInfo>>().setData(data);
    }

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "编辑或更新数据")
    public Result<CourseInfo> saveOrUpdate(CourseInfo courseInfo) {

        if (iCourseInfoService.saveOrUpdate(courseInfo)) {
            return new ResultUtil<CourseInfo>().setData(courseInfo);
        }
        return new ResultUtil<CourseInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加数据")
    public Result<CourseInfo> save(CourseInfo courseInfo) {

        if (iCourseInfoService.saveOrUpdate(courseInfo)) {
            return new ResultUtil<CourseInfo>().setData(courseInfo);
        }
        return new ResultUtil<CourseInfo>().setErrorMsg("操作失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新数据")
    public Result<CourseInfo> updateById(CourseInfo courseInfo) {

        if (iCourseInfoService.updateById(courseInfo)) {
            return new ResultUtil<CourseInfo>().setData(courseInfo);
        }
        return new ResultUtil<CourseInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iCourseInfoService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }




    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<CourseInfo>> getByCondition(CourseInfo courseInfo,
                                                 PageVo page){
        courseInfo.setId(null);
        IPage<CourseInfo> data = iCourseInfoService.page(PageUtil.initPage(page), Wrappers.query(courseInfo));
        return new ResultUtil<IPage<CourseInfo>>().setData(data);
    }
}
