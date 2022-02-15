package com.esmooc.legion.your.service;

import com.esmooc.legion.your.entity.count.*;

import java.util.List;


public interface CountService {
    TCTCount_Model TCT_count(String startTime, String endTime, String areaCode, String townCode);

    HistCount_Model Hist_count(String startTime, String endTime, String areaCode, String townCode);

    Integer Col_Total(String startTime, String endTim, String areaCode, String townCode);

    List<MubaJibie> muba_jibie(String startTime, String endTime, String areaCode, String townCode);

    Integer muba_total(String startTime, String endTime, String areaCode, String townCode);

    List<BreJibie> bre_jibie(String startTime, String endTime, String areaCode, String townCode);

    Integer bre_total_r(String startTime, String endTime, String areaCode, String townCode);

    Integer bre_total_l(String startTime, String endTime, String areaCode, String townCode);

    Integer tct_total(String startTime, String endTime, String areaCode, String townCode);

    PalCount_Model Pal_Total(String startTime, String endTime, String areaCode, String townCode);

    Integer all_count(String startTime, String endTime, String areaCode, String townCode);

    Integer bre_count(String startTime, String endTime, String areaCode, String townCode);

    Integer bre_r_count(String startTime, String endTime, String areaCode, String townCode);

    Integer gyna_count(String startTime, String endTime, String areaCode, String townCode);

    GynaCount_Model Gyna_Total(String startTime, String endTime, String areaCode, String townCode);

    Integer Gyna_Count(String startTime, String endTime, String areaCode, String townCode);


    Integer both_count(String startTime, String endTime, String areaCode, String townCode);
}
