package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.base.entity.Dict;
import com.esmooc.legion.base.entity.DictData;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.base.service.DictService;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "字典数据管理接口")
@RequestMapping("/legion/dictData")
@CacheConfig(cacheNames = "dictData")
@Transactional
public class DictDataController {

    @Autowired
    private DictService dictService;

    @Autowired
    private DictDataService dictDataService;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<IPage<DictData>> getByCondition(DictData dictData,
                                                  PageVo pageVo) {

        IPage<DictData> page = dictDataService.findByCondition(dictData, pageVo);
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/getByType/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "通过类型获取")
    @Cacheable(key = "#type")
    public Result<Object> getByType(@PathVariable String type) {
        //TODO 这个地方一直不太理想
        List<DictData> list = dictDataService.findByDictId(type);
        return ResultUtil.data(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result<Object> add(DictData dictData) {

        Dict dict = dictService.getById(dictData.getDictId());
        if (dict == null) {
            return ResultUtil.error("字典类型id不存在");
        }
        dictData.setType(dict.getType());
        String typeCode = dict.getType() + "_" + dictData.getValue();
        dictData.setTypeCode(typeCode);
        DictData data = dictDataService.findByTypeCode(typeCode);
        if (data != null) {
            return ResultUtil.error("类型编码重复");
        }
        dictDataService.save(dictData);
        // 删除缓存
        redisTemplate.delete("dictData::" + dict.getType());
        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Object> edit(DictData dictData) {

        dictDataService.updateById(dictData);
        // 删除缓存
        Dict dict = dictService.getById(dictData.getDictId());
        String typeCode = dict.getType() + "_" + dictData.getValue();
        dictData.setTypeCode(typeCode);
        DictData data = dictDataService.findByTypeCode(typeCode);
        if (data != null) {
            return ResultUtil.error("类型编码重复");
        }
        dictDataService.updateById(dictData);

        redisTemplate.delete("dictData::" + dict.getType());
        return ResultUtil.success("编辑成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            DictData dictData = dictDataService.getById(id);
            if (dictData == null) {
                return ResultUtil.error("数据不存在");
            }
            Dict dict = dictService.getById(dictData.getDictId());
            dictDataService.removeById(id);
            // 删除缓存
            redisTemplate.delete("dictData::" + dict.getType());
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
