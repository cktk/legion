package com.esmooc.legion.edu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.entity.vo.ExamVO;
import com.esmooc.legion.edu.entity.vo.Issue;
import com.esmooc.legion.edu.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * 管理端考试
 *
 * @ClassName ExamController
 * @Author Administrator
 * @Date 2021-1-7 9:21
 **/
@DS("edu")
@Api(tags = "管理端考试")
@RestController
@RequestMapping("/edu/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 查询管理端试卷主表
     *
     * @param examVO
     * @return
     */
    @ApiOperation(value = "查询管理端试卷主表")
    @GetMapping("/examList")
    public Result<IPage<ExamVO>> examList(ExamVO examVO, PageVo pageVo) {
        IPage<ExamVO> list = examService.examList(examVO, PageUtil.initPage(pageVo));
        return ResultUtil.data(list);
    }


    @ApiOperation(value = "管理端试卷保存/修改")
    @PostMapping("/saveExam")
    @Transactional
    public Result<Map> saveExam( ExamVO examVO) {
        /**
         *  校验: 规则、类别、名称
         */
        if (null == examVO) {
            return ResultUtil.error("参数为空");
        }
        if (StrUtil.isBlank(examVO.getClazzId())) {
            if (StrUtil.isBlank(examVO.getTitle())) {
                return ResultUtil.error("参数为空");
            }
            if (StrUtil.isBlank(examVO.getMajorId())) {
                return ResultUtil.error("参数错误");
            }
        }
        if (StrUtil.isBlank(examVO.getRules())) {
            return ResultUtil.error("错误");
        }
        Map m = examService.saveExam(examVO);
        return ResultUtil.data(m);
    }


    @ApiOperation(value = "根据id 查询edu_exam信息")
    @GetMapping("/getExamById")
    public Result<Map> getExamById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数为空");
        }
        return ResultUtil.data(examService.getExamById(id));
    }

    @ApiOperation(value = "isUpdateRules")
    @GetMapping("/isUpdateRules")
    public Result isUpdateRules(String id) {
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数为空");
        }
        int type = examService.isUpdateRules(id);
        if (type == 1) {
            return ResultUtil.success(200, "ok");
        } else {
            return ResultUtil.error(505, "错误");
        }
    }

    @ApiOperation(value = "删除试卷")
    @DeleteMapping("/deleteExam")
    @Transactional
    public Result deleteExam(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error(505, "参数错误");
        }
        examService.deleteExam(id);
        return ResultUtil.success("删除成功");
    }


    @ApiOperation(value = "发布考试")
    @PostMapping("/issueExam")
    public Result issueExam(Issue issue) {
        // 校验
        if (null == issue || "".equals(issue)) {
            return ResultUtil.error(505, "参数错误");
        }
        return ResultUtil.data(examService.issueExam(issue.getId(),issue.getUserIds(),issue.getDeptIds(),issue.getRoleIds()));
    }


    @ApiOperation(value = "发布考试之后撤回")
    @PostMapping("/withdrawExam")
    public Result withdrawExam(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error(505, "参数错误");
        }
        examService.withdrawExam(id);
        return ResultUtil.success(200, "撤回成功");
    }


    @ApiOperation(value = "根据题库创建试卷，校验是否可以创建试卷")
    @GetMapping("/checkBankCreate")
    public Result checkBankCreate(String id) {
        if (null == id || "".equals(id)) {
            return ResultUtil.error(505, "参数错误");
        }
        Map m = examService.checkBankCreate(id);
        return ResultUtil.success("ok", m);
    }

}
