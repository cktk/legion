package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.entity.vo.CourseVO;
import com.esmooc.legion.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 审计控制员
 *
 * @author Daimao
 * @date 2022年01月18日 18点01分46秒
 */
@DS("edu")
@Api(tags = "审计控制员")
@RestController
@RequestMapping("/edu/audit")
public class AuditController {

    /**
     * 商务课程服务
     */
    @Autowired
    private CourseService courseService;

    /**
     * 查询课程业务列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询课程业务列表")
    public Result<IPage<CourseVO>> list(CourseVO bizCourse, PageVo page) {
        IPage<CourseVO> bizCourseVOIPage = courseService.selectBizAuditCourseVOList(bizCourse, PageUtil.initPage(page));
        return ResultUtil.data(bizCourseVOIPage);
    }

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "审核通过")
    @GetMapping("/through/{id}/{courseType}")
    public Result<Integer> through(@PathVariable("id") String id, @PathVariable("courseType") int courseType) {
        return ResultUtil.data(courseService.updateBizAuditCourse(id, courseType));
    }

    /**
     * 审核不通过
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @GetMapping("/notThrough/{id}/{prompt}")
    public Result<Integer> notThrough(@PathVariable("id") String id, @PathVariable("prompt") String prompt) {
        return ResultUtil.data(courseService.deleteBizAuditCourseById(id, prompt));
    }

}
