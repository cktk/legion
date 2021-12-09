package com.esmooc.legion.classroom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.classroom.entity.StudentInfo;
import com.esmooc.legion.classroom.service.IStudentInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Daimao
 */
@Slf4j
@RestController
@Api(tags = "学生信息管理接口")
@RequestMapping("/studentInfo")
@Transactional
public class StudentInfoController {

    @Autowired
    private IStudentInfoService iStudentInfoService;

    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<StudentInfo> get(@PathVariable String id) {

        StudentInfo studentInfo = iStudentInfoService.getById(id);
        return new ResultUtil<StudentInfo>().setData(studentInfo);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取全部数据")
    public Result<List<StudentInfo>> getAll() {

        List<StudentInfo> list = iStudentInfoService.list();
        return new ResultUtil<List<StudentInfo>>().setData(list);
    }

    @GetMapping("/getByPage")
    @ApiOperation(value = "分页获取")
    public Result<IPage<StudentInfo>> getByPage(PageVo page) {

        IPage<StudentInfo> data = iStudentInfoService.page(PageUtil.initPage(page));
        return new ResultUtil<IPage<StudentInfo>>().setData(data);
    }

    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "编辑或更新数据")
    public Result<StudentInfo> saveOrUpdate(StudentInfo studentInfo) {

        if (iStudentInfoService.saveOrUpdate(studentInfo)) {
            return new ResultUtil<StudentInfo>().setData(studentInfo);
        }
        return new ResultUtil<StudentInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/saveBatch")
    @ApiOperation(value = "批量添加数据")
    public Result<List<StudentInfo>> saveBatch(List<StudentInfo> studentInfos) {

        if (iStudentInfoService.saveBatch(studentInfos)) {
            return new ResultUtil<List<StudentInfo>>().setData(studentInfos);
        }
        return new ResultUtil<List<StudentInfo>>().setErrorMsg("操作失败");
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加数据")
    public Result<StudentInfo> save(StudentInfo studentInfo) {

        if (iStudentInfoService.saveOrUpdate(studentInfo)) {
            return new ResultUtil<StudentInfo>().setData(studentInfo);
        }
        return new ResultUtil<StudentInfo>().setErrorMsg("操作失败");
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新数据")
    public Result<StudentInfo> updateById(StudentInfo studentInfo) {

        if (iStudentInfoService.updateById(studentInfo)) {
            return new ResultUtil<StudentInfo>().setData(studentInfo);
        }
        return new ResultUtil<StudentInfo>().setErrorMsg("操作失败");
    }

    @PostMapping("/delByIds")
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iStudentInfoService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }




    @GetMapping("/getByCondition")
    @ApiOperation(value = "多条件分页获取")
    public Result<IPage<StudentInfo>> getByCondition(StudentInfo studentInfo,
                                                 PageVo page){
        studentInfo.setId(null);
        IPage<StudentInfo> data = iStudentInfoService.page(PageUtil.initPage(page), Wrappers.query(studentInfo));
        return new ResultUtil<IPage<StudentInfo>>().setData(data);
    }
}
