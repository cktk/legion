package com.esmooc.legion.your.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esmooc.legion.your.common.utils.DateJson;
import com.esmooc.legion.your.common.utils.JsonUtil;
import com.esmooc.legion.your.entity.cervicalcolposcopy.CervicalColposcopy;
import com.esmooc.legion.your.service.CervicalColposcopyService;
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
@Api(value = "ColposcopyController", tags = "阴道镜检查")
public class CervicalColposcopyController {

    @Autowired
    private CervicalColposcopyService cervicalColposcopyService;

    // 阴道镜检查
    @ResponseBody
    @PostMapping("/Colposcopy_add")
    @ApiOperation(value = "阴道镜检查增加")
    public String colposcopyAdd(@Validated CervicalColposcopy cervicalColposcopy) {
        CervicalColposcopy cervical = cervicalColposcopyService.getOne(new QueryWrapper<CervicalColposcopy>().lambda().eq(CervicalColposcopy::getUserId, cervicalColposcopy.getUserId()));

        if (cervical == null) {
            cervicalColposcopyService.save(cervicalColposcopy);
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }

    }

    // 阴道镜检查修改
    @ResponseBody
    @PostMapping("/Colposcopy_update")
    @ApiOperation(value = "阴道镜检查修改")
    public String colposcopyUpdate(@Validated CervicalColposcopy cervicalColposcopy) {

        return JsonUtil.returnJson("0", String.valueOf(cervicalColposcopyService.updateById(cervicalColposcopy)), null);


    }

    // 返现
    @ResponseBody
    @PostMapping("/Colposcopy_ret")
    @ApiOperation(value = "阴道镜检查返现")
    public String colposcopyRet(Integer userId) {


        CervicalColposcopy colposcopy = cervicalColposcopyService.getOne(new QueryWrapper<CervicalColposcopy>().lambda().eq(CervicalColposcopy::getUserId, userId));

        if (colposcopy != null) {

            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(colposcopy));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

}
