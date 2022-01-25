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
import com.esmooc.legion.edu.common.utils.FileUtils;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.service.CourseFileService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 课程文件附件业务Controller
 *
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "课程附件管理")
@RestController
@RequestMapping("/edu/file")
public class CourseFileController {
    @Autowired
    private CourseFileService bizCourseFileService;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查询课程文件附件业务列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询课程文件附件业务列表")
    public Result<IPage<CourseFile>> list(CourseFile courseFile, PageVo page) {
        IPage<CourseFile> list = bizCourseFileService.selectCourseFileList(courseFile, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }

    /**
     * 导出课程文件附件业务列表
     */
    @SystemLog(description = "导出课程文件附件业务列表", type = LogType.EDU)
    @GetMapping("/export")
    @ApiOperation(value = "导出课程文件附件业务列表")
    @ResponseExcel
    public List<CourseFile> export(CourseFile courseFile, PageVo page) {
        IPage<CourseFile> list = bizCourseFileService.selectCourseFileList(courseFile, PageUtil.initPage(page));
        return list.getRecords();
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取课程文件附件业务详细信息")
    public Result<CourseFile> getInfo(@PathVariable("id") String id) {
        return ResultUtil.data(bizCourseFileService.selectCourseFileById(id));
    }

    @PostMapping("/files")
    @ApiOperation(value = "新增课程文件附件业务")
    @SystemLog(description = "新增课程文件附件业务", type = LogType.EDU)
    public Result<Integer> addFiles( String courseId,
                                     long fileType,
                                     MultipartFile[] files) {
        return ResultUtil.data(bizCourseFileService.insertCourseFiles(courseId, fileType, files, securityUtil.getCurrUser()));
    }

    @ApiOperation(value = "上传文件")
    @PostMapping("/file")
    public Result addFile( String courseId,
                           long fileType,
                           MultipartFile file) {
        return ResultUtil.data(bizCourseFileService.insertCourseFile(courseId, fileType, file, securityUtil.getCurrUser()));
    }

    @ApiOperation(value = "获取文件")
    @PostMapping(value = "/getFile")
    public void getFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = Constants.FILE_PATH + "/" + fileName;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
        FileUtils.writeBytes(filePath, response.getOutputStream());
    }

    @PutMapping
    @ApiOperation(value = "修改课程文件附件业务")
    @SystemLog(description = "课程文件附件业务", type = LogType.EDU)
    public Result<Integer> edit( CourseFile courseFile) {
        return ResultUtil.data(bizCourseFileService.updateBizCourseFile(courseFile));
    }

    @ApiOperation(value = "number")
    @PutMapping("/number")
    public Result editNumber( List<CourseFile> courseFileList) {
        return ResultUtil.data(bizCourseFileService.updateBizCourseFiles(courseFileList));
    }

    @ApiOperation(value = "updateBizCourseFilesNumber")
    @PutMapping("/updateBizCourseFilesNumber")
    public Result editNumber1( List<CourseFile> courseFileList) {
        return ResultUtil.data(bizCourseFileService.updateBizCourseFilesNumber(courseFileList));
    }

    @SystemLog(description = "课程文件附件业务", type = LogType.EDU)
    @ApiOperation(value = "删除课程文件附件业务")
    @DeleteMapping("/{ids}")
    public Result remove( String[] ids) {
        return ResultUtil.data(bizCourseFileService.deleteBizCourseFileByIds(ids));
    }

    @SystemLog(description = "课程文件附件业务", type = LogType.EDU)
    @DeleteMapping
    public Result remove() {
        return ResultUtil.success("没写任何业务逻辑");
    }
}
