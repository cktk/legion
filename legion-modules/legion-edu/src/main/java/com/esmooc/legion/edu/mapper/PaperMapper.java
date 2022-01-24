package com.esmooc.legion.edu.mapper;

import com.esmooc.legion.edu.entity.ExamPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PaperMapper {

    ExamPaper getLastPaperByClazzIdUserId(@Param("clazzId") String clazzId,
                                          @Param("userId") String userId,
                                          @Param("type") String type);

    List<String> getRandomQuestionIdByClazzId(@Param("clazzId") String clazzId,
                                              @Param("type") Integer type,
                                              @Param("count") String count,
                                              @Param("bankId") String bankId);

    void savePaper(@Param("id") String paperId,
                   @Param("clazzId") String clazzId,
                   @Param("userId") String userId,
                   @Param("date") Date dateNowSecond,
                   @Param("type") String type,
                   @Param("clazzMajor") String clazzMajor,
                   @Param("clazzName") String clazzName);

    void savePaperQuestion(@Param("id") String uuid,
                           @Param("paperId") String paperId,
                           @Param("data") String data);

    ExamPaper selectLastPaperId(@Param("id") String id,
                                @Param("userId") String userId);

    Integer selectCountPaperQuestion(String lastPaperId);

    void updatePaperSubmit(@Param("paperId") String paperId,
                           @Param("grade") Double grade,
                           @Param("issue") Integer issue,
                           @Param("histroy") String toArray,
                           @Param("time") Date dateNowSecond,
                           @Param("certificate") String certificate);

    String getHistroyById(String id);

    String selectPaperIdByClazzId(String id);

    String getPaperMessage(String id);

    String getClazzNameByPaperId(String paperId);

    String getCreatePaperNameByPaperId(String paperId);

    String getMaxCertificate();

    String getTypeById(String paperId);

    ExamPaper getPaperById(String id);

    void batchSavePaperQuestion(@Param("id") String uuid,
                                @Param("paperId") String paperId,
                                @Param("ids") List<String> ids);

    List<String> getPaperMajorIdsById(String clazzId);

    List<String> getRandomQuestionIdByMajorIds(@Param("majorIds") List<String> majorIds,
                                               @Param("type") Integer type,
                                               @Param("count") String count,
                                               @Param("bankId") String bankId);

    String getExamMajorById(String id);

    String getExamNameById(String id);

    void deleteExamBankByClazzId(String id);

    void deleteQuestionByClazzId(String id);

    String getPaperName(String id);

    Integer getNoPassCountByClazzIdUserId(@Param("clazzId") String clazzId,
                                          @Param("userId") String userId);

    void updateNoPassCountByClazzIdUserId(@Param("clazzId") String clazzId,
                                          @Param("userId") String userId,
                                          @Param("noPassCount") Integer noPassCount);

    void deletePaperById(String id);

    void updateBankMajor(@Param("clazzId") String id,
                         @Param("major") String subject);

    Integer getStudyTypeByClazzIdUserId(@Param("clazzId") String clazzId,
                                        @Param("userId") String userId);

    Integer getIsDeleteByExamId(String id);

    Integer getIsDeleteByClazzId(String clazzId);

    Integer getQuestionCountByClazzId(@Param("clazzId") String clazzId,
                                      @Param("type") Integer type,
                                      @Param("bankId") String bankId);

    Integer getQuestionCountByMajorIds(@Param("majorIds") List<String> majorIds,
                                       @Param("type") Integer type,
                                       @Param("bankId") String bankId);

    String getBankIdByClazzId(String clazzId);
}
