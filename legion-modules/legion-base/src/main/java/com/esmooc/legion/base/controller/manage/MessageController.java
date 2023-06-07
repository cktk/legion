package com.esmooc.legion.base.controller.manage;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Message;
import com.esmooc.legion.core.entity.MessageSend;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.core.service.MessageSendService;
import com.esmooc.legion.core.service.MessageService;
import com.esmooc.legion.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "消息内容管理接口")
@RequestMapping("/legion/message")
@Transactional
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSendService sendService;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping(value = "/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<Message>> getByCondition(Message message,
                                                 SearchVo searchVo,
                                              PageVo pageVo) {

        IPage<Message> page = messageService.findByCondition(message, searchVo,pageVo);
        page.getRecords().forEach(e -> {
            e.setContentText(HtmlUtil.cleanHtmlTag(e.getContent()));
        });
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<Message> get(@PathVariable String id) {

        Message message = messageService.getById(id);
        message.setContentText(HtmlUtil.filter(message.getContent()));
        return ResultUtil.data(message);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加消息")
    public Result<Object> addMessage(Message message) {

         messageService.save(message);
        Message m =message;
        // 保存消息发送表
        List<MessageSend> messageSends = new ArrayList<>();
        if (CommonConstant.MESSAGE_RANGE_ALL.equals(message.getRange())) {
            // 全体
            List<User> allUser = userService.list();
            allUser.forEach(u -> {
                MessageSend ms = new MessageSend().setMessageId(m.getId()).setUserId(u.getId());
                messageSends.add(ms);
            });
            sendService.saveOrUpdateBatch(messageSends);
            // 推送
            messagingTemplate.convertAndSend("/topic/subscribe", "您收到了新的系统消息");
        } else if (CommonConstant.MESSAGE_RANGE_USER.equals(message.getRange())) {
            // 指定用户
            for (String id : message.getUserIds()) {
                MessageSend ms = new MessageSend().setMessageId(m.getId()).setUserId(id);
                messageSends.add(ms);
            }
            sendService.saveOrUpdateBatch(messageSends);
            // 推送
            for (String id : message.getUserIds()) {
                messagingTemplate.convertAndSendToUser(id, "/queue/subscribe", "您收到了新的消息");
            }
        }
        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑消息")
    public Result<Object> editMessage(Message message) {
        return ResultUtil.ok( messageService.updateById(message));
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除消息")
    public Result<Object> delMessage(@RequestParam String[] ids) {

        for (String id : ids) {
            messageService.removeById(id);
            // 删除发送表
            sendService.deleteByMessageId(id);
        }
        return ResultUtil.success("编辑成功");
    }
}
