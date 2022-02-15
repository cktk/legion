package com.esmooc.legion.your.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.your.entity.breastleftandrightcolor.BreastLeftColor;
import com.esmooc.legion.your.entity.breastleftandrightcolor.BreastRightColor;
import com.esmooc.legion.your.service.BreastLeftColorService;
import com.esmooc.legion.your.service.BreastRightColorService;
import com.esmooc.legion.your.common.utils.DateJson;
import com.esmooc.legion.your.common.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JsonConfig;
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
@Api(value = "BreastColorContextController", tags = "彩色超声检查")
public class BreastLeftAndRightColorController {

    @Autowired
    private BreastLeftColorService breastLeftColorService;
    @Autowired
    private BreastRightColorService breastRightColorService;

    /**
     * 彩超检查左乳信息增加
     * @param breastLeftColor
     * @return
     */
    // 彩色超声 --- 左乳
    @ResponseBody
    @PostMapping("/LeftColor_add")
    @ApiOperation(value = "彩超检查左乳信息增加")
    public String leftColorAdd(@Validated BreastLeftColor breastLeftColor) {
        BreastLeftColor breastLeft = breastLeftColorService.getOne(new QueryWrapper<BreastLeftColor>().lambda().eq(BreastLeftColor::getUserId, breastLeftColor.getUserId()));
        if (breastLeft == null) {
            breastLeftColorService.save(breastLeftColor);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }

    /**
     * 修改左乳彩超信息
     *
     * @param breastLeftColor
     * @return
     */
    @ResponseBody
    @PostMapping("/LeftColor_edit")
    @ApiOperation(value = "修改左乳彩超信息")
    public String leftColorEdit(@Validated BreastLeftColor breastLeftColor) {
        boolean b = breastLeftColorService.updateById(breastLeftColor);
        return JsonUtil.returnJson("1", String.valueOf(b), null);
    }


    /**
     * 彩超检查左乳返现
     *
     * @param userId
     * @return
     */
    // 返现
    @ResponseBody
    @PostMapping("/LeftColor_ret")
    @ApiOperation(value = "彩超检查左乳返现")
    public String leftColorRet(Integer userId) {


        BreastLeftColor breastLeft = breastLeftColorService.getOne(new QueryWrapper<BreastLeftColor>().lambda().eq(BreastLeftColor::getUserId, userId));

        if (breastLeft != null) {

            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(breastLeft));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }
    /**
     * 彩超检查右乳信息增加
     *
     * @param breastRightColor
     * @return
     */
    @ResponseBody
    @PostMapping("/RightColor_add")
    @ApiOperation(value = "彩超检查右乳信息增加")
    public String rightColorAdd(@Validated BreastRightColor breastRightColor) {
        BreastRightColor breastRight = breastRightColorService.getOne(new QueryWrapper<BreastRightColor>().lambda().eq(BreastRightColor::getUserId, breastRightColor.getUserId()));
        if (breastRight == null) {
            breastRightColorService.save(breastRightColor);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }


    /**
     * 修改右乳彩超信息
     *
     * @param breastRightColor
     * @return
     */
    @ResponseBody
    @PostMapping("/RightColor_edit")
    @ApiOperation(value = "修改右乳彩超信息")
    public String rightColorEdit(@Validated BreastRightColor breastRightColor) {
        boolean b = breastRightColorService.updateById(breastRightColor);
        return JsonUtil.returnJson("0", String.valueOf(b), null);
    }


    /**
     * 彩超检查右乳返现
     *
     * @param userId
     * @return
     */
    // 返现
    @ResponseBody
    @PostMapping("/RightColor_ret")
    @ApiOperation(value = "彩超检查右乳返现")
    public String rightColorRet(Integer userId) {


        BreastRightColor breastRight = breastRightColorService.getOne(new QueryWrapper<BreastRightColor>().lambda().eq(BreastRightColor::getUserId, userId));


        if (breastRight != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(breastRight));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }



}
