package com.esmooc.legion.open.controller;

import cn.hutool.core.util.IdUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.open.entity.Client;
import com.esmooc.legion.open.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "客户端管理接口")
@RequestMapping("/legion/client")
@Transactional
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<Client>> getByCondition(Client client,
                                               SearchVo searchVo,
                                               PageVo pageVo) {

        IPage<Client> page = clientService.findByCondition(client, searchVo,pageVo);
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/getSecretKey", method = RequestMethod.GET)
    @ApiOperation(value = "生成随机secretKey")
    public Result<String> getSecretKey() {

        String secretKey = IdUtil.simpleUUID();
        return ResultUtil.data(secretKey);
    }



    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<Client> get(@PathVariable String id) {
        return ResultUtil.data(clientService.getById(id));
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<Client>> getAll() {
        return ResultUtil.data(clientService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public Result<IPage<Client>> getByPage(PageVo page) {
        return ResultUtil.data(clientService.page(PageUtil.initMpPage(page)));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "保存数据")
    public Result<Client> save(Client entity) {
        return ResultUtil.ok(clientService.save(entity));
    }

    @PutMapping(value = "/update")
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<Client> update(Client entity) {
        return ResultUtil.ok(clientService.updateById(entity));
    }

    @PostMapping(value = "/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Client> delByIds(Integer[] ids) {
        return ResultUtil.ok(clientService.removeByIds(Arrays.asList(ids)));
    }


}
