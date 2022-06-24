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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 12:44
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */

@Slf4j
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/legion/dict/admin")
@Api(value = "Dict", tags = "字典表管理")
public class DictAdminController {

    private final DictService dictService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param dict 字典表
     * @return
     */
    @ApiOperation(value = "1. 分页查询 只查询父类 所有的 条件 状态 是否是父类 是否系统内置 标签 值 type 描述 备注 拼音  ", notes = "分页查询 ")
    @GetMapping("/page")
    public Result<IPage<Dict>> getDictDataPage(PageVo page, Dict dict) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(dict.getStatus() != null, Dict::getStatus, dict.getStatus())
                .eq(Dict::isParent, true)
                .eq(dict.getSystemFlag() != null, Dict::getSystemFlag, dict.getSystemFlag())
                .like(StrUtil.isNotBlank(dict.getLabel()), Dict::getLabel, dict.getLabel()).or()
                .like(StrUtil.isNotBlank(dict.getCode()), Dict::getCode, dict.getCode()).or()
                .like(StrUtil.isNotBlank(dict.getType()), Dict::getType, dict.getType()).or()
                .like(StrUtil.isNotBlank(dict.getDescription()), Dict::getDescription, dict.getDescription()).or()
                .like(StrUtil.isNotBlank(dict.getRemarks()), Dict::getRemarks, dict.getRemarks()).or()
                .like(StrUtil.isNotBlank(dict.getValue()), Dict::getValue, dict.getValue()).or()
                .like(StrUtil.isNotBlank(dict.getPinyin()), Dict::getPinyin, dict.getPinyin()).or()
                .orderByAsc(Dict::getType, Dict::getSortOrder);
        return ResultUtil.data(dictService.page(PageUtil.initMpPage(page), queryWrapper));
    }

    @ApiOperation(value = "1.1 字典模糊搜索  一个key字段查所有 ", notes = "分页查询")
    @GetMapping("/search")
    public Result<IPage<Dict>> like(String key, PageVo page) {
        return ResultUtil.data(dictService.search(page, key));
    }


    @PostMapping("/add")
    @ApiOperation(value = "2   添加")
    public Result<Dict> add(@Validated Dict dict) {
        dict.setStatus(true);
        // 先判断保存父类还是子类
        if (dict.isParent()) {
            //保存父类
            Dict dictList = dictService.findParentTypeAll(dict.getType());
            if (dictList != null) {
                return ResultUtil.error("字典类型Type已存在");
            }
            dict.setCode("");
            dict.setLabel("");
            dict.setValue("");
            dict.setPinyin("");
        } else {
            //添加子类
            Dict pDict = dictService.findParentTypeAll(dict.getType());
            if (pDict == null) {
                return ResultUtil.error("字典类型Type不存在 请先添加父类");
            }

            if (StrUtil.isEmpty(dict.getLabel())) {
                throw new LegionException("字典名称不能为空 label");
            }

            if (StrUtil.isEmpty(dict.getCode())) {
                throw new LegionException("字典编码不能为空 code");
            }

            dict.setPinyin(PinyinUtil.getFirstLetter(dict.getLabel(), "").toUpperCase());

            if (StrUtil.isEmpty(dict.getValue())) {
                dict.setValue(dict.getType() + "_" + dict.getCode());
            }

            List<Dict> valueAll = dictService.findByValueAll(dict.getValue());

            if (valueAll != null && valueAll.size() > 0) {
                throw new LegionException("字典项重复 请检查  " + dict.getValue() + "  是否有已禁用项");
            }
            dict.setStatus(true);
        }


        if (dictService.save(dict)) {
            return ResultUtil.data(dict);
        }

        throw new LegionException("保存字典失败");
    }


    @PutMapping("/edit")
    @ApiOperation(value = "3   编辑")
    public Result<Dict> edit(Dict dict) {
        Dict old = dictService.getById(dict.getId());

        if (old == null) throw new LegionException("字典不存在");

        if (old.isParent()) {
            dict.setType(old.getType());
            dict.setCode("");
            dict.setLabel("");
            dict.setValue("");
        } else {
            //子类
            if ((dictService.findByValueAll(dict.getValue()) != null)) {
                return ResultUtil.error("字典类型TypeCode已存在");
            }

        }
        dict.setType(old.getType());


        return ResultUtil.ok(dictService.updateById(dict));
    }


    /**
     * 通过id删除字典表
     *
     * @param ids id
     * @return R
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "4   通过id删除字典表 系统内置不允许删除 ", notes = "通过id删除字典表")
    @DeleteMapping()
    public Result<Dict> removeById(@RequestParam String[] ids) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Dict::getId, ids);
        List<Dict> datas = dictService.list(queryWrapper);
        for (Dict data : datas) {
            if (SystemConstant.FLAG_Y.equals(data.getSystemFlag())) {
                throw new LegionException("系统内置字典不允许删除");
            }
            if (data.isParent()) {
                List<Dict> dictData = dictService.findByTypeAll(data.getType());
                if (dictData != null && dictData.size() > 0) {
                    throw new LegionException("请先删除字典子项");
                }
            }
        }
        return ResultUtil.ok(dictService.removeByIds(Arrays.asList(ids)));
    }


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "5  启用和禁用", notes = "启用禁用")
    @PutMapping("/switch/{id}/{status}")
    public Result<Dict> switchs(@PathVariable("id") Long id,
                                @PathVariable("status") boolean status) {
        return ResultUtil.data(dictService.switchs(id, status));
    }


    /**
     * 通过yope查询
     *
     * @return 已禁用启用都返回
     */
    @ApiOperation(value = "6 查询所有-通过TYPE查询所有子类 ", notes = "分页查询")
    @GetMapping("/{type}")
    public Result<IPage<Dict>> findByTypeAll(@PathVariable("type") String type,PageVo pageVo) {
        return ResultUtil.data(dictService.findByTypeAll(type,pageVo));
    }


    /**
     */
    @ApiOperation(value = "6 通过id查询详情 ", notes = "")
    @GetMapping()
    public Result<Dict> getById(String id) {
        return ResultUtil.data(dictService.getById(id));
    }


}
