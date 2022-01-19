package com.esmooc.legion.open.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.open.common.utils.BaseUtils;
import com.esmooc.legion.open.entity.ExamPaperRules;
import com.esmooc.legion.open.entity.vo.ExamPaperRulesVo;
import com.esmooc.legion.open.service.PaperRulesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PaperRulesController
 * @Author Administrator
 * @Date 2020-12-29 14:19
 **/
@DS("edu")
@Api(tags = "考试规则")
@RestController
@RequestMapping("/edu/paperrules")
public class PaperRulesController {

    @Autowired
    private PaperRulesService paperRulesService;
    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 考试规则列表
     *
     * @return
     */
    @ApiOperation(value = "考试规则列表")
    @GetMapping("/getPaperRulesList")
    public Result<List<ExamPaperRulesVo>> paperRulesList(ExamPaperRulesVo examPaperRulesVo, PageVo pageVo) {
        return ResultUtil.data(paperRulesService.paperRulesList(examPaperRulesVo, pageVo));
    }

    /**
     * 根据课程id，查询课程关联的单选、多选题目数量
     *
     * @param id 课程id
     * @return
     */
    @ApiOperation(value = "根据课程id，查询课程关联的单选、多选题目数量")
    @GetMapping("/getClazzQuestion")
    public Result getClazzQuestion(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperRulesService.getClazzQuestion(id);
        return ResultUtil.data(m);

    }

    /**
     * 新增规则
     *
     * @param examPaperRules
     * @return
     */
    @ApiOperation(value = "新增规则")
    @PostMapping("/saveRules")
    public Result saveRules(ExamPaperRules examPaperRules) {
        // 校验规则
        if (null == examPaperRules) {
            return ResultUtil.error("参数不能为空");
        }
        if (null == examPaperRules.getClazzId() || "".equals(examPaperRules.getClazzId())) {
            return ResultUtil.error("参数不能为空");
        }
        if (null == examPaperRules.getRules() || "".equals(examPaperRules.getRules())) {
            return ResultUtil.error("参数不能为空");
        }
        // 构建参数
        examPaperRules.setId(BaseUtils.getUUID());
        examPaperRules.setCreateTime(String.valueOf(BaseUtils.getDateNowSecond()));
        examPaperRules.setCreateBy(securityUtil.getCurrUser().getId());
        paperRulesService.saveRules(examPaperRules);
        return ResultUtil.success();
    }

    /**
     * 删除规则, 批量删除接口
     *
     * @param id rules主键id
     * @return
     */
    @ApiOperation(value = "删除规则, 批量删除接口")
    @DeleteMapping("/deleteRules")
    public Result deleteRules(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        paperRulesService.deleteRules(id);
        return ResultUtil.success();
    }

    /**
     * 获取规则
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取规则")
    @GetMapping("/getRulesById")
    public Result getRulesById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数不能为空");
        }
        Map m = paperRulesService.getRulesById(id);
        return ResultUtil.data(m);
    }

}
