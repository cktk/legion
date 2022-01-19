package com.esmooc.legion.open.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.open.entity.Prompt;
import com.esmooc.legion.open.service.PromptService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ruoyi
 * @date 2021-02-22
 */
@DS("edu")
@Api(tags = "课程审核记录")
@RestController
@RequestMapping("/edu/prompt")
public class PromptController {
    @Autowired
    private PromptService bizPromptService;
    @Autowired
    private SecurityUtil securityUtil;


    @ApiOperation(value = "查询课程审核记录列表")
    @GetMapping("/list")
    public Result<IPage<Prompt>> list(Prompt prompt, PageVo page) {
        prompt.setUserId(securityUtil.getCurrUser().getId());
        return ResultUtil.data(bizPromptService.selectBizPromptList(prompt, PageUtil.initPage(page)));
    }


    @ApiOperation(value = "导出课程审核记录列表")
    @SystemLog(description = "导出课程审核记录列表", type = LogType.EDU)
    @GetMapping("/export")
    @ResponseExcel
    public List export(Prompt prompt, PageVo page) {
        IPage<Prompt> list = bizPromptService.selectBizPromptList(prompt, PageUtil.initPage(page));
        return list.getRecords();
    }


    @ApiOperation(value = "获取课程审核记录详细信息")
    @GetMapping(value = "/{id}")
    public Result<Prompt> getInfo(@PathVariable("id") Long id) {
        return ResultUtil.data(bizPromptService.selectBizPromptById(id));
    }

    /**
     * 新增课程审核记录
     */
    @ApiOperation(value = " ")
    @SystemLog(description = "课程审核记录", type = LogType.EDU)
    @PostMapping
    public Result add(@RequestBody Prompt prompt) {
        return ResultUtil.data(bizPromptService.insertBizPrompt(prompt));
    }

    /**
     * 修改课程审核记录
     */
    @ApiOperation(value = "修改课程审核记录")
    @SystemLog(description = "修改课程审核记录", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody Prompt prompt) {
        return ResultUtil.data(bizPromptService.updateBizPrompt(prompt));
    }

    /**
     * 删除课程审核记录
     */
    @ApiOperation(value = "删除课程审核记录")
    @SystemLog(description = "删除课程审核记录", type = LogType.EDU)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return ResultUtil.data(bizPromptService.deleteBizPromptByIds(ids));
    }
}
