package com.esmooc.legion.your.service.impl;


import com.esmooc.legion.your.mapper.CountMapper;
import com.esmooc.legion.your.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esmooc.legion.your.entity.count.*;
import java.util.List;


@Service
public class CountServiceImpl
        implements CountService {
    @Autowired
    CountMapper countMapper;

    public TCTCount_Model TCT_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.TCT_count(startTime, endTime, areaCode, areaCode);
    }


    public HistCount_Model Hist_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.Hist_count(startTime, endTime, areaCode, areaCode);
    }


    public Integer Col_Total(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.Col_Total(startTime, endTime, areaCode, areaCode);
    }


    public List<MubaJibie> muba_jibie(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.muba_jibie(startTime, endTime, areaCode, areaCode);
    }


    public Integer muba_total(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.muba_total(startTime, endTime, areaCode, areaCode);
    }


    public List<BreJibie> bre_jibie(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.bre_jibie(startTime, endTime, areaCode, areaCode);
    }


    public Integer bre_total_r(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.bre_total_r(startTime, endTime, areaCode, areaCode);
    }


    public Integer tct_total(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.tct_total(startTime, endTime, areaCode, areaCode);
    }


    public Integer bre_total_l(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.bre_total_l(startTime, endTime, areaCode, areaCode);
    }


    public PalCount_Model Pal_Total(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.Pal_Total(startTime, endTime, areaCode, areaCode);
    }


    public Integer all_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.all_count(startTime, endTime, areaCode, townCode);
    }


    public Integer bre_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.bre_count(startTime, endTime, areaCode, townCode);
    }


    public Integer bre_r_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.bre_r_count(startTime, endTime, areaCode, townCode);
    }


    public Integer gyna_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.gyna_count(startTime, endTime, areaCode, townCode);
    }


    public GynaCount_Model Gyna_Total(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.Gyna_Total(startTime, endTime, areaCode, areaCode);
    }

    public Integer Gyna_Count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.Gyna_Count(startTime, endTime, areaCode, townCode);
    }


    public Integer both_count(String startTime, String endTime, String areaCode, String townCode) {
        return this.countMapper.both_count(startTime, endTime, areaCode, townCode);
    }
}
