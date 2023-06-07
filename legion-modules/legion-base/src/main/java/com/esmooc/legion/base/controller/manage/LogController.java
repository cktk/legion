package com.esmooc.legion.base.controller.manage;

import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "日志管理接口")
@RequestMapping("/legion/log")
@Transactional
public class LogController {


    @Autowired
    private LogService logService;

    @ApiOperation(value = "分页获取全部")
    @GetMapping("/getAllByPage")
    public Result<Object> getAllByPage(@RequestParam(required = false) Integer type,
                                       @RequestParam String key,
                                       SearchVo searchVo,
                                      PageVo pageVo) {
        return ResultUtil.data(logService.findByConfition(type, key, searchVo, pageVo));
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {
        return ResultUtil.ok(logService.removeBatchByIds(Arrays.asList(ids)));
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.POST)
    @ApiOperation(value = "全部删除")
    public Result<Object> delAll() {


        logService.deleteAll();

        return ResultUtil.success("删除成功");
    }
}
