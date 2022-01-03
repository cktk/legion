package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SearchUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Log;
import com.esmooc.legion.core.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "日志管理接口")
@RequestMapping("/legion/log")
@Transactional
public class LogController {


    @Autowired
    private LogService logService;

    @PostMapping("/getAllByPage")
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<Log>> getAllByPage(@RequestBody Map<String,Object> search) {
        log.info("数据啊{}", search);
        Page<Log> page = logService.page(PageUtil.initPage(search), SearchUtil.parseWhereSql(search));
        return new ResultUtil<Page<Log>>().setData(page);
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量删除")
    public Result<Log> delByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            logService.removeById(id);
        }
        return ResultUtil.success("删除成功");
    }

    @PostMapping("/delAll")
    @ApiOperation(value = "全部删除")
    public Result<Log> delAll() {

        List<Log> list = logService.list();
        List<String> collect = list.stream().map(Log::getId).collect(Collectors.toList());
        logService.removeByIds(collect);
        return ResultUtil.success("删除成功");
    }
}
