package com.esmooc.legion.open.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.open.common.constant.Constants;
import com.esmooc.legion.open.entity.Course;
import com.esmooc.legion.open.entity.vo.CourseVO;
import com.esmooc.legion.open.entity.vo.StuPdfCourseVO;
import com.esmooc.legion.open.service.CourseService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "课程")
@RestController
@RequestMapping("/edu/pdfCourse")
public class PDFCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程列表")
    @GetMapping("/list")
    public Result<IPage<CourseVO>> list(CourseVO bizCourse, PageVo page) {
        bizCourse.setCourseType(Constants.SOCIETYCOURSE + "");
        IPage<CourseVO> list = courseService.selectBizCourseVOList(bizCourse, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }

    @ApiOperation(value = "导出课程列表")
    @SystemLog(description = "导出课程列表", type = LogType.EDU)
    @GetMapping("/export")
    @ResponseExcel()
    public List<CourseVO> export(CourseVO bizCourse, PageVo page) {
        bizCourse.setCourseType(Constants.SOCIETYCOURSE + "");
        IPage<CourseVO> list = courseService.selectBizCourseVOList(bizCourse, PageUtil.initPage(page));
        return list.getRecords();
    }


    @ApiOperation(value = "获取课程详细信息")
    @GetMapping(value = "/{id}")
    public Result<Course> getInfo(@PathVariable("id") String id) {
        return ResultUtil.data(courseService.selectBizCourseById(id));
    }


    @ApiOperation(value = "新增课程")
    @SystemLog(description = "新增课程", type = LogType.EDU)
    @PostMapping
    public Result<Integer> add(@RequestBody Course course) {
        course.setCourseType(Constants.SOCIETYCOURSE);
        course.setCreateBy(securityUtil.getCurrUser().getId());
        if (Constants.CITY.equals(securityUtil.getCurrUser().getType())) {
            course.setDelFlag(Constants.UNREVISED);
        } else {
            course.setDelFlag(Constants.ISNOTDELETE);
        }
        return ResultUtil.data(courseService.insertBizCourse(course));
    }


    @ApiOperation(value = "修改课程")
    @SystemLog(description = "修改课程", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody Course course) {
        course.setCourseType(Constants.SOCIETYCOURSE);
        course.setUpdateBy(securityUtil.getCurrUser().getId());
        return ResultUtil.data(courseService.updateBizCourse(course));
    }


    @ApiOperation(value = "删除课程")
    @SystemLog(description = "删除课程", type = LogType.EDU)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids) {
        return ResultUtil.data(courseService.deleteBizCourseByIds(ids));
    }


    @ApiOperation(value = "查询学员课程列表")
    @GetMapping("/stuPdfCourseList")
    public Result<IPage<StuPdfCourseVO>> stuVideoCourseList(StuPdfCourseVO stuPdfCourse, PageVo page) {
        stuPdfCourse.setCourseType(Constants.SOCIETYCOURSE + "");
        IPage<StuPdfCourseVO> list = courseService.stuPdfCourseList(stuPdfCourse, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }
}
