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
import com.esmooc.legion.open.entity.LearningRecordFile;
import com.esmooc.legion.open.service.LearningRecordFileService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程附件学习状态业务Controller
 *
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "课程附件学习状态业务")
@RestController
@RequestMapping("/edu/recordFile")
public class LearningRecordFileController {
    @Autowired
    private LearningRecordFileService learningRecordFileService;

    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程附件学习状态业务列表")
    @GetMapping("/list")
    public Result<IPage<LearningRecordFile>> list(LearningRecordFile learningRecordFile, PageVo pageVo) {
        IPage<LearningRecordFile> list = learningRecordFileService.selectBizLearningRecordFileList(learningRecordFile, PageUtil.initPage(pageVo));
        return ResultUtil.data(list);
    }

    @ApiOperation(value = "导出课程附件学习状态业务列表")
    @SystemLog(description = "导出课程附件学习状态业务列表", type = LogType.EDU)
    @GetMapping("/export")
    @ResponseExcel()
    public List<LearningRecordFile> xport(LearningRecordFile learningRecordFile, PageVo pageVo) {
        IPage<LearningRecordFile> list = learningRecordFileService.selectBizLearningRecordFileList(learningRecordFile, PageUtil.initPage(pageVo));
        return list.getRecords();
    }

    @ApiOperation(value = "获取课程附件学习状态业务详细信息")
    @GetMapping(value = "/{courseId}")
    public Result getInfo(@RequestBody LearningRecordFile learningRecordFile) {
        return ResultUtil.data(learningRecordFileService.selectBizLearningRecordFileById(learningRecordFile));
    }

    @ApiOperation(value = "课程附件学习状态业务")
    @SystemLog(description = "课程附件学习状态业务", type = LogType.EDU)
    @PostMapping
    public Result add(@RequestBody LearningRecordFile learningRecordFile) {
        return ResultUtil.data(learningRecordFileService.insertBizLearningRecordFile(learningRecordFile));
    }

    /**
     * 修改课程附件学习状态业务
     */
    @ApiOperation(value = "修改课程附件学习状态业务")
    @SystemLog(description = "课程附件学习状态业务", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody LearningRecordFile learningRecordFile) {
        return ResultUtil.data(learningRecordFileService.updateBizLearningRecordFile(learningRecordFile));
    }

    /**
     * 删除课程附件学习状态业务
     */
    @ApiOperation(value = "课程附件学习状态业务")
    @SystemLog(description = "课程附件学习状态业务", type = LogType.EDU)
    @DeleteMapping("/{courseIds}")
    public Result remove(@PathVariable String[] courseIds) {
        return ResultUtil.data(learningRecordFileService.deleteBizLearningRecordFileByIds(courseIds, securityUtil.getCurrUser().getId()));
    }
}
