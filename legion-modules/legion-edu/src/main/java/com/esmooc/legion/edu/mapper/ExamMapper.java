package com.esmooc.legion.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import com.esmooc.legion.edu.entity.Exam;
import com.esmooc.legion.edu.entity.vo.ExamVO;import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.Date;import java.util.List;

/**
 * @Author 呆猫
 * @Date: 2022/01/26/ 21:01
 * @Description:
 */
@Mapper
public interface ExamMapper extends BaseMapper<Exam> {
    IPage<ExamVO> examList(@Param("exam") ExamVO examVO, @Param("page") Page page);



    void deleteBizExamRulesByExamId(String id);

    ExamVO getExamById(String id);

    void updateBizExamIssue(@Param("id") String id,
                            @Param("issue") String issue);

    void deleteBizExam(String id);

    void deleteBizExamGradeIsNull(String id);

    List<String> selectUserIdsByRoleIds(@Param("ids") Long[] roleIds);

    void insertExamPaper(@Param("id") String uuid,
                         @Param("clazzId") String id,
                         @Param("userId") String userId,
                         @Param("time") Date dateNowSecond,
                         @Param("type") Integer type,
                         @Param("examMajor") String examMajor,
                         @Param("exanName") String examName);

    Integer getExamCountById(String id);

    void updateBizCourse(@Param("id") String id,
                         @Param("issue") String issue);

    Integer getExamCountByIdUserId(@Param("userId") String userId,
                                   @Param("id") String id);

    String selectSumGradeById(@Param("id") String id);

    Integer getUserGrade(String id);

    Integer getQuestionCount(String id);

    Integer getBankIdCount(String backId);

    void deleteBizExamRulesByBankId(String id);

    String getRules(String id);

    String getClazzId(String id);

    String getClazzIds(String id);
}
