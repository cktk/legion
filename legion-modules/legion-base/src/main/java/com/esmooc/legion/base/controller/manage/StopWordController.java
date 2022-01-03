package com.esmooc.legion.base.controller.manage;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SearchUtil;
import com.esmooc.legion.core.common.utils.StopWordsUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.StopWord;
import com.esmooc.legion.core.service.StopWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "禁用词管理管理接口")
@RequestMapping("/legion/stopWord")
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class StopWordController {

     StopWordService stopWordService;

    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<Page<StopWord>> getByCondition(String title, PageVo pageVo){
        QueryWrapper<StopWord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StrUtil.isNotEmpty(title),StopWord::getTitle,title);

        return new ResultUtil<Page<StopWord>>().setData(stopWordService.page(PageUtil.initPage(pageVo),queryWrapper));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存数据")
    public Result<StopWord> save(StopWord stopWord) {

        stopWordService.save(stopWord);
        StopWordsUtil.addWord(stopWord.getTitle());
        return new ResultUtil<StopWord>().setData(stopWord);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "更新数据")
    public Result<StopWord> update(StopWord stopWord) {

        StopWord old = stopWordService.getById(stopWord.getId());
        stopWordService.updateById(stopWord);
        StopWordsUtil.removeWord(old.getTitle());
        StopWordsUtil.addWord(stopWord.getTitle());
        return new ResultUtil<StopWord>().setData(stopWord);
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            StopWord stopWord = stopWordService.getById(id);
            stopWordService.removeById(id);
            StopWordsUtil.removeWord(stopWord.getTitle());
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }

}
