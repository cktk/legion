package com.esmooc.legion.your.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;


import com.esmooc.legion.your.entity.count.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("screening2")
public interface CountMapper {
    TCTCount_Model TCT_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    HistCount_Model Hist_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer Col_Total(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    List<MubaJibie> muba_jibie(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer muba_total(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    List<BreJibie> bre_jibie(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer bre_total_r(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer tct_total(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer bre_total_l(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    PalCount_Model Pal_Total(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer all_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer bre_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer bre_r_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer gyna_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    GynaCount_Model Gyna_Total(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer both_count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);

    Integer Gyna_Count(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("areaCode") String areaCode, @Param("townCode") String townCode);
}
