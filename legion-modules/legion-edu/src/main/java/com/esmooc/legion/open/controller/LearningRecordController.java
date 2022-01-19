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
import com.esmooc.legion.open.common.constant.Constants;
import com.esmooc.legion.open.entity.LearningRecord;
import com.esmooc.legion.open.entity.vo.LearningRecordVO;
import com.esmooc.legion.open.service.LearningRecordService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "课程学习状态业务")
@RestController
@RequestMapping("/edu/record")
public class LearningRecordController {
    @Autowired
    private LearningRecordService learningRecordService;

    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程学习状态业务列表")
    @GetMapping("/list")
    public Result<IPage<LearningRecordVO>> list(LearningRecordVO bizLearningRecord, PageVo pageVo) {
        bizLearningRecord.setDelFlag(Constants.ISNOTDELETE);
        IPage<LearningRecordVO> list = learningRecordService.selectBizLearningRecordList(bizLearningRecord, securityUtil.getCurrUser(), PageUtil.initPage(pageVo));
        return ResultUtil.data(list);
    }


    @ResponseExcel()
    @ApiOperation(value = "导出课程学习状态业务列表")
    @SystemLog(description = "导出课程学习状态业务列表", type = LogType.EDU)
    @GetMapping("/export")
    public List<LearningRecordVO> export(LearningRecordVO bizLearningRecord, PageVo page) {
        bizLearningRecord.setDelFlag(Constants.ISNOTDELETE);
        IPage<LearningRecordVO> list = learningRecordService.selectBizLearningRecordList(bizLearningRecord, securityUtil.getCurrUser(), PageUtil.initPage(page));
        return list.getRecords();
    }


    @ApiOperation(value = "获取课程学习状态业务详细信息")
    @GetMapping(value = "/{courseId}")
    public Result getInfo(@PathVariable("courseId") String courseId) {
        return ResultUtil.data(learningRecordService.selectBizLearningRecordById(courseId, securityUtil.getCurrUser().getId()));
    }


    @ApiOperation(value = "新增课程学习状态业务")
    @SystemLog(description = "新增课程学习状态业务", type = LogType.EDU)
    @PostMapping
    public Result add(@RequestBody LearningRecord learningRecord) {
        learningRecord.setUserId(securityUtil.getCurrUser().getId());
        return ResultUtil.data(learningRecordService.insertBizLearningRecord(learningRecord));
    }


    @ApiOperation(value = "修改课程学习状态业务")
    @SystemLog(description = "修改课程学习状态业务", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody LearningRecord learningRecord) {
        return ResultUtil.data(learningRecordService.updateBizLearningRecord(learningRecord));
    }


    @ApiOperation(value = "删除课程学习状态业务")
    @SystemLog(description = "删除课程学习状态业务", type = LogType.EDU)
    @DeleteMapping("/{courseIds}")
    public Result remove(@PathVariable String[] courseIds) {
        return ResultUtil.data(learningRecordService.deleteBizLearningRecordByIds(courseIds, securityUtil.getCurrUser().getId()));
    }


    @ApiOperation(value = "清除学习记录")
    @GetMapping(value = "clearingLearningRecord/{courseId}")
    public Result ClearingLearningRecord(@PathVariable("courseId") String courseId) {
        LearningRecordVO vo = learningRecordService.selectBizLearningRecordById(courseId, securityUtil.getCurrUser().getId());
        if (Constants.TESTLIMIT == vo.getNoPassCount()) {
            return ResultUtil.data(learningRecordService.ClearingLearningRecord(courseId, securityUtil.getCurrUser().getId()));
        } else {
            return ResultUtil.data(1);
        }
    }

}
