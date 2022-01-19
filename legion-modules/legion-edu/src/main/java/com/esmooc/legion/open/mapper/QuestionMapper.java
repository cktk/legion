package com.esmooc.legion.open.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.ExamQuestion;
import com.esmooc.legion.open.entity.ExamQuestionBank;
import com.esmooc.legion.open.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface QuestionMapper {
    IPage<ExamQuestion> questionListByClazzId(ExamQuestion examQuestion, Page page);

    void deleteQuestionById(String id);

    void saveQuestion(ExamQuestion examQuestion);

    List<ExamQuestion> getQuestionById(@Param("id") List<String> id);

    ExamQuestion getQuestion(String id);

    void putQuestion(ExamQuestion examQuestion);

    void createQuestionBank(ExamQuestionBank examQuestionBank);

    IPage<ExamQuestionBank> selectQuestionBank(ExamQuestionBank examQuestionBank, Page page);

    void deleteQuestionBank(@Param("ids") String[] ids);

    void deleteQuestionByBankId(@Param("ids") String[] ids);

    List<SysDictData> selectSysDictData();

    String countQuestionByClazzId(@Param("clazzId") String clazzId,
                                  @Param("title") String title,
                                  @Param("type") String type);

    String countQuestionByBankId(@Param("bankId") String bankId,
                                 @Param("title") String title,
                                 @Param("type") String type);

    Integer getQuestionCountByPaperId(String paperId);

    ExamQuestionBank getBankById(String id);

    Integer selectQuestionCount(@Param("id") String clazzId,
                                @Param("type") Integer type);

    Integer getPaperCountByUserId(String id);

    Integer selectQuestionCountByMajorId(@Param("majorIds") String[] majorIds,
                                         @Param("type") Integer type);

    String getMajorNameByMajorId(String id);

    void updateBankById(@Param("id") String id,
                        @Param("date") Date dateNowSecond);

    String getBankMajorById(String bankId);

    String getClazzMajorById(String clazzId);

    void updateQuestion(ExamQuestion examQuestion);

    void updateCreateTimeByBankId(@Param("bankId") String bankId,
                                  @Param("time") Date time);

    void updateCreateTimeByClazzId(@Param("clazzId") String clazzId,
                                   @Param("time") Date time);

    String getBankIdByClazzId(String clazzId);

    String getClazzIdByQuestionId(String id);

    String getBankIdByQuestionId(String id);
}
