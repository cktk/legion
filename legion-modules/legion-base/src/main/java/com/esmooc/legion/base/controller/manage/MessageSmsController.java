package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SearchUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.MessageSmsSend;
import com.esmooc.legion.core.service.mybatis.MessageSmsSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author 呆猫
 * @Date: 2022/02/18/ 14:44
 * @Description:
 */
@Slf4j
@RestController
@Api(tags = "短信管理")
@RequestMapping("/legion/sms")
@Transactional
public class MessageSmsController {

    @Autowired
    private MessageSmsSendService messageSmsSendService;

    @PostMapping(value = "/list")
    @ApiOperation(value = "短信发送记录查询")
    public Result<IPage<MessageSmsSend>> add(Map<String, Object> condition ) {
        return ResultUtil.data( messageSmsSendService.page(PageUtil.initMpPage(condition), SearchUtil.parseWhereSql(condition)));
    }

}
