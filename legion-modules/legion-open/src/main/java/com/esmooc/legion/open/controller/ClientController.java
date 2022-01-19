package com.esmooc.legion.open.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daimao
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
    public Result<Page<Client>> getByCondition(Client client,
                                               SearchVo searchVo,
                                               PageVo pageVo) {
        return new ResultUtil<Page<Client>>().setData(clientService.page(PageUtil.initPage(pageVo), Wrappers.query(client)));
    }

    @RequestMapping(value = "/getSecretKey", method = RequestMethod.GET)
    @ApiOperation(value = "生成随机secretKey")
    public Result<String> getSecretKey() {

        String secretKey = IdUtil.simpleUUID();
        return new ResultUtil<String>().setData(secretKey);
    }
}
