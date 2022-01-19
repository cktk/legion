package com.esmooc.legion.open.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.open.entity.MyExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyExamMapper {
    IPage<MyExam> list(@Param("exam") MyExam exam, @Param("page") Page page);

    Integer getUserPassExamCount(String userId);

    List<MyExam> getLaseTwoTimes(@Param("userId")String userId, @Param("passExamCount") Integer passExamCount);

    Integer isClazz(String clazzId);

    String getMajorNameByBankId(String clazzId);

    String getMajorIdByClazzId(String clazzId);

    String getGradeByUserIdClazzId(@Param("clazzId") String clazzId,
                                   @Param("userId") String userId);
}
