package com.esmooc.legion.open.service;

import com.esmooc.legion.core.entity.User;
import com.esmooc.legion.open.entity.SubmitPaper;

import java.util.Map;

public interface PaperService {
    Map getPaper(String clazzId, String type, User user);

    Map startPracticing(String clazzId, String type, String userId);

    Map satartExam(String id);

    Map submitPaper(SubmitPaper data);

    Map viewResolution(String id);

    String selectPaperIdByClazzId(String id);

    Map getPaperById(String id);

    void deletePaperById(String id);

    Integer getIsDeleteByExamId(String id);

    Integer getIsDeleteByClazzId(String clazzId);
}
