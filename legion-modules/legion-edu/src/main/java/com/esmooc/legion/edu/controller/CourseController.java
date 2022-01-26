package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.common.utils.DateUtils;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.service.CourseFileService;
import com.esmooc.legion.file.entity.File;
import com.esmooc.legion.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/26/ 15:01
 * @Description:
 */

@DS("edu")
@Api(tags = "课程附件管理")
@RestController
@RequestMapping("/edu/file")
public class CourseController {
    @Autowired
    private CourseFileService courseFileService;

    @Autowired
    private SecurityUtil securityUtil;


    @GetMapping("/list")
    @ApiOperation(value = "查询课程文件附件业务列表")
    public Result<IPage<CourseFile>> list(CourseFile courseFile, PageVo page) {
        return ResultUtil.data(courseFileService.page(PageUtil.initPage(page), Wrappers.query(courseFile)));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取课程文件附件业务详细信息")
    public Result<CourseFile> getInfo(@PathVariable("id") String id) {
        return ResultUtil.data(courseFileService.getById(id));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增课程文件附件业务")
    @SystemLog(description = "新增课程文件附件业务", type = LogType.EDU)
    public Result<Boolean> addFiles(CourseFile courseFile) {
        return ResultUtil.data(courseFileService.save(courseFile));
    }

    @PutMapping
    @ApiOperation(value = "修改课程文件附件业务")
    @SystemLog(description = "课程文件附件业务", type = LogType.EDU)
    public Result<Boolean> edit(CourseFile courseFile) {
        return ResultUtil.data(courseFileService.updateById(courseFile));
    }


    @ApiOperation(value = "number")
    @PutMapping("/number")
    public Result editNumber(List<CourseFile> courseFileList) {
        return ResultUtil.data(courseFileService.updateBatchById(courseFileList));
    }


    @ApiOperation(value = "updateCourseFilesNumber")
    @PutMapping("/updateCourseFilesNumber")
    public Result editNumber1(List<CourseFile> courseFileList) {
        return ResultUtil.data(courseFileService.updateBatchById(courseFileList));
    }


    @SystemLog(description = "删除课程文件附件业务", type = LogType.EDU)
    @ApiOperation(value = "删除课程文件附件业务")
    @DeleteMapping("/{ids}")
    public Result removeByIds(@PathVariable List<String> ids) {
        return ResultUtil.data(courseFileService.removeByIds(ids));
    }



}
