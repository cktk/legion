package com.esmooc.legion.your.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.your.common.utils.JsonUtil;
import com.esmooc.legion.your.entity.breastleftandrightpalpation.BreastLeftPalpation;
import com.esmooc.legion.your.entity.breastleftandrightpalpation.BreastRightPalpation;
import com.esmooc.legion.your.service.BreastLeftPalpationService;
import com.esmooc.legion.your.service.BreastRightPalpationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author: zqj
 * @version: 1.0
 * @description:
 */
@Controller
@RequestMapping("/v2/Screening")
@Api(value = "BreastLeftAndRightPalpationController", tags = "乳腺触诊检查")
public class BreastLeftAndRightPalpationController {
    @Autowired
    private BreastLeftPalpationService breastLeftPalpationService;
    @Autowired
    private BreastRightPalpationService breastRightPalpationService;

    // 左乳触诊
    @ResponseBody
    @PostMapping("/LeftPalpation_add")
    @ApiOperation(value = "左乳触诊增加")
    @SystemLog(description = "左乳触诊增加", type = LogType.SCREENING)
    public String leftPalpationAdd(@Validated BreastLeftPalpation breastLeftPalpation) {

        BreastLeftPalpation leftPalpation = breastLeftPalpationService.getOne(new QueryWrapper<BreastLeftPalpation>().lambda().eq(BreastLeftPalpation::getUserId, breastLeftPalpation.getUserId()));

        if (leftPalpation == null) {
            breastLeftPalpationService.save(breastLeftPalpation);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }

    }

    // 左乳触诊
    @ResponseBody
    @PostMapping("/LeftPalpation_ret")
    @ApiOperation(value = "左乳触诊返现")
    public String colposcopyRet(Integer id) {

        BreastLeftPalpation leftPalpation = breastLeftPalpationService.getOne(new QueryWrapper<BreastLeftPalpation>().lambda().eq(BreastLeftPalpation::getUserId, id));

        if (leftPalpation != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(leftPalpation));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

    // 右乳触诊
    @ResponseBody
    @PostMapping("/RightPalpation_add")
    @ApiOperation(value = "右乳触诊增加")
    public String rightPalpationAdd(@Validated BreastRightPalpation breastRightPalpation) {

        BreastRightPalpation rightPalpation = breastRightPalpationService.getOne(new QueryWrapper<BreastRightPalpation>().lambda().eq(BreastRightPalpation::getUserId, breastRightPalpation.getUserId()));

        if (rightPalpation == null) {
            breastRightPalpationService.save(breastRightPalpation);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }

    // 返现
    @ResponseBody
    @PostMapping("/RightPalpation_ret")
    @ApiOperation(value = "右乳触诊返现")
    public String colposcopy(Integer id) {

        BreastRightPalpation rightPalpation = breastRightPalpationService.getOne(new QueryWrapper<BreastRightPalpation>().lambda().eq(BreastRightPalpation::getUserId, id));

        if (rightPalpation != null) {

            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(rightPalpation));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }
}
