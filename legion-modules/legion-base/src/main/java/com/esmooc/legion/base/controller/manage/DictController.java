package com.esmooc.legion.base.controller.manage;

import com.esmooc.legion.base.entity.Dict;
import com.esmooc.legion.base.service.DictDataService;
import com.esmooc.legion.base.service.DictService;
import com.esmooc.legion.core.common.redis.RedisTemplateHelper;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "字典管理接口")
@RequestMapping("/legion/dict")
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DictController {

    DictService dictService;
    DictDataService dictDataService;
    RedisTemplateHelper redisTemplate;

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<Dict>> getAll() {

        List<Dict> list = dictService.findAllOrderBySortOrder();
        return new ResultUtil<List<Dict>>().setData(list);
    }

    @PostMapping( "/add")
    @ApiOperation(value = "添加")
    public Result<Object> add(Dict dict) {

        if (dictService.findByType(dict.getType()) != null) {
            return ResultUtil.error("字典类型Type已存在");
        }
        dictService.save(dict);
        return ResultUtil.success("添加成功");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑")
    public Result<Object> edit(Dict dict) {

        Dict old = dictService.getById(dict.getId());
        // 若type修改判断唯一
        if (!old.getType().equals(dict.getType()) && dictService.findByType(dict.getType()) != null) {
            return ResultUtil.error("字典类型Type已存在");
        }
        dictService.updateById(dict);
        // 删除缓存
        redisTemplate.delete("dictData::" + dict.getType());
        return ResultUtil.success("编辑成功");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            Dict dict = dictService.getById(id);
            dictService.removeById(id);
            dictDataService.deleteByDictId(id);
            // 删除缓存
            redisTemplate.delete("dictData::" + dict.getType());
        }
        return ResultUtil.success("删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索字典")
    public Result<List<Dict>> searchPermissionList(@RequestParam String key) {

        List<Dict> list = dictService.findByTitleOrTypeLike(key);
        return new ResultUtil<List<Dict>>().setData(list);
    }
}
