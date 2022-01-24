package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.edu.entity.CourseFile;
import com.esmooc.legion.edu.entity.MyExam;
import com.esmooc.legion.edu.mapper.CourseFileMapper;
import com.esmooc.legion.edu.mapper.MyExamMapper;
import com.esmooc.legion.edu.service.MyExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MyExamServiceImpl
 * @Author Administrator
 * @Date 2021-1-12 13:49
 **/
@Service
public class MyExamServiceImpl extends ServiceImpl<MyExamMapper, MyExam> implements MyExamService {

    @Autowired
    private MyExamMapper myExamMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public IPage<MyExam> list(MyExam exam, Page page) {
        exam.setUserId(securityUtil.getCurrUser().getId());
        IPage<MyExam> list = myExamMapper.list(exam, page);
        for (MyExam d : list.getRecords()) {
            // 查询分数
            String grade = myExamMapper.getGradeByUserIdClazzId(d.getClazzId(), securityUtil.getCurrUser().getId());
            if (null == grade || "".equals(grade)) {
                d.setGrade("0");
                d.setIsPass(0);
            }
            if (null != d.getPeriod() && !"".equals(d.getPeriod())) {
                double f = Double.valueOf(d.getPeriod());
                BigDecimal b = new BigDecimal(f);
                d.setPeriod(String.valueOf(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
            }
        }
        return list;
    }

    @Override
    public Map homeMyExam() {
        Map m = new HashMap<>();
        // 查询通过考试的次数
        Integer passExamCount = myExamMapper.getUserPassExamCount(securityUtil.getCurrUser().getId());
        List<MyExam> data = myExamMapper.getLaseTwoTimes(securityUtil.getCurrUser().getId(),passExamCount);
        m.put("passCount", passExamCount);
        m.put("data", data);
        return m;
    }
}
