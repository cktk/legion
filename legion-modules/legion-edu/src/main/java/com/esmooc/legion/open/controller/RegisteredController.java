package com.esmooc.legion.open.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.esmooc.legion.core.common.annotation.SystemLog;
import com.esmooc.legion.core.common.enums.LogType;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 外部学员
 *
 * @author sun
 */
@DS("edu")
@Api(tags = "外部学员信息管理")
@RestController
@RequestMapping("/edu/registered")
public class RegisteredController {



    /**
     * 获取用户列表
     */
    @ApiOperation("获取外部学员列表")
    @GetMapping("/list")
    public Result list() {
        return ResultUtil.success("一会改改这边得重新写");
    }

    /**
     * 用户注册
     *
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping
    public Result registered() {
        return ResultUtil.success("一会改改这边得重新写");
    }

    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "删除外部学员")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数错误");
        }
        //Boolean b = registeredService.deleteById(id);
        if (false) {
            return ResultUtil.success("删除成功！");
        } else {
            return ResultUtil.error("当前学员已有学习记录，不允许删除，可以进行禁用！");
        }
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改")
    @SystemLog(description = "用户管理-状态修改", type = LogType.EDU)
    @PutMapping("/changeStatus")
    public Result changeStatus() {
        return ResultUtil.success("一会改改这边得重新写");
    }


    @ApiOperation(value = "重置密码")
    @SystemLog(description = "用户管理-重置密码", type = LogType.EDU)
    @PutMapping("/resetPwd")
    public Result resetPwd() {
        return ResultUtil.success("一会改改这边得重新写");
    }

}
