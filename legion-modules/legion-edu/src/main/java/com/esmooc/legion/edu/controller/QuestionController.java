package com.esmooc.legion.edu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esmooc.legion.core.common.utils.PageUtil;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.vo.PageVo;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.edu.entity.ExamQuestion;
import com.esmooc.legion.edu.entity.ExamQuestionBank;
import com.esmooc.legion.edu.service.QuestionService;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@DS("edu")
@Api(tags = "题库管理")
@RestController
@RequestMapping("/edu/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 根据id 查询所有的题
     *
     * @param examQuestion
     * @return
     */
    @ApiOperation(value = "根据id 查询所有的题")
    @GetMapping("/questionList")
    public Result<IPage<ExamQuestion>> questionListByClazzId(ExamQuestion examQuestion, PageVo page) {
        // 校验是否传课程id
        if (null == examQuestion) {
            return null;
        }
        if ((null == examQuestion.getClazzId() || "".equals(examQuestion.getClazzId())) && (null == examQuestion.getBankId() || "".equals(examQuestion.getBankId()))) {
            return null;
        }
        IPage<ExamQuestion> list = questionService.questionListByClazzId(examQuestion, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }

    /**
     * 根据id 删除问题， 批量删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id 删除问题， 批量删除")
    @DeleteMapping("/deleteQuestion")
    public Result deleteQuestionById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数错误");
        }
        questionService.deleteQuestionById(id);
        return ResultUtil.success();
    }

    /**
     * 保存问题
     *
     * @param examQuestion
     * @return
     */
    @ApiOperation(value = "保存问题")
    @PostMapping("/saveQuestion")
    public Result saveQuestion( ExamQuestion examQuestion) {
        /**
         *  校验，类别id必传
         */
        if (null == examQuestion) {
            return ResultUtil.error("参数错误");
        }
        if (null == examQuestion.getMajorId() || "".equals(examQuestion.getMajorId())) {
            return ResultUtil.error("参数错误");
        }
        questionService.saveQuestion(examQuestion);
        return ResultUtil.success();
    }

    /**
     * 通过id查询试题信息
     *
     * @return
     */
    @ApiOperation(value = "通过id查询试题信息")
    @GetMapping("/getQuestionById")
    public Result getQuestionById(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数错误");
        }
        return ResultUtil.data(questionService.getQuestionById(id));
    }

    /**
     * 修改问题
     *
     * @param examQuestion
     * @return
     */
    @ApiOperation(value = "修改问题")
    @PutMapping("/putQuestion")
    public Result putQuestion( ExamQuestion examQuestion) {
        // 校验数据
        if (null == examQuestion) {
            return ResultUtil.error("参数错误");
        }
        if (null == examQuestion.getId() || "".equals(examQuestion.getId())) {
            return ResultUtil.error("参数错误");
        }
        questionService.putQuestion(examQuestion);
        return ResultUtil.success();
    }

    /**
     * 创建题库
     *
     * @return
     */
    @ApiOperation(value = "创建题库")
    @PostMapping("/createQuestionBank")
    public Result createQuestionBank( ExamQuestionBank examQuestionBank) {
        /**
         *  校验， 题库名称、类别不能为空
         */
        if (null == examQuestionBank) {
            return ResultUtil.error("参数错误");
        }
        if (StrUtil.isBlank(examQuestionBank.getTitle())) {
            return ResultUtil.error("参数错误");
        }
        if (null == examQuestionBank.getMajorId() || "".equals(examQuestionBank.getTitle())) {
            return ResultUtil.error("参数错误");
        }
        return ResultUtil.data(questionService.createQuestionBank(examQuestionBank));
    }

    /**
     * 查询题库列表
     *
     * @param examQuestionBank
     * @return
     */
    @ApiOperation(value = "查询题库列表")
    @GetMapping("/selectQuestionBank")
    public Result<IPage<ExamQuestionBank>> selectQuestionBank(ExamQuestionBank examQuestionBank, PageVo page) {
        IPage<ExamQuestionBank> list = questionService.selectQuestionBank(examQuestionBank, PageUtil.initPage(page));
        return ResultUtil.data(list);
    }

    /**
     * 删除题库多选
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除题库多选")
    @DeleteMapping("/deleteQuestionBank")
    public Result deleteQuestionBank(String ids) {
        /**
         *  校验
         */
        if (null == ids || "".equals(ids)) {
            return ResultUtil.error("参数错误");
        }
        questionService.deleteQuestionBank(ids);
        return ResultUtil.success();
    }

    @ApiOperation(value = "导入")
    @PostMapping("/importData")
    public Result importData(@RequestExcel List<ExamQuestion> questions, BindingResult bindingResult, String backId, String clazzId) throws Exception {
        if (null == clazzId || "".equals(clazzId) || "null".equals(clazzId)) {
            clazzId = null;
        }
        if (null == backId || "".equals(backId) || "null".equals(backId)) {
            backId = null;
        }
        String message = questionService.importUser(questions, backId, clazzId);
        return ResultUtil.data(questions, "测试一下 这个是导入的数据");
    }


    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "备份?")
    @GetMapping("/bankDetails")
    public Result bankDetails(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数错误");
        }
        Map m = questionService.bankDetails(id);
        return ResultUtil.data(m);
    }

    /**
     * 根据类别查询题库数量
     *
     * @param id/*9-+-
     * @return
     */
    @ApiOperation(value = "根据类别查询题库数量")
    @GetMapping("/paperDetails")
    public Result paperDetails(String id) {
        // 校验
        if (null == id || "".equals(id)) {
            return ResultUtil.error("参数错误");
        }
        Map m = questionService.paperDetails(id);
        return ResultUtil.data(m);
    }

}
