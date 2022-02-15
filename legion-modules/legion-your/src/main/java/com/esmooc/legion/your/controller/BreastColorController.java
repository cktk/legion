package com.esmooc.legion.your.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.your.entity.breastcolor.BreastColor;
import com.esmooc.legion.your.service.BreastColorService;
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
@Api(value = "ColorContextController", tags = "彩色超声")
public class BreastColorController {

    @Autowired
    private BreastColorService breastColorService;

    /**
     * 彩色超声增加
     * @param breastColor
     * @return
     */
    // 彩色超声
    @ResponseBody
    @PostMapping("/Color_add")
    @ApiOperation(value = "彩色超声增加")
    public String colorAdd(@Validated BreastColor breastColor) {
        BreastColor color = breastColorService.getOne(new QueryWrapper<BreastColor>().lambda().eq(BreastColor::getUserId, breastColor.getUserId()));

        if (color == null) {
            breastColorService.save(breastColor);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }

    /**
     * 彩色超声返现
     * @param userId
     * @return
     */
    // 返现
    @ResponseBody
    @PostMapping("/Color_jianyi_ret")
    @ApiOperation(value = "彩色超声返现")
    public String colposcopyRet(Integer userId) {

        BreastColor color = breastColorService.getOne(new QueryWrapper<BreastColor>().lambda().eq(BreastColor::getUserId, userId));

        if (color != null) {
            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(color));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }
}
