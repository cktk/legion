package com.esmooc.legion.pacs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.pacs.entity.Instance;
import com.esmooc.legion.pacs.entity.dto.StudyDTO;
import com.esmooc.legion.pacs.entity.vo.StudiesVo;
import com.esmooc.legion.pacs.service.InstanceService;
import com.esmooc.legion.pacs.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: SearchController
 * @Description:
 * @date 2021年12月19日20点45分
 **/
@Slf4j
@RestController
@Api(tags = "搜索接口")
@RequestMapping("/pacs/studies")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchController {

    StudyService studyService;

    InstanceService instanceService;


    /**
     * @Author: Daimao
     * @Date: 2021/12/21 17:15
     * @description:
     * @version: 1.0
     * @return: null
     */
    @GetMapping("/search")
    @ApiOperation(value = "条件搜索对应的片子列表", notes = "备哈哈哈")
    public Result<Object> searchByPage(@ApiParam(name = "分页对象支持排序", value = "form data格式", required = true) PageVo pageVo,
                                       @ApiParam(name = "筛选条件", value = "form data格式", required = true) StudiesVo studiesVo) {
        log.info("接收参数 {} {}", pageVo, studiesVo);
        Page<StudyDTO> page = studyService.searchByPage(PageUtil.initPage(pageVo), studiesVo);
        return ResultUtil.data(page);
    }

    @GetMapping("/list/{uid}")
    @ApiOperation(value = "获取所有片子")
    public Result<List<Instance>> getInstanceByUid(@ApiParam(name = "uid", value = "片子的+存储id", required = true) @PathVariable String uid) {
        QueryWrapper<Instance> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Instance::getSeriesInstanceUid, uid);
        List<Instance> list = instanceService.list(queryWrapper);
        return ResultUtil.data(list);
    }


}
