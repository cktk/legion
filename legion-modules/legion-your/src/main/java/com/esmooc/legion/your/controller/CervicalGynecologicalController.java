package com.esmooc.legion.your.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.esmooc.legion.your.common.utils.JsonUtil;
import com.esmooc.legion.your.entity.UserUser;
import com.esmooc.legion.your.entity.cervicalGynecological.CervicalGynecological;


import com.esmooc.legion.your.entity.cervicalGynecological.GynecologicalDto;
import com.esmooc.legion.your.entity.check.CervicalGynaecology;
import com.esmooc.legion.your.service.CervicalGynaecologyService;
import com.esmooc.legion.your.service.CervicalGynecologicalService;
import com.esmooc.legion.your.service.UserUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zqj
 * @version: 1.0
 * @description:
 */

@RestController
@RequestMapping("/v2/Screening")
@Api(value = "CervicalGynecologicalController", tags = "妇科检查")
public class CervicalGynecologicalController {
    @Autowired
    private CervicalGynecologicalService cervicalGynecologicalService;
    @Autowired
    private CervicalGynaecologyService cervicalGynaecologyService;
    @Autowired
    private UserUserService userUserService;

    /**
     * 妇科检查增加
     * @param gynecologicalDto
     * @return
     */
    @ResponseBody
    @PostMapping("/Gynecological_add")
    @ApiOperation(value = "妇科检查增加")
    public String gynecologicalAdd(@Validated GynecologicalDto gynecologicalDto) {
        CervicalGynecological cervicalGynecological = BeanUtil.copyProperties(gynecologicalDto, CervicalGynecological.class);
        if (cervicalGynecological != null) {
            CervicalGynecological gynecological = cervicalGynecologicalService.getOne(new QueryWrapper<CervicalGynecological>().lambda().eq(CervicalGynecological::getUserId, cervicalGynecological.getUserId()));
            if (gynecological == null) {
                cervicalGynecologicalService.save(cervicalGynecological);
                CervicalGynaecology cervicalGynaecology = BeanUtil.copyProperties(gynecologicalDto, CervicalGynaecology.class);
                cervicalGynaecologyService.save(cervicalGynaecology);
                UserUser user = new UserUser();
                user.setId(gynecologicalDto.getUserId());
                user.setUpload(gynecologicalDto.getUpload());
                userUserService.updateById(user);
            }
            return JsonUtil.returnJson("0", "成功", null);
        } else {
            return JsonUtil.returnJson("1", "失败，重复提交", null);
        }
    }


    /**
     * 妇科检查修改
     * @param gynecologicalDto
     * @return
     */
    @ResponseBody
    @PostMapping("/Gynecological_update")
    @ApiOperation(value = "妇科检查修改")
    public String gynecologicalUpdate(@Validated GynecologicalDto gynecologicalDto) {
        CervicalGynecological cervicalGynecological = BeanUtil.copyProperties(gynecologicalDto, CervicalGynecological.class);
        cervicalGynecologicalService.update(cervicalGynecological, new UpdateWrapper<CervicalGynecological>().lambda().eq(CervicalGynecological::getUserId, cervicalGynecological.getUserId()));
        CervicalGynaecology cervicalGynaecology = BeanUtil.copyProperties(gynecologicalDto, CervicalGynaecology.class);
        cervicalGynaecologyService.update(cervicalGynaecology, new UpdateWrapper<CervicalGynaecology>().lambda().eq(CervicalGynaecology::getUserId, cervicalGynaecology.getUserId()));
        UserUser user = new UserUser();
        user.setId(gynecologicalDto.getUserId());
        user.setUpload(gynecologicalDto.getUpload());
        userUserService.updateById(user);
        return JsonUtil.returnJson("0", "成功", null);

    }

    // 返现
    @ResponseBody
    @RequestMapping(value = "Gynecological_ret", produces = "text/html; charset=utf-8")
    public String gynecologicalRet(Integer id) {

        GynecologicalDto gynecological = cervicalGynecologicalService.getAllByUserId(Integer.valueOf(id));

        if (gynecological != null) {

            return JsonUtil.returnJson("0", "成功", JSONUtil.toJsonStr(gynecological));
        } else {
            return JsonUtil.returnJson("1", "查无信息", null);
        }
    }

}