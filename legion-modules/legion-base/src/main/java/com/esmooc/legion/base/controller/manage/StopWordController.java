package com.esmooc.legion.base.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.StopWordsUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.core.common.vo.SearchVo;
import com.esmooc.legion.core.entity.StopWord;
import com.esmooc.legion.core.service.StopWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author DaiMao
 */
@Slf4j
@RestController
@Api(tags = "禁用词管理管理接口")
@RequestMapping("/legion/stopWord")
@Transactional
public class StopWordController {

    @Autowired
    private StopWordService stopWordService;

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<StopWord>> getByCondition(StopWord stopWord, SearchVo searchVo, PageVo pageVo) {

        IPage<StopWord> page = stopWordService.findByCondition(stopWord, searchVo, pageVo);
        return ResultUtil.data(page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存数据")
    public Result<StopWord> save(StopWord stopWord) {

      stopWordService.save(stopWord);
        StopWordsUtil.addWord(stopWord.getTitle());
        return ResultUtil.data(stopWord);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "更新数据")
    public Result<StopWord> update(StopWord stopWord) {

        StopWord old = stopWordService.getById(stopWord.getId());
        stopWordService.removeById(stopWord.getId());
        stopWord.setCreateBy(old.getCreateBy());
        stopWord.setCreateTime(new Date());
        stopWordService.save(stopWord);

        StopWordsUtil.removeWord(old.getTitle());
        StopWordsUtil.addWord(stopWord.getTitle());
        return ResultUtil.data(stopWord);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
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
