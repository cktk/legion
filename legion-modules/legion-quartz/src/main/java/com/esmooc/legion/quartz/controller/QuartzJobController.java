package com.esmooc.legion.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.quartz.entity.QuartzJob;
import com.esmooc.legion.quartz.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "定时任务管理接口")
@RequestMapping("/legion/quartzJob")
@Transactional
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;

    @Resource
    private Scheduler scheduler;

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }

    @RequestMapping(value = "/getAllByPage", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有定时任务")
    public Result<IPage<QuartzJob>> getAllByPage(String key, PageVo page) {

        IPage<QuartzJob> data = quartzJobService.findByCondition(key, page);
        return ResultUtil.data(data);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加定时任务")
    public Result<Object> addJob(QuartzJob job) {

        List<QuartzJob> list = quartzJobService.findByJobClassName(job.getJobClassName());
        if (list != null && list.size() > 0) {
            return ResultUtil.error("该定时任务类名已存在");
        }
        add(job.getJobClassName(), job.getCronExpression(), job.getParameter());
        quartzJobService.save(job);
        return ResultUtil.success("创建定时任务成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "更新定时任务")
    public Result<Object> editJob(QuartzJob job) {

        delete(job.getJobClassName());
        add(job.getJobClassName(), job.getCronExpression(), job.getParameter());
        job.setStatus(CommonConstant.STATUS_NORMAL);
        quartzJobService.updateById(job);
        return ResultUtil.success("更新定时任务成功");
    }

    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ApiOperation(value = "暂停定时任务")
    public Result<Object> pauseJob(QuartzJob job) {

        try {
            scheduler.pauseJob(JobKey.jobKey(job.getJobClassName()));
        } catch (SchedulerException e) {
            throw new LegionException("暂停定时任务失败");
        }
        job.setStatus(CommonConstant.STATUS_DISABLE);
        quartzJobService.updateById(job);
        return ResultUtil.success("暂停定时任务成功");
    }

    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    @ApiOperation(value = "恢复定时任务")
    public Result<Object> resumeJob(QuartzJob job) {

        try {
            scheduler.resumeJob(JobKey.jobKey(job.getJobClassName()));
        } catch (SchedulerException e) {
            throw new LegionException("恢复定时任务失败");
        }
        job.setStatus(CommonConstant.STATUS_NORMAL);
        quartzJobService.updateById(job);
        return ResultUtil.success("恢复定时任务成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除定时任务")
    public Result<Object> deleteJob(@RequestParam String[] ids) {

        for (String id : ids) {
            QuartzJob job = quartzJobService.getById(id);
            delete(job.getJobClassName());
            quartzJobService.removeById(job);
        }
        return ResultUtil.success("删除定时任务成功");
    }

    /**
     * 添加定时任务
     *
     * @param jobClassName
     * @param cronExpression
     * @param parameter
     */
    public void add(String jobClassName, String cronExpression, String parameter) {

        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobClassName)
                    .usingJobData("parameter", parameter)
                    .build();

            // 表达式调度构建器(即任务执行的时间) 使用withMisfireHandlingInstructionDoNothing() 忽略掉调度暂停过程中没有执行的调度
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.toString());
            throw new LegionException("创建定时任务失败");
        } catch (Exception e) {
            throw new LegionException("后台找不到该类名任务");
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName
     */
    public void delete(String jobClassName) {

        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            throw new LegionException("删除定时任务失败");
        }
    }

}
