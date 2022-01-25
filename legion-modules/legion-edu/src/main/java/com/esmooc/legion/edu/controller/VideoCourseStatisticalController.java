package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.entity.vo.StuVideoCourseVO;
import com.esmooc.legion.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@DS("edu")
@Api(tags = "课程业务")
@RestController
@RequestMapping("/edu/videoCourseStatistical")
public class VideoCourseStatisticalController {
    @Autowired
    private CourseService courseService;

    /**
     * 查询学员课程业务列表
     */
    @ApiOperation(value = "查询学员课程业务列表")
    @GetMapping("/list")
    public Result<IPage<StuVideoCourseVO>> stuVideoCourseList(StuVideoCourseVO stuVideoCourse, PageVo page) {
        IPage<StuVideoCourseVO> list = courseService.stuVideoCourseList(stuVideoCourse, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }
}
