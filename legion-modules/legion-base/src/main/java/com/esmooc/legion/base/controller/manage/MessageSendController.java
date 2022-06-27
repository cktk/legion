package com.esmooc.legion.base.controller.manage;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.config.security.service.PigxUser;
import com.esmooc.legion.core.config.security.util.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.MessageSend;
import com.esmooc.legion.core.entity.SysUser;
import com.esmooc.legion.core.service.MessageSendService;
import com.esmooc.legion.core.service.MessageService;
import com.esmooc.legion.core.service.SysUserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "消息发送管理接口")
@RequestMapping("/legion/messageSend")
@Transactional
public class MessageSendController  {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService messageSendService;


    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<MessageSend> get(@PathVariable String id) {
        return ResultUtil.data(messageSendService.getById(id));
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<MessageSend>> getAll() {
        return ResultUtil.data(messageSendService.list());
    }

    @GetMapping(value = "/getByPage")
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<IPage<MessageSend>> getByPage(PageVo page) {
        return ResultUtil.data(messageSendService.page(PageUtil.initMpPage(page)));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存数据")
    public Result<MessageSend> save(MessageSend entity) {
        return ResultUtil.ok(messageSendService.save(entity));
    }

    @PutMapping(value = "/update")
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<MessageSend> update(MessageSend entity) {
        return ResultUtil.ok(messageSendService.updateById(entity));
    }

    @PostMapping(value = "/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<MessageSend> delByIds(Integer[] ids) {
        return ResultUtil.ok(messageSendService.removeByIds(Arrays.asList(ids)));
    }


    @ApiOperation(value = "多条件分页获取")
    @GetMapping("/getByCondition")
    public Result<IPage<MessageSend>> getByCondition(MessageSend ms,
                                                     PageVo pv) {

        IPage<MessageSend> page = messageSendService.findByCondition(ms,pv);
        page.getRecords().forEach(item -> {
            SysUser u = sysUserService.getById(item.getUserId());
            if (u != null) {
                item.setUsername(u.getUsername()).setNickname(u.getNickname());
            }
            Message m = messageService.getById(item.getMessageId());
            if (m != null) {
                if (m.getIsTemplate()) {
                    Message message = messageSendService.getTemplateMessage(item.getMessageId(),
                            new Gson().fromJson(item.getParams(), HashMap.class));
                    item.setTitle(message.getTitle()).setContent(message.getContent()).setType(m.getType());
                } else {
                    item.setTitle(m.getTitle()).setContent(m.getContent()).setType(m.getType());
                }
            }
        });
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/all/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Object> batchOperation(@Param("0全部已读 1全部删除已读") @PathVariable Integer type) {
        PigxUser u = SecurityUtil.getUser();
        if (type == 0) {
            messageSendService.updateStatusByUserId(u.getId(), CommonConstant.MESSAGE_STATUS_READ);
        } else if (type == 1) {
            messageSendService.deleteByUserId(u.getId());
        }
        return ResultUtil.success("操作成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Object> edit(MessageSend messageSend) {

        if (messageService.getById(messageSend.getMessageId()) != null) {
            messageSendService.updateById(messageSend);
        }
        return ResultUtil.success("操作成功");
    }
}
