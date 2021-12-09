package com.esmooc.legion.classroom.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.classroom.entity.StudentCourse;
import com.esmooc.legion.classroom.service.IStudentCourseService;
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
@Api(tags = "选课中间表管理接口")
@RequestMapping("/studentCourse")
@Transactional
public class StudentCourseController {

//    @Autowired
//    private IStudentCourseService iStudentCourseService;
//
//    @GetMapping("/get/{id}")
//    @ApiOperation(value = "通过id获取")
//    public Result<StudentCourse> get(@PathVariable String id) {
//
//        StudentCourse studentCourse = iStudentCourseService.getById(id);
//        return new ResultUtil<StudentCourse>().setData(studentCourse);
//    }
//
//    @GetMapping("/getAll")
//    @ApiOperation(value = "获取全部数据")
//    public Result<List<StudentCourse>> getAll() {
//
//        List<StudentCourse> list = iStudentCourseService.list();
//        return new ResultUtil<List<StudentCourse>>().setData(list);
//    }
//
//    @GetMapping("/getByPage")
//    @ApiOperation(value = "分页获取")
//    public Result<IPage<StudentCourse>> getByPage(PageVo page) {
//
//        IPage<StudentCourse> data = iStudentCourseService.page(PageUtil.initPage(page));
//        return new ResultUtil<IPage<StudentCourse>>().setData(data);
//    }
//
//    @PostMapping("/insertOrUpdate")
//    @ApiOperation(value = "编辑或更新数据")
//    public Result<StudentCourse> saveOrUpdate(StudentCourse studentCourse) {
//
//        if (iStudentCourseService.saveOrUpdate(studentCourse)) {
//            return new ResultUtil<StudentCourse>().setData(studentCourse);
//        }
//        return new ResultUtil<StudentCourse>().setErrorMsg("操作失败");
//    }
//
//    @PostMapping("/save")
//    @ApiOperation(value = "添加数据")
//    public Result<StudentCourse> save(StudentCourse studentCourse) {
//
//        if (iStudentCourseService.saveOrUpdate(studentCourse)) {
//            return new ResultUtil<StudentCourse>().setData(studentCourse);
//        }
//        return new ResultUtil<StudentCourse>().setErrorMsg("操作失败");
//    }
//
//    @PutMapping("/update")
//    @ApiOperation(value = "更新数据")
//    public Result<StudentCourse> updateById(StudentCourse studentCourse) {
//
//        if (iStudentCourseService.updateById(studentCourse)) {
//            return new ResultUtil<StudentCourse>().setData(studentCourse);
//        }
//        return new ResultUtil<StudentCourse>().setErrorMsg("操作失败");
//    }
//
//    @PostMapping("/delByIds")
//    @ApiOperation(value = "批量通过id删除")
//    public Result<Object> delAllByIds(@RequestParam String[] ids) {
//
//        for (String id : ids) {
//            iStudentCourseService.removeById(id);
//        }
//        return ResultUtil.success("批量通过id删除数据成功");
//    }
//
//
//
//
//    @GetMapping("/getByCondition")
//    @ApiOperation(value = "多条件分页获取")
//    public Result<IPage<StudentCourse>> getByCondition(StudentCourse studentCourse,
//                                                 PageVo page){
//        IPage<StudentCourse> data = iStudentCourseService.page(PageUtil.initPage(page), Wrappers.query(studentCourse));
//        return new ResultUtil<IPage<StudentCourse>>().setData(data);
//    }
}
