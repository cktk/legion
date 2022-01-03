package com.esmooc.legion.pacs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.pacs.entity.Study;
import com.esmooc.legion.pacs.entity.dto.StudyDTO;
import com.esmooc.legion.pacs.entity.vo.StudiesVo;
import com.esmooc.legion.pacs.service.PatientService;
import com.esmooc.legion.pacs.service.StudyService;
import io.swagger.annotations.*;
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
@RequestMapping("/paca/studies")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SearchController {

    StudyService studyService;

    PatientService patientService;


    @GetMapping("/{id}")
    @ApiOperation(value = "条件搜索对应的片子列表")
    public Result<List<Study>> getStudiesById(@PathVariable String id) {
        //SELECT data FROM study WHERE 1 = 1 AND data @> '{"studyInstanceUID": "1.2.410.200010.86.101.13.202108040109"}'
        QueryWrapper<Study> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Study::getStudyInstanceUid, id);
        List<Study> studyList = studyService.list(queryWrapper);
        return ResultUtil.data(studyList);
    }


    /**
     * @Author: Daimao
     * @Date: 2021/12/21 17:15
     * @description:
     * @version: 1.0
     * @return: null
     */
    @GetMapping("/search")
    @ApiOperation(value = "条件搜索对应的片子列表", notes = "备注哈哈哈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageVo", value = "分页参数", required = true, paramType = "form"),
            @ApiImplicitParam(name = "modality", value = "类型", required = false, paramType = "form"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "form", dataType = "date"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "form", dataType = "date"),
            @ApiImplicitParam(name = "patientId", value = "病历号", required = false, paramType = "form"),
            @ApiImplicitParam(name = "patientName", value = "病人姓名", required = false, paramType = "form"),
            @ApiImplicitParam(name = "patientSex", value = "病人性别", required = false, paramType = "form"),
            @ApiImplicitParam(name = "patientAge", value = "病人年龄", required = false, paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public Result<Object> searchByPage(PageVo pageVo,
                                       StudiesVo studiesVo
    ) {
        Page<StudyDTO> page = studyService.searchByPage(PageUtil.initPage(pageVo), studiesVo);
        return ResultUtil.data(page);
    }


}
