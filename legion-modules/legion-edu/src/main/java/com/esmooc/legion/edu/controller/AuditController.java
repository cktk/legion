package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.Course;
import com.esmooc.legion.edu.entity.Prompt;
import com.esmooc.legion.edu.service.CourseService;
import com.esmooc.legion.edu.service.PromptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private PromptService promptService;
    @Autowired
    private SecurityUtil securityUtil;
    /**
     * 查询课程业务列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询课程业务列表")
    public Result<IPage<Course>> list(Course course, PageVo page) {
        course.setAudit(Constants.UNREVISED);
        return ResultUtil.data(courseService.page(PageUtil.initPage(page), Wrappers.query(course)));

    }

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "审核通过")
    @Transactional
    @GetMapping("/through/{id}/{courseType}")
    public Result<Boolean> through(@PathVariable("id") String id, @PathVariable("courseType") int courseType) {
        Course course = courseService.getById(id);
        Prompt prompt = new Prompt();
        prompt.setCourseId(id);
        prompt.setPrompt("通过");
        prompt.setUserId(securityUtil.getCurrUser().getId());
        prompt.setCourseTitle(course.getCourseTitle());
        course.setAudit(Constants.UNREVISED_OK);
        promptService.save(prompt);
        return ResultUtil.data(courseService.updateById(course));
    }

    /**
     * 审核不通过
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "审核不通过")
    @Transactional
    @GetMapping("/notThrough/{id}/{prompt}")
    public Result<Boolean> notThrough(@PathVariable("id") String id, @PathVariable("prompt") String prompt) {
        Course course = courseService.getById(id);
        Prompt promp = new Prompt();
        promp.setCourseId(id);
        promp.setPrompt(prompt);
        promp.setUserId(securityUtil.getCurrUser().getId());
        promp.setCourseTitle(course.getCourseTitle());
        course.setAudit(Constants.UNREVISED_NO);
        promptService.save(promp);
        return ResultUtil.data(courseService.updateById(course));


    }

}
