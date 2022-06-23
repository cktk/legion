package com.esmooc.legion.autochat.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.autochat.mapper.AutoChatMapper;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.autochat.service.AutoChatService;
import com.esmooc.legion.autochat.vo.AssociateVo;
import com.esmooc.legion.autochat.vo.GuessVo;
import com.esmooc.legion.autochat.vo.MessageVo;

import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.AutoChatSetting;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "问答助手客服管理接口")
@RequestMapping("/legion/autoChat")
@Transactional
public class AutoChatController {

    @Autowired
    private AutoChatService autoChatService;

    @Autowired
    private AutoChatMapper autoChatMapper;

    @Autowired
    private SettingService settingService;


    public AutoChatSetting getChatSetting() {

        Setting setting = settingService.get(SettingConstant.CHAT_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return null;
        }
        return new Gson().fromJson(setting.getValue(), AutoChatSetting.class);
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<AutoChat>> getByCondition(AutoChat autoChat, SearchVo searchVo, PageVo pageVo) {

        IPage<AutoChat> page = autoChatService.findByCondition(autoChat, searchVo, pageVo);
        page.getRecords().forEach(e -> {
            if (StrUtil.isNotBlank(e.getContent())) {
                e.setContentText(HtmlUtil.cleanHtmlTag(e.getContent()));
            }
        });
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/ask", method = RequestMethod.GET)
    @ApiOperation(value = "问答接口")
    public Object ask(@ApiParam("问题") @RequestParam String q,
                      @ApiParam("最大回答数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        // 先直接匹配
        AutoChat autoChat = autoChatService.findByTitle(q);
        if (autoChat != null) {
            return new MessageVo().card(autoChat.getId(), autoChat.getContent(), autoChat.getEvaluable());
        }
        // 全文索引
        List<AutoChat> list = autoChatMapper.find(q, pageSize);
        if (list == null || list.size() == 0) {
            AutoChatSetting chatSetting = getChatSetting();
            String content = "暂未没找到相匹配的回答";
            if (chatSetting != null && StrUtil.isNotBlank(chatSetting.getNoDataReply())) {
                content = chatSetting.getNoDataReply();
            }
            return new MessageVo().card(content, false);
        } else if (list.size() == 1) {
            autoChat = list.get(0);
            return new MessageVo().card(autoChat.getId(), autoChat.getContent(), autoChat.getEvaluable());
        } else {
            List<MessageVo> result = new ArrayList<>();
            // 第一条回答
            autoChat = list.get(0);
            MessageVo answer = new MessageVo().card(autoChat.getId(), autoChat.getContent(), autoChat.getEvaluable());
            result.add(answer);
            list.remove(0);
            // 其余放猜你想问列表 2-4条
            if (list.size() == 1) {
                list.add(autoChat);
            } else if (list.size() > 4) {
                list = list.subList(0, 4);
            }
            list.forEach(e -> {
                e.setContent(e.getTitle());
            });
            List<GuessVo> promotions = new ArrayList<>();
            promotions.add(new GuessVo().setList(list));
            MessageVo promotion = new MessageVo().promotion(promotions);
            result.add(promotion);

            return result;
        }
    }

    @RequestMapping(value = "/associate", method = RequestMethod.GET)
    @ApiOperation(value = "联想搜索")
    public AssociateVo associate(@ApiParam("问题") @RequestParam String q,
                                 @ApiParam("最大回答数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        List<AutoChat> list = autoChatMapper.find(q, pageSize);
        return new AssociateVo().setKeyword(q).setList(list);
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    @ApiOperation(value = "赞踩")
    public Result<Object> evaluate(String messageId, String evaluateType) {

        AutoChat autoChat = autoChatService.getById(messageId);
        if ("good".equals(evaluateType)) {
            autoChat.setGood(autoChat.getGood() + 1);
        } else {
            autoChat.setBad(autoChat.getBad() + 1);
        }
        autoChatService.updateById(autoChat);
        return ResultUtil.success("操作成功");
    }

    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<AutoChat> get(@PathVariable String id) {
        return ResultUtil.data(autoChatService.getById(id));
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<AutoChat>> getAll() {
        return ResultUtil.data(autoChatService.list());
    }

    @GetMapping(value = "/getByPage")
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<IPage<AutoChat>> getByPage(PageVo page) {
        return ResultUtil.data(autoChatService.page(PageUtil.initMpPage(page)));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存数据")
    public Result<AutoChat> save(AutoChat entity) {
        return ResultUtil.ok(autoChatService.save(entity));
    }

    @PutMapping(value = "/update")
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<AutoChat> update(AutoChat entity) {
        return ResultUtil.ok(autoChatService.updateById(entity));
    }

    @PostMapping(value = "/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<AutoChat> delByIds(Integer[] ids) {
        return ResultUtil.ok(autoChatService.removeByIds(Arrays.asList(ids)));
    }


}
