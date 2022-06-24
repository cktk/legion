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
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "Dict", tags = "字典表管理")
public class DictController {
    private final DictService dictService;

    /**
     * 通过yope查询
     *
     * @return 已禁用启用都返回
     */
    @ApiOperation(value = "查询所有-通过TYPE查询不分页", notes = "分页查询")
    @GetMapping("/all/{type}")
    public Result<List<Dict>> findByTypeAll(@PathVariable("type") String type) {
        return ResultUtil.data(dictService.findByTypeAll(type));
    }

    /**
     * 通过yope查询 只查询已启用的
     */
    @ApiOperation(value = "通过TYPE查询所有字典只查询已启用的", notes = "分页查询")
    @GetMapping("/{type}")
    public Result<List<Dict>> getByType(@PathVariable("type") String type) {
        return ResultUtil.data(dictService.findByType(type));
    }

    /**
     * 通过yope查询
     *
     * @return
     */
    @ApiOperation(value = "通过TypeCode获取")
    @GetMapping("/code/{code}")
    public Result<Dict> getBytypeCode(@PathVariable("typeCode") String typeCode) {
        return ResultUtil.data(dictService.findByCode(typeCode));
    }

    /**
     * 通过id查询字典表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping
    public Result<Dict> getById(Long id) {
        return ResultUtil.data(dictService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param dict 字典表
     * @return
     */
    @ApiOperation(value = "分页查询 只查询父类 所有的 条件 状态 是否是父类 是否系统内置 标签 值 type 描述 备注 拼音  ", notes = "分页查询 ")
    @GetMapping("/page")
    public Result<IPage<Dict>> getDictDataPage(PageVo page, Dict dict) {

        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StrUtil.isNotBlank(dict.getStatus()), Dict::getStatus, dict.getStatus())
                .eq(Dict::isParent, true)
                .eq(dict.getSystemFlag()!=null, Dict::getSystemFlag, dict.getSystemFlag())
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

    @ApiOperation(value = "分页查询 只少选子类  ", notes = "分页查询")
    @GetMapping("/search/{key}")
    public Result<List<Dict>> like(@PathVariable("key") String key) {
        return ResultUtil.data(dictService.findByTitleOrTypeLike(key));
    }


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "启用和禁用", notes = "启用禁用")
    @PutMapping("/switch/{id}/{status}")
    public Result<Dict> switchs(@PathVariable("id") Long id,
                                @PathVariable("status") String status) {
        return ResultUtil.data(dictService.switchs(id, status));
    }


    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public Result<Dict> add( Dict dict) {
        dict.setStatus(SystemConstant.FLAG_Y);
        // 先判断保存父类还是子类
        if (dict.isParent()) {
            //保存父类
            Dict dictList = dictService.findParentTypeAll(dict.getType());
            if (dictList==null) {
                dict.setCode("");
                dict.setLabel("");
                dict.setValue("");
            } else {
                return ResultUtil.error("字典类型Type已存在");
            }

        } else {

            Dict pDict = dictService.findParentTypeAll(dict.getType());
            if (pDict==null){
                return ResultUtil.error("字典类型Type不存在 请先添加父类");
            }

            Dict typeCode = dictService.findByCode(dict.getCode());
            if (typeCode != null) {
                dict.setStatus(SystemConstant.FLAG_N);
            }
        }

        if (StrUtil.isNotEmpty(dict.getLabel())) {
            dict.setPinyin(PinyinUtil.getFirstLetter(dict.getLabel(), "").toUpperCase());
        }

        if (dictService.findByCode(dict.getCode()) != null) {
            throw new LegionException("标准编码重复(CODE)");
        }

        if (dictService.save(dict)) {
            return ResultUtil.data(dict);
        }

        throw new LegionException("保存字典失败");
    }


    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public Result<Dict> edit( Dict dict) {
        Dict old = dictService.getById(dict.getId());

        if (old == null) throw new LegionException("字典不存在");

        if (old.isParent()) {
            dict.setType(old.getType());
            dict.setCode("");
            dict.setLabel("");
            dict.setValue("");
        } else {
            //子类
            if ((dictService.findByCodeAll(dict.getCode()) != null)) {
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
    @ApiOperation(value = "通过id删除字典表 系统内置不允许删除", notes = "通过id删除字典表")
    @DeleteMapping()
    public Result<Dict> removeById( Long[] ids) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Dict::getId, ids);
        List<Dict> datas = dictService.list(queryWrapper);
        for (Dict data : datas) {
            if (SystemConstant.FLAG_Y.equals(data.getSystemFlag())) {
                throw new LegionException("系统内置字典不允许删除");
            }
            if (SystemConstant.FLAG_Y.equals(data.isParent())) {
                List<Dict> dictData = dictService.findByTypeAll(data.getType());
                if (dictData != null && dictData.size() > 0) {
                    throw new LegionException("请先删除字典子项");
                }
            }
        }
        return ResultUtil.ok(dictService.removeByIds(Arrays.asList(ids)));
    }


}
