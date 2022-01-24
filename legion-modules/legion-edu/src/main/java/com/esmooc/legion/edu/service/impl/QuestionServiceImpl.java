package com.esmooc.legion.edu.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.BaseUtils;
import com.esmooc.legion.edu.entity.ExamQuestion;
import com.esmooc.legion.edu.entity.ExamQuestionBank;
import com.esmooc.legion.edu.entity.SysDictData;
import com.esmooc.legion.edu.mapper.ExamQuestionMapper;
import com.esmooc.legion.edu.mapper.QuestionMapper;
import com.esmooc.legion.edu.service.ExamQuestionService;
import com.esmooc.legion.edu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QuestionServiceImpl
 * @Author Administrator
 * @Date 2020-12-29 16:55
 **/
@Service
public class QuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion>  implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public IPage<ExamQuestion> questionListByClazzId(ExamQuestion examQuestion, Page page) {
        IPage<ExamQuestion> list = questionMapper.questionListByClazzId(examQuestion, page);
        for (ExamQuestion data : list.getRecords()) {
            if (null != data.getAnswers() && !"".equals(data.getAnswers())) {
                // 转移json
                JSONArray array = JSONUtil.parseArray(data.getAnswers());
                // 拼接字符串
                String answers = "";
                for (int i = 0; i < array.size(); i++) {
                    answers = answers + array.get(i) + ",";
                }
                data.setAnswers(answers.substring(0, answers.length() - 1));
            }
        }
        return list;
    }

    @Override
    public void deleteQuestionById(String id) {
        String[] ids = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            // 根据id 查询题库id
            String clazzId = questionMapper.getClazzIdByQuestionId(id);
            String bankId = "";

            if (null == clazzId || "".equals(clazzId)) {
                // bankId
                bankId = questionMapper.getBankIdByQuestionId(id);
            } else {
                // 根据clazzId查询
                bankId = questionMapper.getBankIdByClazzId(clazzId);
            }
            questionMapper.updateBankById(bankId, BaseUtils.getDateNowSecond());
            questionMapper.deleteQuestionById(ids[i]);
        }
    }

    @Override
    public void saveQuestion(ExamQuestion examQuestion) {
        // 添加创建人、创建时间
        examQuestion.setCreateTime(BaseUtils.getDateNowSecond());
        examQuestion.setCreateUser(String.valueOf(securityUtil.getCurrUser().getId()));
        String bankId = "";
        if (null == examQuestion.getClazzId() || "".equals(examQuestion.getClazzId())) {
            // bankId
            bankId = examQuestion.getBankId();
        } else {
            // clazzId
            bankId = questionMapper.getBankIdByClazzId(examQuestion.getClazzId());
        }
        // 修改更新时间
        questionMapper.updateBankById(bankId, BaseUtils.getDateNowSecond());


        this.saveOrUpdate(examQuestion);

    }

    @Override
    public ExamQuestion getQuestionById(String id) {
        ExamQuestion examQuestion = questionMapper.getQuestion(id);
        examQuestion.setAnswers(examQuestion.getAnswers().replace(";", ","));
        // 查询类别
        if (null != examQuestion.getClazzId() && !"".equals(examQuestion.getClazzId())) {
            // 查询视频课程类别
            String clazzMajor = questionMapper.getClazzMajorById(examQuestion.getClazzId());
            if (!clazzMajor.contains(examQuestion.getMajorId())) {
                examQuestion.setMajorId("");
            }
        }
        return examQuestion;
    }

    @Override
    public void putQuestion(ExamQuestion examQuestion) {
        examQuestion.setCreateTime(BaseUtils.getDateNowSecond());
        String bankId = "";
        if (null == examQuestion.getClazzId() || "".equals(examQuestion.getClazzId())) {
            // bankId
            bankId = examQuestion.getBankId();
        } else {
            // clazzId
            bankId = questionMapper.getBankIdByClazzId(examQuestion.getClazzId());
        }
        // 修改更新时间
        questionMapper.updateBankById(bankId, BaseUtils.getDateNowSecond());
        questionMapper.putQuestion(examQuestion);
    }

    /**
     * 创建题库
     *
     * @param examQuestionBank
     * @return
     */
    @Override
    public ExamQuestionBank createQuestionBank(ExamQuestionBank examQuestionBank) {
        String id = BaseUtils.getUUID();
        examQuestionBank.setId(id);
        examQuestionBank.setCreateBy(securityUtil.getCurrUser().getId());
        examQuestionBank.setCreateTime(BaseUtils.getDateNowSecond());
        questionMapper.createQuestionBank(examQuestionBank);
        return examQuestionBank;
    }

    @Override
    public IPage<ExamQuestionBank> selectQuestionBank(ExamQuestionBank examQuestionBank, Page page) {
        IPage<ExamQuestionBank> list = questionMapper.selectQuestionBank(examQuestionBank, page);
        for (ExamQuestionBank data : list.getRecords()) {
            // 根据类别id查询类别名称
            String unitName = "";
            String[] unitId = data.getMajorId().split(",");
            for (int i = 0; i < unitId.length; i++) {
                unitName = unitName + "," + questionMapper.getMajorNameByMajorId(unitId[i]);
            }
            data.setMajorName(unitName.substring(1, unitName.length()));
        }
        return list;
    }

    @Override
    public void deleteQuestionBank(String id) {
        // 解析id
        String[] ids = id.split(",");
        // 删除题库主表
        questionMapper.deleteQuestionBank(ids);
        // 删除题目表
        questionMapper.deleteQuestionByBankId(ids);
    }

    @Override
    public String importUser(List<ExamQuestion> questions, String bankId, String clazzId) {
        if (null == questions || questions.size() == 0) {
            return "当前excel无内容！";
        }
        // 返回信息
        String message = "";
        // 查询类别
        List<SysDictData> dictData = questionMapper.selectSysDictData();
        // 根据题库id查询类别
        String major = "";
        if (null == clazzId || "".equals(clazzId)) {
            major = questionMapper.getBankMajorById(bankId);
            // 根据bankId 修改时间
            questionMapper.updateCreateTimeByBankId(bankId, BaseUtils.getDateNowSecond());
        } else {
            major = questionMapper.getClazzMajorById(clazzId);
            // 根据clazzId 修改时间
            questionMapper.updateCreateTimeByClazzId(clazzId, BaseUtils.getDateNowSecond());
        }
        String[] majors = major.split(",");
        // 解析试题
        for (ExamQuestion data : questions) {
            /**
             *  校验 Analysis
             */
            if (null == data) {
                continue;
            } else if (null == data.getTitle() || "".equals(data.getTitle())) {
                message = message + "序号为" + data.getId() + "：题目为空。 ";
                continue;
            } else if (null == data.getOptions() || "".equals(data.getOptions())) {
                message = message + "序号为" + data.getId() + "：选项为空。 ";
                continue;
            } else if (null == data.getAnswers() || "".equals(data.getAnswers())) {
                message = message + "序号为" + data.getId() + "：答案为空。 ";
                continue;
            } else if (null == data.getMajorId() || "".equals(data.getMajorId())) {
                message = message + "序号为" + data.getId() + "：类别为空。 ";
                continue;
            } else if (!(data.getOptions().contains(";") && data.getOptions().contains(":"))) {
                message = message + "序号为" + data.getId() + "：选项类型不正确。 ";
                break;
            } else if (null == data.getAnalysis() || "".equals(data.getAnalysis())) {
                message = message + "序号为" + data.getId() + "：解析为空。 ";
                continue;
            }
            // 解析选项
            String[] semicolons = data.getOptions().split(";");
            JSONArray jsonArray = new JSONArray();
            int optionCount = 0;
            for (int i = 0; i < semicolons.length; i++) {
                Map m = new HashMap<>();
                String[] colon = semicolons[i].split(":");
                if (colon.length != 2) {
                    message = message + "序号为" + data.getId() + "：选项内容不正确。 ";
                    optionCount = 1;
                    break;
                }
                m.put(colon[0], colon[1]);
                jsonArray.add(JSONUtil.parseArray(m));
            }
            // 题目类型 type
            if ("单选题".equals(data.getType())) {
                data.setType("0");
                if (jsonArray.size() < 2) {
                    if (!message.contains("序号为" + data.getId() + "：选项内容不正确。 ")) {
                        message = message + "序号为" + data.getId() + "：选项内容不正确。 ";
                    }
                    continue;
                }
            } else if ("多选题".equals(data.getType())) {
                data.setType("1");
                if (jsonArray.size() < 2) {
                    if (!message.contains("序号为" + data.getId() + "：选项内容不正确。 ")) {
                        message = message + "序号为" + data.getId() + "：选项内容不正确。 ";
                    }
                    continue;
                }
            } else if ("判断题".equals(data.getType())) {
                data.setType("2");
                // 判断选项
                if (jsonArray.size() != 2) {
                    if (!message.contains("序号为" + data.getId() + "：选项内容不正确。 ")) {
                        message = message + "序号为" + data.getId() + "：选项内容不正确。 ";
                    }
                    continue;
                }
            } else {
                message = message + "序号为" + data.getId() + "：类型填写错误！ ";
                continue;
            }
            // 选项
            data.setOptions(String.valueOf(jsonArray));
            // 答案
            String[] answer = data.getAnswers().split(",");
            JSONArray array = new JSONArray();
            for (int i = 0; i < answer.length; i++) {
                array.add(answer[i]);
            }
            data.setAnswers(String.valueOf(array));
            // 专业id majorId
            Integer majorCount = 0;
            for (SysDictData dict : dictData) {
                if (dict.getName().equals(data.getMajorId())) {
                    data.setMajorId(dict.getValue());
                    majorCount++;
                }
            }
            if (majorCount == 0) {
                message = message + "序号为" + data.getId() + "：类别填写不正确。 ";
                continue;
            }
            Integer trueMajorCount = 0;
            // 判断是否有类别
            for (int i = 0; i < majors.length; i++) {
                if (data.getMajorId().equals(majors[i])) {
                    trueMajorCount++;
                }
            }
            if (trueMajorCount == 0) {
                message = message + "序号为" + data.getId() + "：类别填写不正确。 ";
                continue;
            }
            // id
            data.setId(BaseUtils.getUUID());
            // 课程、题库id
            data.setClazzId(clazzId);
            data.setBankId(bankId);
            // 创建时间
            data.setCreateTime(BaseUtils.getDateNowSecond());
            // 创建人
            data.setCreateBy(securityUtil.getCurrUser().getId());
            // 判断题目是否重复
            String id = "";
            if (null == bankId || "".equals(bankId)) {
                id = questionMapper.countQuestionByClazzId(clazzId, data.getTitle(), data.getType());
            } else {
                id = questionMapper.countQuestionByBankId(bankId, data.getTitle(), data.getType());
            }
            if (optionCount == 0) {
                if (null == id || "".equals(id)) {
                    // 新增
                    questionMapper.saveQuestion(data);
                } else {
                    // 更新
                    data.setId(id);
                    questionMapper.putQuestion(data);
                }
            }
        }
        if ("".equals(message)) {
            message = "操作成功，导入" + questions.size() + "条数据！ ";
        }
        return message;
    }

    @Override
    public Map bankDetails(String id) {
        Map m = new HashMap<>();
        // 查询题库信息
        ExamQuestionBank data = questionMapper.getBankById(id);
        // 修改更新时间
//        bizQuestionMapper.updateBankById(id, BaseUtils.getDateNowSecond());
        // 判断是否关联视频课程
        if (null != data) {
            String clazzId = "";
            if (null == data.getClazzId() || "".equals(data.getClazzId())) {
                // 题库
                clazzId = data.getId();
            } else {
                // 视频课程
                clazzId = data.getClazzId();
            }
            // 查询试题个数
            m.put("radioCount", questionMapper.selectQuestionCount(clazzId, Constants.RADIO));
            m.put("checkCount", questionMapper.selectQuestionCount(clazzId, Constants.CHECKBOX));
            m.put("judgmentCount", questionMapper.selectQuestionCount(clazzId, Constants.JUDGMENT));
        }
        m.put("data", data);
        return m;
    }

    @Override
    public Map paperDetails(String id) {
        Map m = new HashMap<>();
        String[] majorIds = id.split(",");
        m.put("radioCount", questionMapper.selectQuestionCountByMajorId(majorIds, Constants.RADIO));
        m.put("checkCount", questionMapper.selectQuestionCountByMajorId(majorIds, Constants.CHECKBOX));
        m.put("judgmentCount", questionMapper.selectQuestionCountByMajorId(majorIds, Constants.JUDGMENT));
        return m;
    }

}
