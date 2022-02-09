package com.esmooc.legion.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.ExamPaper;
import com.esmooc.legion.edu.entity.ExamPaperRules;
import com.esmooc.legion.edu.entity.ExamQuestion;
import com.esmooc.legion.edu.entity.SubmitPaper;
import com.esmooc.legion.edu.mapper.PaperMapper;
import com.esmooc.legion.edu.mapper.PaperRulesMapper;
import com.esmooc.legion.edu.mapper.QuestionMapper;
import com.esmooc.legion.edu.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName PaperServiceImpl
 * @Author Administrator
 * @Date 2020-12-30 9:22
 **/
@Service
public class PaperServiceImpl  extends ServiceImpl<PaperMapper, ExamPaper> implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private PaperRulesMapper paperRulesMapper;

    @Autowired
    private PaperRulesService paperRulesService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private LearningRecordService learningRecordService;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public Map getPaper(String clazzId, String type, User user) {
        Map m = new HashMap<>();
        ExamPaper paper = paperMapper.getLastPaperByClazzIdUserId(clazzId, securityUtil.getCurrUser().getId(), type);
        m.put("paper", paper);
        return m;
    }

    @Override
    public Map startPracticing(String clazzId, Integer type, String userId) {
        Map m = new HashMap<>();
        // 查询是否已经学习
        if (1==type) {
            Integer studyType = paperMapper.getStudyTypeByClazzIdUserId(clazzId, userId);
            if (!Constants.ENDSTUDY.equals(studyType)) {
                throw new LegionException("请重新学习视频课程后再进行考试！",509);
            }
        }
        // 查询生成规则
        String rules = paperRulesMapper.getRulesByClazzId(clazzId);
        if (null == rules || "".equals(rules)) {
            throw new LegionException("系统异常，请联系管理员！",509);
        }
        // 查询题库id
        ExamPaperRules examPaperRules = examPaperRulesService.getByClazzId(clazzId);
        // 查询题库id
        String bankId = examPaperRules.getBankId();
        // 根据课程id查询题库id
        String bank = paperMapper.getBankIdByClazzId(clazzId);
        if (null == bank || "".equals(bank)) {
            throw new LegionException("当前试卷题数发生变化，请联系管理员！",509);
        }
        // 查询课程相关信息
        String clazzMajor = paperRulesMapper.getClazzMajorById(clazzId);
        String clazzName = paperRulesMapper.getClazzNameById(clazzId);
        String paperId = BaseUtils.getUUID();
        // 新增试卷主表
        m = generateTestQuestions(paperId, rules, clazzId, "1", bankId);
        if (Boolean.valueOf(m.get("code").toString())) {
            ExamPaper examPaper = new ExamPaper();
            examPaper.setId(paperId);
            examPaper.setClazzId(clazzId);
            examPaper.setUserId(userId);
            examPaper.setType(type);
            examPaper.setClazzName(clazzName);
            examPaper.setMajorId(clazzMajor);
            if (this.save(examPaper)) {
                throw new LegionException("保存失败");
            }
        }

        m.put("paperId", paperId);
        return m;
    }
    @Autowired
    private ExamQuestionService  examQuestionService;

    private Map generateTestQuestions(String paperId, String rules, String clazzId, String type, String bankId) {
        Map m = new HashMap<>();
        /**
         *  解析规则
         */
        Map ruleMap = paperRulesService.parsingRules(rules);
        /**
         *  校验题库数量与规则
         */
        if ("1".equals(type)) {
            Integer radioCount = paperMapper.getQuestionCountByClazzId(clazzId, Constants.RADIO, bankId);
            if (radioCount < Integer.valueOf(ruleMap.get("radio").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",578);
            }
            Integer checkboxCount = paperMapper.getQuestionCountByClazzId(clazzId, Constants.CHECKBOX, bankId);
            if (checkboxCount < Integer.valueOf(ruleMap.get("checkbox").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",577);
            }
            Integer judgmentCount = paperMapper.getQuestionCountByClazzId(clazzId, Constants.JUDGMENT, bankId);
            if (judgmentCount < Integer.valueOf(ruleMap.get("judgment").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",576);
            }
        } else {
            // 根据clazzId查询考试范围
            List<String> majorIds = paperMapper.getPaperMajorIdsById(clazzId);
            Integer radioCount = paperMapper.getQuestionCountByMajorIds(majorIds, Constants.RADIO, bankId);
            if (radioCount < Integer.valueOf(ruleMap.get("radio").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",575);
            }
            Integer checkboxCount = paperMapper.getQuestionCountByMajorIds(majorIds, Constants.CHECKBOX, bankId);
            if (checkboxCount < Integer.valueOf(ruleMap.get("checkbox").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",574);
            }
            Integer judgmentCount = paperMapper.getQuestionCountByMajorIds(majorIds, Constants.JUDGMENT, bankId);
            if (judgmentCount < Integer.valueOf(ruleMap.get("judgment").toString())) {
                throw new LegionException("当前试卷题数发生变化，请联系管理员！",573);
            }
        }
        // 单选题
        List<String> radioIds = new ArrayList<>();
        // 多选题
        List<String> checkboxIds = new ArrayList<>();
        // 判断题
        List<String> judgmentIds = new ArrayList<>();
        // 判断是否创建考试
        if ("1".equals(type)) {
            // 练习，考试
            radioIds = paperMapper.getRandomQuestionIdByClazzId(clazzId, Constants.RADIO, ruleMap.get("radio").toString(), bankId);
            checkboxIds = paperMapper.getRandomQuestionIdByClazzId(clazzId, Constants.CHECKBOX, ruleMap.get("checkbox").toString(), bankId);
            judgmentIds = paperMapper.getRandomQuestionIdByClazzId(clazzId, Constants.JUDGMENT, ruleMap.get("judgment").toString(), bankId);
        } else {
            // 根据clazzId查询考试范围
            List<String> majorIds = paperMapper.getPaperMajorIdsById(clazzId);
            radioIds = paperMapper.getRandomQuestionIdByMajorIds(majorIds, Constants.RADIO,Integer.parseInt(ruleMap.get("radio")+"") , bankId);
            checkboxIds = paperMapper.getRandomQuestionIdByMajorIds(majorIds, Constants.CHECKBOX, Integer.parseInt( ruleMap.get("checkbox")+""), bankId);
            judgmentIds = paperMapper.getRandomQuestionIdByMajorIds(majorIds, Constants.JUDGMENT, Integer.parseInt( ruleMap.get("judgment")+""), bankId);
        }
        List<ExamQuestion> radioList = new ArrayList<>();
        List<ExamQuestion> checkList = new ArrayList<>();
        List<ExamQuestion> judgmentList = new ArrayList<>();
        // 新增单选题
        if (radioIds.size() > 0) {
            paperMapper.batchSavePaperQuestion(BaseUtils.getUUID(), paperId, radioIds);
            // 构建试卷 单选题
            radioList = questionMapper.getQuestionById(radioIds);
        }
        // 新增多选题
        if (checkboxIds.size() > 0) {
            paperMapper.batchSavePaperQuestion(BaseUtils.getUUID(), paperId, checkboxIds.size() > 0 ? checkboxIds : null);
            // 多选题
            checkList = questionMapper.getQuestionById(checkboxIds);
        }
        // 新增判断题
        if (judgmentIds.size() > 0) {
            paperMapper.batchSavePaperQuestion(BaseUtils.getUUID(), paperId, judgmentIds.size() > 0 ? judgmentIds : null);
            // 判断题
            judgmentList = questionMapper.getQuestionById(judgmentIds);
        }
        m.put("radio", radioList);
        m.put("check", checkList);
        m.put("judgment", judgmentList);
        m.put("ruleMap", ruleMap);
        m.put("code", true);
        return m;
    }

    @Autowired
    private ExamPaperRulesService examPaperRulesService;
    /**
     * 创建考试- 开始考试
     *
     * @param id edu_exam 表的id
     * @return
     */
    @Override
    public Map satartExam(String id) {
        Map m = new HashMap<>();
        // 查询生成规则
        String rules = paperRulesMapper.getRulesByClazzId(id);
        if (StrUtil.isBlank(rules)) {
            // 查询考试clazzId
            String clazzId = paperRulesMapper.getExamClazzId(id);
            rules = paperRulesMapper.getRulesByClazzId(clazzId);
            if (null == rules || "".equals(rules)) {
               throw  new LegionException("试卷错误暂无数据");
            }
        }
       ExamPaperRules examPaperRules = examPaperRulesService.getByClazzId(id);
        // 查询题库id
        String bankId = examPaperRules.getBankId();
        //if(null == bankId || "".equals(bankId)){
        //    m.put("code", false);
        //    m.put("msg", "当前试卷题数发生变化，请联系管理员！");
        //    return m;
        //}
        // 查询最后一次考试的id
        ExamPaper lastPaper = paperMapper.selectLastPaperId(id, securityUtil.getCurrUser().getId());
        // 查询最后一次考试是否关联试题
        Integer lastPaperQuestionCount = paperMapper.selectCountPaperQuestion(lastPaper.getId());
        String newPaperId = "";
        if (0 == lastPaperQuestionCount) {
            // 在当前试卷新增题目
            m = generateTestQuestions(lastPaper.getId(), rules, id, "2", bankId);
            m.put("paperId", lastPaper.getId());
        } else {
            // 复制试卷新增题
            newPaperId = BaseUtils.getUUID();
            // 查询创建考试的信息
            String examMajor = paperMapper.getExamMajorById(id);
            String examName = paperMapper.getExamNameById(id);
            // 新增试卷
            ExamPaper examPaper = new ExamPaper();
            examPaper.setId(newPaperId);
            examPaper.setClazzId(id);
            examPaper.setUserId(securityUtil.getCurrUser().getId());
            examPaper.setType(lastPaper.getType());
            examPaper.setClazzName(examName);
            examPaper.setMajorId(examMajor);
            if (!this.save(examPaper)) {
                throw new LegionException("保存失败");
            }

            m = generateTestQuestions(newPaperId, rules, id, "2", bankId);
            m.put("paperId", newPaperId);
        }
        return m;
    }

    @Override
    public Map submitPaper(SubmitPaper data) {
        Map map = new HashMap<>();
        // 校验答案是否为空
        if (null == data.getAnswers() || "".equals(data.getAnswers())) {
            // 未做题
            paperMapper.updatePaperSubmit(data.getPaperId(), 0.0, 0, "", BaseUtils.getDateNowSecond(), null);
            // 根据试卷id 查询一共多少试题
            Integer questionCount = questionMapper.getQuestionCountByPaperId(data.getPaperId());
            map.put("right", 0);
            map.put("not", questionCount);
            map.put("grade", 0);
            map.put("issue", 0);
        } else {
            // 查询规则
            String clazzId = paperRulesMapper.getClazzIdByPaperId(data.getPaperId());
            String rules = paperRulesMapper.getRulesByClazzId(clazzId);
            if (null == rules || "".equals(rules)) {
                String id = paperRulesMapper.getExamClazzId(clazzId);
                rules = paperRulesMapper.getRulesByClazzId(id);
            }
            // 解析规则
            Map ruleMap = paperRulesService.parsingRules(rules);
            // 有答案
            JSONArray jsonArray = JSONUtil.parseArray(data.getAnswers());
            JSONArray historyQuestion = new JSONArray();
            Double grade = new Double(0);
            Integer right = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                // 去除括号
                String[] answers = jsonArray.get(i).toString().replace("{", "").replace("}", "").split(":");
                // 获取答案和试题id
                String questionId = answers[0];
                String questionAnswers = answers[1];
                // 查询试题详情
                ExamQuestion question = questionMapper.getQuestion(questionId.replace("\"", ""));
                question.setIsItRightOrNot("0");
                String trueAnswer = question.getAnswers().replace("[", "").replace("]", "").replace("\"", "");
                // 判断试题类型
                if (String.valueOf(Constants.CHECKBOX).equals(question.getType())) {
                    // 多选题
                    int a = 0;
                    String[] trueAnswers = trueAnswer.split(",");
                    String[] questionAnswerss = questionAnswers.replace("\"", "").split(",");
                    if (trueAnswers.length == questionAnswerss.length) {
                        for (int n = 0; n < trueAnswers.length; n++) {
                            for (int m = 0; m < questionAnswerss.length; m++) {
                                if (trueAnswers[n].equals(questionAnswerss[m])) {
                                    a++;
                                }
                            }
                        }
                    }
                    if (a == trueAnswers.length) {
                        // 做对了，加分
                        grade = grade + Double.valueOf(ruleMap.get("checkboxGrade").toString());
                        question.setIsItRightOrNot("1");
                        right++;
                    }
                } else {
                    // 单选题，判断题
                    if (questionAnswers.replace("\"", "").equals(trueAnswer)) {
                        // 根据类型，做对了，加分
                        if (String.valueOf(Constants.RADIO).equals(question.getType())) {
                            // 单选题
                            grade = grade + Double.valueOf(ruleMap.get("radioGrade").toString());
                            question.setIsItRightOrNot("1");
                            right++;
                        } else {
                            // 判断题
                            grade = grade + Double.valueOf(ruleMap.get("judgmentGrade").toString());
                            question.setIsItRightOrNot("1");
                            right++;
                        }
                    }
                }
                // 构造历史数据
                question.setYourAnswers(questionAnswers.replace("\"", ""));
                historyQuestion.add(JSONUtil.parse(question));
            }
            Integer issue = grade >= Double.valueOf(ruleMap.get("gradePass").toString()) ? 1 : 0;

            map.put("right", right);
            map.put("not", (jsonArray.size() - right));
            map.put("grade", grade);
            map.put("issue", issue);
            // 判断是否考试
            String type = paperMapper.getTypeById(data.getPaperId());
            String certificate = "";
            // 记录考试不及格的次数
            if (String.valueOf(Constants.EXAMINATION).equals(type) && issue == 0) {
                // 不及格
                // 根据课程id  clazzId 查询不及格次数
                Integer noPassCount = paperMapper.getNoPassCountByClazzIdUserId(clazzId, securityUtil.getCurrUser().getId());
                noPassCount++;
                // 修改不及格次数
                paperMapper.updateNoPassCountByClazzIdUserId(clazzId, securityUtil.getCurrUser().getId(), noPassCount);
                // 判断是否3次
                if (noPassCount == 3) {
                    // todo 不及格调用方法
                    learningRecordService.ClearingLearningRecord(clazzId, String.valueOf(securityUtil.getCurrUser().getId()));
                    paperMapper.updateNoPassCountByClazzIdUserId(clazzId, securityUtil.getCurrUser().getId(), 0);
                }
            }
            if (String.valueOf(Constants.EXAMINATION).equals(type) && issue == 1) {
                // 生成证书编号
                // 获取最大证书编号
                String maxCertificate = paperMapper.getMaxCertificate();
                if (null == maxCertificate || "".equals(maxCertificate)) {
                    maxCertificate = "1";
                }
                certificate = BaseUtils.getCertificate(String.valueOf(Integer.valueOf(maxCertificate)), Constants.CERTIFICATELENGTH);
            } else if (String.valueOf(Constants.EXAM).equals(type) && issue == 1) {
                String maxCertificate = paperMapper.getMaxCertificate();
                if (null == maxCertificate || "".equals(maxCertificate)) {
                    maxCertificate = "1";
                }
                certificate = BaseUtils.getCertificate(String.valueOf(Integer.valueOf(maxCertificate) + 1), Constants.CERTIFICATELENGTH);
            }
            // 修改试卷主表
            paperMapper.updatePaperSubmit(data.getPaperId(), grade, issue, historyQuestion.toString(), BaseUtils.getDateNowSecond(), certificate);
        }
        // 查询学员信息
        map.put("username", securityUtil.getCurrUser().getNickname());
        /**
         *  查询课程名称
         */
        // 先查询课程
        String clazzName = paperMapper.getClazzNameByPaperId(data.getPaperId());
        // 如果为null或者为 ""，则查询创建的试卷
        if (null == clazzName || "".equals(clazzName)) {
            clazzName = paperMapper.getCreatePaperNameByPaperId(data.getPaperId());
        }
        map.put("clazzName", clazzName);
        // 所属单位
        //map.put("company", sysUnitMapper.getUnitNameByUserId(securityUtil.getCurrUser().getId()));
        map.put("company", "这边改改 先删了");
        return map;
    }

    @Override
    public Map viewResolution(String id) {
        Map m = new HashMap<>();
        // 根据id 查询历史记录
        String histroy = paperMapper.getHistroyById(id);
        if (null == histroy || "".equals(histroy)) {
            m.put("data", "");
            return m;
        }
        // 构建试卷 单选题
        List<ExamQuestion> radioList = new ArrayList<>();
        // 多选题
        List<ExamQuestion> checkList = new ArrayList<>();
        // 判断题
        List<ExamQuestion> judgmentList = new ArrayList<>();
        // 解析历史记录
        JSONArray jsonArray = JSONUtil.parseArray(histroy);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONUtil.parseObj(jsonArray.get(i).toString());
            ExamQuestion examQuestion = JSONUtil.toBean(jsonObject, ExamQuestion.class);
            if (null != examQuestion && examQuestion.getType() != null) {
                if (String.valueOf(Constants.RADIO).equals(examQuestion.getType())) {
                    radioList.add(examQuestion);
                } else if (String.valueOf(Constants.CHECKBOX).equals(examQuestion.getType())) {
                    checkList.add(examQuestion);
                } else if (String.valueOf(Constants.JUDGMENT).equals(examQuestion.getType())) {
                    judgmentList.add(examQuestion);
                }
            }
        }
        // 查询规则
        String clazzId = paperRulesMapper.getClazzIdByPaperId(id);
        String rules = paperRulesMapper.getRulesByClazzId(clazzId);
        if (null == rules || "".equals(rules)) {
            String ids = paperRulesMapper.getExamClazzId(clazzId);
            rules = paperRulesMapper.getRulesByClazzId(ids);
        }
        // 解析规则
        Map ruleMap = paperRulesService.parsingRules(rules);
        // 分数+及格/未及格
        String message = paperMapper.getPaperMessage(id);
        // 查询试卷名称
        m.put("paperName", paperMapper.getPaperName(id));
        m.put("radio", radioList);
        m.put("check", checkList);
        m.put("judgment", judgmentList);
        m.put("ruleMap", ruleMap);
        m.put("grade", message.split("::")[0]);
        m.put("issue", message.split("::")[1]);
        return m;
    }

    @Override
    public String selectPaperIdByClazzId(String id) {
        return paperMapper.selectPaperIdByClazzId(id);
    }

    @Override
    public Map getPaperById(String id) {
        Map m = new HashMap<>();
        m.put("data", paperMapper.getPaperById(id));
        return m;
    }

    @Override
    public void deletePaperById(String id) {
        paperMapper.deletePaperById(id);
    }

    @Override
    public Integer getIsDeleteByExamId(String id) {
        return paperMapper.getIsDeleteByExamId(id);
    }

    @Override
    public Integer getIsDeleteByClazzId(String clazzId) {
        return paperMapper.getIsDeleteByClazzId(clazzId);
    }
}
