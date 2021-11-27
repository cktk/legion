package com.esmooc.legion.autochat.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.autochat.entity.AutoChat;
import com.esmooc.legion.autochat.entity.vo.AssociateVo;
import com.esmooc.legion.autochat.entity.vo.GuessVo;
import com.esmooc.legion.autochat.entity.vo.MessageVo;
import com.esmooc.legion.autochat.mapper.AutoChatMapper;
import com.esmooc.legion.autochat.service.AutoChatService;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.entity.vo.AutoChatSetting;
import com.esmooc.legion.core.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "问答助手客服管理接口")
@RequestMapping("/legion/autoChat")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AutoChatController {

     AutoChatService autoChatService;
     AutoChatMapper autoChatMapper;
     SettingService settingService;


    @GetMapping( "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<AutoChat> get(@PathVariable String id) {
        return new ResultUtil<AutoChat>().setData(autoChatService.getById(id));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<AutoChat>> getAll() {
        return new ResultUtil<List<AutoChat>>().setData(autoChatService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<Page<AutoChat>> getByPage(PageVo page) {
        return new ResultUtil<Page<AutoChat>>().setData(autoChatService.page(PageUtil.initPage(page)));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存数据")
    public Result<AutoChat> save(AutoChat autoChat) {



        if ( autoChatService.save(autoChat)){
            return new ResultUtil<AutoChat>().setData(autoChat);
        }
        return ResultUtil.error("更新失败");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新数据")
    public Result<AutoChat> update(AutoChat autoChat) {
        if ( autoChatService.updateById(autoChat)){
            return new ResultUtil<AutoChat>().setData(autoChat);
        }
        return ResultUtil.error("更新失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    @Transactional
    public Result<Object> delByIds(List<String> ids) {

      if (autoChatService.removeByIds(ids)){
          return ResultUtil.success("批量通过id删除数据成功");
      }

        return ResultUtil.error("删除失败已回滚");


    }


    public AutoChatSetting getChatSetting() {

        Setting setting = settingService.getById(SettingConstant.CHAT_SETTING);
        if (setting == null || StrUtil.isBlank(setting.getValue())) {
            return null;
        }
        return JSONUtil.toBean(setting.getValue(),AutoChatSetting.class);
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<AutoChat>> getByCondition(AutoChat autoChat, SearchVo searchVo, PageVo pageVo) {
        return new ResultUtil<Page<AutoChat>>().setData(autoChatService.page(PageUtil.initPage(pageVo),Wrappers.query(autoChat)));
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
            list.forEach(e->{
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
        if (autoChat != null) {
            if ("good".equals(evaluateType)) {
                autoChat.setGood(autoChat.getGood() + 1);
            } else {
                autoChat.setBad(autoChat.getBad() + 1);
            }
            autoChatService.updateById(autoChat);
        }
        return ResultUtil.success("操作成功");
    }
}
