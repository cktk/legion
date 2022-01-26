package com.esmooc.legion.file.controller;

import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/legion/upload")
@Transactional
public class UploadController {


    @Autowired
    private FileService fileService;

    @PostMapping(value = "/file")
    @ApiOperation(value = "文件上传")
    public Result<Object> upload(@RequestParam(required = false) MultipartFile file,
                                 @RequestParam(required = false) String base64,
                                 HttpServletRequest request) {
        return ResultUtil.data(fileService.upload(file, base64).getUrl());
    }

}
