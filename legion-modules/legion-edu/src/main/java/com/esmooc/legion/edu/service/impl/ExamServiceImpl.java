package com.esmooc.legion.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.*;
import com.esmooc.legion.edu.entity.vo.ExamVO;
import com.esmooc.legion.edu.mapper.ExamMapper;
import com.esmooc.legion.edu.mapper.PaperMapper;
import com.esmooc.legion.edu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExamServiceImpl
 * @Author Administrator
 * @Date 2021-1-7 9:23
 **/
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private PaperRulesService paperRulesService;

    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private ExamMajorService examMajorService;
    @Autowired
    private ExamPaperRulesService examPaperRulesService;

    //TODO 没写完

    @Override
    public IPage<ExamVO> examList(ExamVO examVO, Page page) {
        IPage<ExamVO> list = examMapper.examList(examVO, page);
        // 解析规则
        for (ExamVO data : list.getRecords()) {
            String rules = data.getRules();
            if (null == rules || "".equals(rules)) {
                // 查询规则
                rules = examMapper.getRules(data.getId());
            }
            // 解析规则
            Map ruleMap = paperRulesService.parsingRules(rules);
            if (ruleMap.size() > 0) {
                data.setRadioCount(ruleMap.get("radio").toString());
                data.setCheckboxCount(ruleMap.get("checkbox").toString());
                data.setGradeCount(ruleMap.get("gradeCount").toString());
                data.setJudgmentCount(ruleMap.get("judgment").toString());
                data.setGradePass(ruleMap.get("gradePass").toString());
                data.setQuestionCount(String.valueOf(Integer.valueOf(data.getRadioCount()) + Integer.valueOf(data.getJudgmentCount()) + Integer.valueOf(data.getCheckboxCount())));
            }
        }
        return list;
    }

    @Override
    public Map saveExam(ExamVO examVO) {
        Map m = new HashMap<>();

        // 构建参数
        examVO.setRulesId(BaseUtils.getUUID());
        if (null == examVO.getClazzId() || "".equals(examVO.getClazzId())) {
            /**
             *  创建考试进来
             */

            Integer countId = 0;
            if (null == examVO.getId() || "".equals(examVO.getId())) {
                countId = examMapper.getBankIdCount(examVO.getBackId());
                // 查询id
            } else {
                if (null == examVO.getBackId() || "".equals(examVO.getBackId())) {
                    countId = 1;
                } else {
                    countId = examMapper.getBankIdCount(examVO.getBackId());
                }
            }

            if (0 == countId) {
                String examId = BaseUtils.getUUID();
                Exam exam = new Exam();
                examVO.setId(examId);
                exam.setId(examId);
                exam.setTitle(examVO.getTitle());
                exam.setBankId(examVO.getBackId());
                if (!this.save(exam)) {
                    throw new LegionException("保存失败");
                }
            } else {
                if (null == examVO.getId() || "".equals(examVO.getId())) {
                    examVO.setId(examVO.getBackId());
                }
                // 修改
                // 修改主表
                Exam exam = new Exam();
                exam.setId(examVO.getId());
                exam.setTitle(exam.getTitle());
                if (!this.updateById(exam)) {
                    throw new LegionException("更新失败");
                }
                // 删除规则
                examMapper.deleteBizExamRulesByBankId(examVO.getId());
                examMapper.deleteBizExamRulesByExamId(examVO.getId());
            }
            // 新增类别
            String[] majorIds = examVO.getMajorId().split(",");
            for (int i = 0; i < majorIds.length; i++) {
                ExamMajor major = new ExamMajor();
                major.setId(BaseUtils.getUUID());
                major.setExamId(examVO.getId());
                major.setMajorId(majorIds[i]);
                if (!examMajorService.save(major)) {
                    throw new LegionException("类别保存失败");
                }
            }

        } else {
            /**
             *  课程进入
             */
            examMapper.deleteBizExamRulesByExamId(examVO.getId());
            examVO.setId(examVO.getClazzId());
        }

        ExamPaperRules examPaperRules = new ExamPaperRules();
        examPaperRules.setId(examVO.getRulesId());
        examPaperRules.setClazzId(examVO.getId());
        examPaperRules.setRules(examVO.getRules());
        examPaperRules.setBankId(examVO.getBackId());
        if (!examPaperRulesService.save(examPaperRules)) {
            throw new LegionException("试卷规则保存失败");
        }
        m.put("id", examVO.getId());
        return m;
    }


    @Override
    public Map getExamById(String id) {
        Map m = new HashMap<>();
        ExamVO data = examMapper.getExamById(id);
        m.put("data", data);
        return m;
    }


    @Override
    public boolean issueExam(String id, ArrayList<String> userIds, ArrayList<String> deptIds, ArrayList<String> roleIds) {
        // 判断当前id是课程id还是exam表的id
        // 查询exam表
        Integer examIdCount = examMapper.getExamCountById(id);
        if (0 != examIdCount) {
            // 课程， 修改课程是否发布状态
            Course course = new Course();
            course.setExamIssue(1);
            course.setId(id);
            return courseService.updateById(course);
        } else {
            // 创建考试
            // 修改发布状态
            Exam exam = new Exam();
            exam.setId(id);
            exam.setIssue(1);

            if (! this.updateById(exam)) {
                //"错误"
                throw new LegionException("状态更新失败");
            }

            ArrayList<ExamPaper> examPapers = new ArrayList<ExamPaper>();

            // 查询专业
            String examMajor = paperMapper.getExamMajorById(id);

            if (StrUtil.isBlank(examMajor)) {
                throw new LegionException("参数有误 examMajor 为空");
            }

            // 查询名称
            String examName = paperMapper.getExamNameById(id);

            if (StrUtil.isBlank(examName)) {
                throw new LegionException("参数有误 examName 为空");
            }


            if (userIds != null) {
                for (String userId : userIds) {
                    ExamPaper examPaper = new ExamPaper();
                    examPaper.setClazzId(id);
                    examPaper.setUserId(userId);
                    examPaper.setType(Constants.EXAM);
                    examPaper.setMajorId(examMajor);
                    examPaper.setClazzName(examName);
                    examPaper.setUserType(Constants.USER);
                    examPapers.add(examPaper);

                }
            }


            if (deptIds != null) {
                for (String deptId : deptIds) {
                    ExamPaper examPaper = new ExamPaper();
                    examPaper.setClazzId(id);
                    examPaper.setUserId(deptId);
                    examPaper.setType(Constants.EXAM);
                    examPaper.setMajorId(examMajor);
                    examPaper.setClazzName(examName);
                    examPaper.setUserType(Constants.DEPT);
                    examPapers.add(examPaper);
                }

            }

            if (roleIds != null) {
                for (String role : roleIds) {
                    ExamPaper examPaper = new ExamPaper();
                    examPaper.setClazzId(id);
                    examPaper.setUserId(role);
                    examPaper.setType(Constants.EXAM);
                    examPaper.setMajorId(examMajor);
                    examPaper.setClazzName(examName);
                    examPaper.setUserType(Constants.ROLE);
                    examPapers.add(examPaper);
                }
            }


            return examPaperService.saveBatch(examPapers, 100);
        }
    }

    @Override
    public void deleteExam(String id) {
        String[] ids = id.split(",");
        // 删除试卷
        for (int i = 0; i < ids.length; i++) {
            examMapper.deleteBizExam(ids[i]);
            examMapper.deleteBizExamGradeIsNull(ids[i]);
        }
    }

    @Override
    public void withdrawExam(String id) {
        // 修改edu_exam 状态
        examMapper.updateBizExamIssue(id, "0");
        // 删除生成的试卷
        examMapper.deleteBizExamGradeIsNull(id);
    }

    @Override
    public int isUpdateRules(String id) {
        String grade = examMapper.selectSumGradeById(id);
        if (null == grade || "".equals(grade)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Map checkBankCreate(String id) {
        Map m = new HashMap<>();
        // 根据id 查询人员考试成绩
        Integer count = examMapper.getUserGrade(id);
        if (count == 0) {
            m.put("code", true);
            // 根据题库id 查询ClazzId
            String clazzId = examMapper.getClazzId(id);
            count = examMapper.getUserGrade(clazzId);
            if (count != 0) {
                m.put("code", false);
                m.put("msg", "当前试卷已有学员考试，不允许修改！");
            } else {
                String bankId = examMapper.getClazzIds(id);
                count = examMapper.getUserGrade(bankId);
                if (count != 0) {
                    m.put("code", false);
                    m.put("msg", "当前试卷已有学员考试，不允许修改！");
                }
            }
        } else {
            m.put("code", false);
            m.put("msg", "当前试卷已有学员考试，不允许修改！");
        }
        return m;
    }
}


