package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.StuPdfCourseVO;
import com.esmooc.legion.edu.service.CourseService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "社会课程")
@RestController
@RequestMapping("/edu/pdfCourse")
public class PDFCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程列表")
    @GetMapping("/list")
    public Result<IPage<Course>> list(Course course, PageVo page) {
        course.setCourseType(Constants.SOCIETYCOURSE);
        IPage<Course> list = courseService.page(PageUtil.initPage(page), Wrappers.query(course));
        return ResultUtil.data(list);
    }

    @ApiOperation(value = "获取课程详细信息")
    @GetMapping(value = "/{id}")
    public Result<Course> getInfo(@PathVariable("id") String id) {
        return ResultUtil.data(courseService.getById(id));
    }


    @ApiOperation(value = "新增课程")
    @SystemLog(description = "新增课程", type = LogType.EDU)
    @PostMapping
    public Result<Boolean> add(@RequestBody Course course) {
        course.setCourseType(Constants.SOCIETYCOURSE);
        course.setCreateBy(securityUtil.getCurrUser().getId());
        if (Constants.CITY.equals(securityUtil.getCurrUser().getType())) {
            course.setAudit(Constants.UNREVISED);
        } else {
            course.setAudit(Constants.UNREVISED_NO);
        }
        return ResultUtil.data(courseService.save(course));
    }


    @ApiOperation(value = "修改课程")
    @SystemLog(description = "修改课程", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody Course course) {
        course.setCourseType(Constants.SOCIETYCOURSE);
        course.setUpdateBy(securityUtil.getCurrUser().getId());
        return ResultUtil.data(courseService.updateById(course));
    }


    @ApiOperation(value = "删除课程")
    @SystemLog(description = "删除课程", type = LogType.EDU)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable ArrayList<String> ids) {
        return ResultUtil.data(courseService.removeByIds(ids));
    }


    @ApiOperation(value = "查询学员课程列表")
    @GetMapping("/stuPdfCourseList")
    public Result<IPage<StuPdfCourseVO>> stuVideoCourseList(StuPdfCourseVO stuPdfCourse, PageVo page) {
        stuPdfCourse.setCourseType(Constants.SOCIETYCOURSE + "");
        IPage<StuPdfCourseVO> list = courseService.stuPdfCourseList(stuPdfCourse, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }
}
