package com.esmooc.legion.your.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.esmooc.legion.your.common.utils.JsonUtil;
import com.esmooc.legion.your.entity.check.BreastColorCheck;
import com.esmooc.legion.your.entity.check.BreastPalpationCheck;
import com.esmooc.legion.your.entity.check.CervicalGynaecology;
import com.esmooc.legion.your.service.BreastColorCheckService;
import com.esmooc.legion.your.service.BreastPalpationCheckService;
import com.esmooc.legion.your.service.CervicalGynaecologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Api(value = "BreastCheckController", tags = "检查人员日期相关")
public class CheckController {


    @Autowired
    private BreastColorCheckService breastColorCheckService;
    @Autowired
    private BreastPalpationCheckService breastPalpationCheckService;
    @Autowired
    private CervicalGynaecologyService cervicalGynaecologyService;

    // 妇科检查

    /**
     * 新增妇科检查人员日期相关
     * @param cervicalGynaecology
     * @return
     */
    @ResponseBody
    @PostMapping("/Gynaecology_check")
    @ApiOperation(value = "新增妇科检查人员日期相关")
    public String gynaecologyCheck(CervicalGynaecology cervicalGynaecology) {
        CervicalGynaecology Gynaecology = cervicalGynaecologyService.getOne(new QueryWrapper<CervicalGynaecology>().lambda().eq(CervicalGynaecology::getUserId, cervicalGynaecology.getUserId()));
        if (Gynaecology == null) {
            cervicalGynaecologyService.save(cervicalGynaecology);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }

    }

    /**
     * 妇科检查人员日期相关返现
     * @param userId
     * @return
     */
    // 返现
    @ResponseBody
    @PostMapping("/Gynaecology_rets")
    @ApiOperation(value = "妇科检查人员日期相关返现")
    public String colposcopyRet(Integer userId) {

        CervicalGynaecology gynaecology = cervicalGynaecologyService.getOne(new QueryWrapper<CervicalGynaecology>().lambda().eq(CervicalGynaecology::getUserId, userId));
        if (gynaecology != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(gynaecology));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

    /**
     * 新增乳腺触诊人员日期相关
     * @param breastPalpationCheck
     * @return
     */
    // 乳腺触诊
    @ResponseBody
    @PostMapping("/Palpation_check")
    @ApiOperation(value = "新增乳腺触诊人员日期相关")
    public String palpationCheck(BreastPalpationCheck breastPalpationCheck) {
        BreastPalpationCheck breastPalpation = breastPalpationCheckService.getOne(new QueryWrapper<BreastPalpationCheck>().lambda().eq(BreastPalpationCheck::getUserId, breastPalpationCheck.getUserId()));
        if (breastPalpation == null) {
            breastPalpationCheckService.save(breastPalpationCheck);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }

    /**
     * 乳腺触诊人员日期相关返现
     * @param userId
     * @return
     */
    // 乳腺触诊返现
    @ResponseBody
    @PostMapping("/Palpation_rets")
    @ApiOperation(value = "乳腺触诊人员日期相关返现")
    public String palpationRet(Integer userId) {
        BreastPalpationCheck breastPalpation = breastPalpationCheckService.getOne(new QueryWrapper<BreastPalpationCheck>().lambda().eq(BreastPalpationCheck::getUserId, userId));
        if (breastPalpation != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(breastPalpation));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

    /**
     * 新增乳房彩超检查人员日期相关
     * @param breastColorCheck
     * @return
     */
    // 彩超检查
    @ResponseBody
    @PostMapping("/Color_check")
    @ApiOperation(value = "新增乳房彩超检查人员日期相关")
    public String colorCheck(BreastColorCheck breastColorCheck) {
        BreastColorCheck breastColor = breastColorCheckService.getOne(new QueryWrapper<BreastColorCheck>().lambda().eq(BreastColorCheck::getUserId, breastColorCheck.getUserId()));
        if (breastColor == null) {
            breastColorCheckService.save(breastColorCheck);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }

    /**
     * 乳房彩超检查人员日期相关返现
     * @param userId
     * @return
     */
    // 返现
    @ResponseBody
    @PostMapping("/Color_rets")
    @ApiOperation(value = "乳房彩超检查人员日期相关返现")
    public String colorRet(Integer userId) {
        BreastColorCheck breastColor = breastColorCheckService.getOne(new QueryWrapper<BreastColorCheck>().lambda().eq(BreastColorCheck::getUserId, userId));
        if (breastColor != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(breastColor));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

}
