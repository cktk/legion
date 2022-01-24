package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.entity.MyExam;
import com.esmooc.legion.edu.service.MyExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyExamController
 * @Author Administrator
 * @Date 2021-1-12 13:45
 **/
@DS("edu")
@Api(tags = "我的考试")
@RestController
@RequestMapping("/edu/myexam")
public class MyExamController {

    @Autowired
    private MyExamService myExamService;

    @ApiOperation(value = "我的考试列表")
    @GetMapping("/list")
    public Result<IPage<MyExam>> list(MyExam data, PageVo pageVo) {
        IPage<MyExam> list = myExamService.list(data, PageUtil.initPage(pageVo));
        return ResultUtil.data(list);
    }

    @ApiOperation(value = "首页我的考试")
    @GetMapping("/homeMyExam")
    public Result homeMyExam() {
        return ResultUtil.data(myExamService.homeMyExam());
    }

}
