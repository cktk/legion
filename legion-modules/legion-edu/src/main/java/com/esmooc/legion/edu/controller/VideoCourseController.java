package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.vo.CourseVO;
import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;
import com.esmooc.legion.edu.service.CourseService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@DS("edu")
@Api(tags = "课程业务")
@RestController
@RequestMapping("/edu/videoCourse")
public class VideoCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程业务列表")
    @GetMapping("/list")
    public Result<IPage<CourseVO>> list(CourseVO bizCourse, PageVo page) {
        bizCourse.setCourseType(Constants.VIDEOCOURSE + "");
        return ResultUtil.data(courseService.selectBizCourseVOList(bizCourse, PageUtil.initPage(page)));
    }


    @ApiOperation(value = "导出课程业务列表")
    @SystemLog(description = "导出课程业务列表", type = LogType.EDU)
    @GetMapping("/export")
    @ResponseExcel
    public List<CourseVO> export(CourseVO bizCourse, PageVo page) {
        bizCourse.setCourseType(Constants.VIDEOCOURSE + "");
        return this.list(bizCourse, page).getResult().getRecords();
    }


    @ApiOperation(value = "获取课程业务详细信息")
    @SystemLog(description = "获取课程业务详细信息", type = LogType.EDU)
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") String id) {
        return ResultUtil.data(courseService.selectBizCourseById(id));
    }

    /**
     * 新增课程业务
     */
    @ApiOperation(value = "新增课程业务")
    @SystemLog(description = "新增课程业务", type = LogType.EDU)
    @PostMapping
    public Result add( Course course) {
        course.setCourseType(Constants.VIDEOCOURSE);
        course.setCreateBy(securityUtil.getCurrUser().getId() + "");
        if (Constants.CITY.equals(securityUtil.getCurrUser().getType())) {
            course.setDelFlag(Constants.UNREVISED);
        } else {
            course.setDelFlag(Constants.ISNOTDELETE);

        }
        return ResultUtil.data(courseService.insertBizCourse(course));
    }


    @ApiOperation(value = "修改课程业务")
    @SystemLog(description = "修改课程业务", type = LogType.EDU)
    @PutMapping
    public Result edit( Course course) {
        course.setCourseType(Constants.VIDEOCOURSE);
        course.setUpdateBy(securityUtil.getCurrUser().getId() + "");
        return ResultUtil.data(courseService.updateBizCourse(course));
    }


    @ApiOperation(value = "删除课程业务")
    @SystemLog(description = "删除课程业务", type = LogType.EDU)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids) {
        return ResultUtil.data(courseService.deleteBizCourseByIds(ids));
    }


    @ApiOperation(value = "查询学员课程业务列表")
    @GetMapping("/stuVideoCourseList")
    public Result<IPage<StuVideoCourseVO>> stuVideoCourseList(StuVideoCourseVO stuVideoCourse, PageVo page) {
        stuVideoCourse.setCourseType(Constants.VIDEOCOURSE + "");
        IPage<StuVideoCourseVO> list = courseService.stuVideoCourseList(stuVideoCourse, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }
}
