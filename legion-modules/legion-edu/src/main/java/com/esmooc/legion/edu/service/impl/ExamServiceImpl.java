package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.vo.ExamVO;
import com.esmooc.legion.edu.mapper.ExamMapper;
import com.esmooc.legion.edu.mapper.PaperMapper;
import com.esmooc.legion.edu.service.ExamService;
import com.esmooc.legion.edu.service.PaperRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExamServiceImpl
 * @Author Administrator
 * @Date 2021-1-7 9:23
 **/
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private PaperRulesService paperRulesService;

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SecurityUtil securityUtil;
    //TODO 没写完

    @Override
    public IPage<ExamVO> examList(ExamVO examVO, Page page) {
        IPage<ExamVO> list = examMapper.examList(examVO,page);
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
        examVO.setCreateBy(securityUtil.getCurrUser().getId());
        examVO.setCreateTime(BaseUtils.getDateNowSecond());
        examVO.setRulesId(BaseUtils.getUUID());
        if (null == examVO.getClazzId() || "".equals(examVO.getClazzId())) {
            /**
             *  创建考试进来
             */
            // 根据id判断是否新增
            // 根据id查询条数
//            Integer countId = bizExamMapper.getQuestionCount(examVO.getId());
            // 根据bankId 查询当前是否有规则
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
                // 新增
                // 新增edu_exam 表
                String bizExamId = BaseUtils.getUUID();
                examVO.setId(bizExamId);
                examMapper.saveBizExam(examVO);
            } else {
                if (null == examVO.getId() || "".equals(examVO.getId())) {
                    examVO.setId(examVO.getBackId());
                }
                // 修改
                // 修改主表
                examMapper.updateBizExam(examVO);
                // 删除专业
                examMapper.deleteBizExamMajorByExamId(examVO.getId());
                // 删除规则
                examMapper.deleteBizExamRulesByBankId(examVO.getId());
                examMapper.deleteBizExamRulesByExamId(examVO.getId());
            }
            // 新增类别
            String[] majorIds = examVO.getMajorId().split(",");
            for (int i = 0; i < majorIds.length; i++) {
                examMapper.saveBizExamMajor(BaseUtils.getUUID(), examVO.getId(), majorIds[i]);
            }
            // 新增规则
            examMapper.saveRules(examVO);
            m.put("id", examVO.getId());
        } else {
            /**
             *  课程进入
             */
            examMapper.deleteBizExamRulesByExamId(examVO.getId());
            examVO.setId(examVO.getClazzId());
            examMapper.saveRules(examVO);
            m.put("id", examVO.getId());
        }
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
    public void issueExam(String id) {
        // 判断当前id是课程id还是exam表的id
        // 查询exam表
        Integer examIdCount = examMapper.getExamCountById(id);
        if (0 != examIdCount) {
            // 课程， 修改课程是否发布状态
            examMapper.updateBizCourse(id, "1");
        } else {
            // 创建考试
            // 修改发布状态
            examMapper.updateBizExamIssue(id, "1");
            // 查询内部学员
            /**
             *  需求变动 ，需要填加外部学员
             *  Long[] roleIds = new Long[2];
             *  roleIds[0] = Constants.INTERNAL;
             *  roleIds[1] = Constants.EXTERNAL;
             */
            Long[] roleIds = new Long[1];
            roleIds[0] = Constants.INTERNAL;
            // 用户id, group by user_id， 去重，一个人既是内部学员，又是外部学员只会有一个考试
            List<String> userIdList = examMapper.selectUserIdsByRoleIds(roleIds);
            // 查询专业
            String examMajor = paperMapper.getExamMajorById(id);
            // 查询名称
            String examName = paperMapper.getExamNameById(id);
            for (String userId : userIdList) {
                // 根据id和用户id，查询当前用户是否有考试
                Integer examCoutn = examMapper.getExamCountByIdUserId(userId, id);
                if (examCoutn == 0) {
                    examMapper.insertExamPaper(BaseUtils.getUUID(), id, userId, BaseUtils.getDateNowSecond(), Constants.EXAM, examMajor, examName);
                }
            }
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
