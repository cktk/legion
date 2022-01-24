package com.esmooc.legion.edu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.SubmitPaper;
import com.esmooc.legion.edu.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName
 * @Author Administrator
 * @Date 2020-12-30 9:22
 **/
@DS("edu")
@Api(tags = "PaperController")
@RestController
@RequestMapping("/edu/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 获取我的练习，我的考试最近一次成绩
     *
     * @param clazzId 课程id
     * @param type    类型
     * @return
     */
    @ApiOperation(value = "获取我的练习，我的考试最近一次成绩")
    @GetMapping("/getPaper")
    public Result getPaper(String clazzId, String type) {
        // 校验
        if (null == clazzId || "".equals(clazzId)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperService.getPaper(clazzId, type, securityUtil.getCurrUser());
        return ResultUtil.data(m);
    }

    /**
     * 开始练习/开始考试
     *
     * @param clazzId 课程id
     * @param type    类型
     * @return
     */
    @ApiOperation(value = "开始练习/开始考试")
    @PostMapping("/startPracticing")
    public Result startPracticing(String clazzId, String type) {
        // 校验
        if (null == clazzId || null == type || "".equals(clazzId) || "".equals(type)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperService.startPracticing(clazzId, type, securityUtil.getCurrUser().getId());
        if (!Boolean.valueOf(m.get("code").toString())) {
            m.put("success", false);
            return ResultUtil.data(m);
        }
        m.put("success", true);
        return ResultUtil.data(m);
    }

    /**
     * 开始考试/ 创建的考试
     *
     * @param id edu_exam 表的id
     * @return
     */
    @ApiOperation(value = "开始考试/ 创建的考试")
    @PostMapping("/satartExam")
    public Result satartExam(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        return ResultUtil.data(paperService.satartExam(id));
    }

    /**
     * 校验是否删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "校验是否删除")
    @GetMapping("/practicingIsDelete")
    public Result practicingIsDelete(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = new HashMap();
        m.put("success", true);
        // 校验是否删除
        Integer isDelete = paperService.getIsDeleteByClazzId(id);
        if (Constants.RADIO.equals(isDelete)) {
            m.put("success", false);
            m.put("msg", "当前视频已被删除！");
        }
        return ResultUtil.data(m);
    }

    /**
     * 校验是否删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "校验是否删除2")
    @GetMapping("/examIsDelete")
    public Result examIsDelete(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = new HashMap();

        // 校验是否删除
        Integer isDelete = paperService.getIsDeleteByExamId(id);
        if (Constants.CHECKBOX.equals(isDelete)) {
            m.put("success", false);
            m.put("msg", "当前试卷已被删除！");
        } else {
            m.put("success", true);
        }
        return ResultUtil.data(m);
    }

    /**
     * 提交试卷
     *
     * @return
     */
    @ApiOperation(value = "提交试卷")
    @PostMapping("/submitPaper")
    public Result submitPaper( SubmitPaper data) {
        // 校验 试卷id是否传
        if (null == data) {
            return ResultUtil.error("参数不能为空");
        }
        if (StrUtil.isNotEmpty(data.getPaperId())) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperService.submitPaper(data);
        return ResultUtil.data(m);
    }

    /**
     * 查看解析
     *
     * @param id 试卷id
     * @return
     */
    @ApiOperation(value = "查看解析")
    @GetMapping("/viewResolution")
    public Result viewResolution(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperService.viewResolution(id);
        return ResultUtil.data(m);
    }

    /**
     * 根据课程查看试卷  考试的试卷
     *
     * @param id 课程id
     * @return
     */
    @ApiOperation(value = "根据课程查看试卷  考试的试卷")
    @GetMapping("/viewResolutionByClazz")
    public Result viewResolutionByClazz(String id) {
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        String paperId = paperService.selectPaperIdByClazzId(id);
        Map m = paperService.viewResolution(paperId);
        return ResultUtil.data(m);
    }

    /**
     * 根据试卷id获取信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据试卷id获取信息")
    @GetMapping("/getPaperById")
    public Result getPaperById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperService.getPaperById(id);
        return ResultUtil.data(m);
    }

    /**
     * 根据id删除试卷
     *
     * @return
     */
    @ApiOperation(value = "根据id删除试卷")
    @DeleteMapping("/deletePaperById")
    public Result deletePaperById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        paperService.deletePaperById(id);
        return ResultUtil.success();
    }

}
