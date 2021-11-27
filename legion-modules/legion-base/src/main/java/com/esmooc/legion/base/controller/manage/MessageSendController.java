package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.MessageSend;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.MessageSendService;
import com.esmooc.legion.core.service.MessageService;
import com.esmooc.legion.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "消息发送管理接口")
@RequestMapping("/legion/messageSend")
@Transactional
public class MessageSendController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private SecurityUtil securityUtil;


    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<MessageSend> get(@PathVariable String id) {
        return new ResultUtil<MessageSend>().setData(messageSendService.getById(id));
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<MessageSend>> getAll() {
        return new ResultUtil<List<MessageSend>>().setData(messageSendService.list());
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<Page<MessageSend>> getByPage(PageVo page) {
        return new ResultUtil<Page<MessageSend>>().setData(messageSendService.page(PageUtil.initPage(page)));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存数据")
    public Result<MessageSend> save(MessageSend autoChat) {


        if (messageSendService.save(autoChat)) {
            return new ResultUtil<MessageSend>().setData(autoChat);
        }
        return ResultUtil.error("更新失败");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新数据")
    public Result<MessageSend> update(MessageSend autoChat) {
        if (messageSendService.updateById(autoChat)) {
            return new ResultUtil<MessageSend>().setData(autoChat);
        }
        return ResultUtil.error("更新失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    @Transactional
    public Result<MessageSend> delByIds(List<String> ids) {

        if (messageSendService.removeByIds(ids)) {
            return ResultUtil.success("批量通过id删除数据成功");
        }

        return ResultUtil.error("删除失败已回滚");


    }


    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<MessageSend>> getByCondition(MessageSend msg,PageVo pv) {


       var queryWrapper = new QueryWrapper<MessageSend>();
        queryWrapper.eq( StrUtil.isNotEmpty(msg.getUserId()),"user_id",msg.getUserId());
        queryWrapper.eq(msg.getStatus()!=null , "status",msg.getStatus());
        Page<MessageSend> page = messageSendService.page(PageUtil.initPage(pv),queryWrapper);

        page.getRecords().forEach(item -> {
            User u = userService.getById(item.getUserId());
            if (u != null) {
                item.setUsername(u.getUsername()).setNickname(u.getNickname());
            }
            Message m = messageService.getById(item.getMessageId());
            if (m != null) {
                if (m.getIsTemplate()) {
                    Message message = messageSendService.getTemplateMessage(item.getMessageId(),
                            JSONUtil.toBean(item.getParams(), HashMap.class));
                    item.setTitle(message.getTitle()).setContent(message.getContent()).setType(m.getType());
                } else {
                    item.setTitle(m.getTitle()).setContent(m.getContent()).setType(m.getType());
                }
            }
        });
        return new ResultUtil<Page<MessageSend>>().setData(page);
    }

    @RequestMapping(value = "/all/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Object> batchOperation(@Param("0全部已读 1全部删除已读") @PathVariable Integer type) {

        User u = securityUtil.getCurrUser();
        if (type == 0) {
            messageSendService.updateStatusByUserId(u.getId(), CommonConstant.MESSAGE_STATUS_READ);
        } else if (type == 1) {
            messageSendService.deleteByUserId(u.getId());
        }
        return ResultUtil.success("操作成功");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑")
    public Result<Object> edit(MessageSend messageSend) {

        if (messageService.getById(messageSend.getMessageId()) != null) {
            messageSendService.updateById(messageSend);
        }
        return ResultUtil.success("操作成功");
    }
}
