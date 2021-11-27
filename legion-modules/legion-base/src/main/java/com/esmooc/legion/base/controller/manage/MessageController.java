package com.esmooc.legion.base.controller.manage;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SearchUtil;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "消息内容管理接口")
@RequestMapping("/legion/message")
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MessageController {

     MessageService messageService;
     MessageSendService sendService;
     UserService userService;
     SimpMessagingTemplate messagingTemplate;

    @PostMapping(value = "/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<Message>> getByCondition(@RequestBody Map<String, Object> search, PageVo pageVo) {
        IPage<Message> page = messageService.page(PageUtil.initPage(pageVo), SearchUtil.parseWhereSql(search));
        page.getRecords().forEach(e -> e.setContentText(HtmlUtil.cleanHtmlTag(e.getContent())));
        return ResultUtil.data(page);
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<Message> get(@PathVariable String id) {

        Message message = messageService.getById(id);
        message.setContentText(HtmlUtil.filter(message.getContent()));
        return new ResultUtil<Message>().setData(message);
    }

    @PostMapping( "/add")
    @ApiOperation(value = "添加消息")
    public Result<Object> addMessage(Message message) {

        messageService.save(message);
        // 保存消息发送表
        List<MessageSend> messageSends = new ArrayList<>();
        if (CommonConstant.MESSAGE_RANGE_ALL.equals(message.getRange())) {
            // 全体
            List<User> allUser = userService.list();
            allUser.forEach(u -> {
                MessageSend ms = new MessageSend().setMessageId(message.getId()).setUserId(u.getId());
                messageSends.add(ms);
            });
            sendService.saveOrUpdateBatch(messageSends);
            // 推送
            messagingTemplate.convertAndSend("/topic/subscribe", "您收到了新的系统消息");
        } else if (CommonConstant.MESSAGE_RANGE_USER.equals(message.getRange())) {
            // 指定用户
            for (String id : message.getUserIds()) {
                MessageSend ms = new MessageSend().setMessageId(message.getId()).setUserId(id);
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

    @PostMapping("/edit")
    @ApiOperation(value = "编辑消息")
    public Result<Object> editMessage(Message message) {

        messageService.updateById(message);
        return ResultUtil.success("编辑成功");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "删除消息")
    @Transactional
    public Result<Object> delMessage(@RequestParam String[] ids) {
        messageService.removeByIds(Arrays.asList(ids));
        sendService.removeByIds(Arrays.asList(ids));
        return ResultUtil.success("编辑成功");
    }
}
