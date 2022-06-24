package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.constant.SystemConstant;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.entity.Dict;
import com.esmooc.legion.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @author DaiMao
 */


@Slf4j
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/legion/dict")
@Api(value = "Dict", tags = "字典表业务")
public class DictController {
    private final DictService dictService;


    @ApiOperation(value = "业务用 通过TYPE查询所有字典子项", notes = "分页查询")
    @GetMapping("/{type}")
    public Result<List<Dict>> getByType(@PathVariable("type") String type) {
        return ResultUtil.data(dictService.findByType(type));
    }


    /**
     * 通过yope查询
     *
     * @return
     */
    @ApiOperation(value = "通过value获取字典详情 ")
    @GetMapping("/value/{value}")
    public Result<Dict> getBytypeCode(@PathVariable("value") String value) {
        return ResultUtil.data(dictService.findByValue(value));
    }


    @ApiOperation(value = "1字典模糊搜索 根据type 模糊查询下面的所有子类", notes = "分页查询")
    @GetMapping("/search/{type}/{key}")
    public Result<List<Dict>> like(@PathVariable("type")String type,@PathVariable("key")String key) {
        return ResultUtil.data(dictService.search(type,key));
    }






}
