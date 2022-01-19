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
import com.esmooc.legion.open.entity.LearningRecordFile;
import com.esmooc.legion.open.entity.vo.LearningRecordVO;
import com.esmooc.legion.open.service.LearningRecordFileService;
import com.esmooc.legion.open.service.LearningRecordService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程业务Controller
 *
 * @author sun
 * @date 2020-12-28
 */
@DS("edu")
@Api(tags = "课程业务")
@RestController
@RequestMapping("/edu/stuVideoCourse")
public class StuVideoCourseController {
    @Autowired
    private LearningRecordService learningRecordService;

    @Autowired
    private LearningRecordFileService learningRecordFileService;

    @Autowired
    private SecurityUtil securityUtil;

    @ApiOperation(value = "查询课程业务列表")
    @GetMapping("/list")
    public Result<IPage<LearningRecordVO>> list(LearningRecordVO bizLearningRecord, PageVo page) {
        bizLearningRecord.setUserId(securityUtil.getCurrUser().getId());
        bizLearningRecord.setCourseType(Constants.VIDEOCOURSE.toString());
        bizLearningRecord.setDelFlag(Constants.ISNOTDELETE);
        IPage<LearningRecordVO> list = learningRecordService.selectBizLearningRecordList(bizLearningRecord, securityUtil.getCurrUser(), PageUtil.initPage(page));
        return ResultUtil.data(list);
    }

    @ApiOperation(value = "导出课程业务列表")
    @SystemLog(description = "导出课程业务列表", type = LogType.EDU)
    @GetMapping("/export")
    @ResponseExcel
    public List<LearningRecordVO> export(LearningRecordVO bizLearningRecord, PageVo page) {
        return this.list(bizLearningRecord, page).getResult().getRecords();
    }

    /**
     * 获取课程业务详细信息
     */
    @ApiOperation(value = " ")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("courseId") String courseId) {
        return ResultUtil.data(learningRecordService.selectBizLearningRecordById(courseId, securityUtil.getCurrUser().getId()));
    }

    /**
     * 获取播放列表
     *
     * @param courseId
     * @return
     */
    @ApiOperation(value = " ")
    @GetMapping(value = "/playbackFileList/{courseId}")
    public Result selectPlaybackFileVOList(@PathVariable("courseId") String courseId) {
        return ResultUtil.data(learningRecordService.selectPlaybackFileVOList(courseId, securityUtil.getCurrUser().getId(), Constants.VIDEO));
    }

    /**
     *
     */
    @ApiOperation(value = "新增课程业务")
    @SystemLog(description = "新增课程业务", type = LogType.EDU)
    @PostMapping
    public Result add(@RequestBody LearningRecord learningRecord) {
        learningRecord.setUserId(securityUtil.getCurrUser().getId() + "");
        return ResultUtil.data(learningRecordService.insertBizLearningRecord(learningRecord));
    }

    /**
     *
     */
    @ApiOperation(value = "修改课程业务")
    @SystemLog(description = "修改课程业务", type = LogType.EDU)
    @PutMapping
    public Result edit(@RequestBody LearningRecord learningRecord) {
        learningRecord.setUserId(securityUtil.getCurrUser().getId() + "");
        return ResultUtil.data(learningRecordService.updateBizLearningRecord(learningRecord));
    }

    @ApiOperation(value = "新增课程业务")
    @SystemLog(description = "新增课程业务", type = LogType.EDU)
    @PostMapping("/videoLearningRecordFile")
    public Result addVideoLearningRecordFile(@RequestBody LearningRecordFile learningRecordFile) {
        learningRecordFile.setUserId(securityUtil.getCurrUser().getId() + "");
        try {
            return ResultUtil.data(learningRecordFileService.insertVideoLearningRecord(learningRecordFile));
        } catch (Exception e) {
            return ResultUtil.data("操作成功");
        }
    }

    /**
     *
     */
    @ApiOperation(value = "删除课程业务")
    @SystemLog(description = "删除课程业务", type = LogType.EDU)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] courseIds) {
        return ResultUtil.data(learningRecordService.deleteBizLearningRecordByIds(courseIds, securityUtil.getCurrUser().getId()));
    }

}
