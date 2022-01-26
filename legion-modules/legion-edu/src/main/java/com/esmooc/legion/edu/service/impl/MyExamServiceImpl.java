package com.esmooc.legion.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.Department;
import com.esmooc.legion.core.entity.Role;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.MyExam;
import com.esmooc.legion.edu.mapper.MyExamMapper;
import com.esmooc.legion.edu.service.MyExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public IPage<MyExam> list(MyExam exam, Page pageVo) {
        exam.setUserId(securityUtil.getCurrUser().getId());
        IPage<MyExam> page = myExamMapper.list(exam, pageVo);

        try {
            List<String> deptIds = securityUtil.getCurrUserDept().stream().map(Department::getId).collect(Collectors.toList());
            List<String> RoleIds = securityUtil.getCurrUserRole().stream().map(Role::getId).collect(Collectors.toList());
            List<MyExam> deptlist = myExamMapper.listBy(deptIds, Constants.DEPT,exam.getClazzName(),exam.getMajorId());
            List<MyExam> rolelist = myExamMapper.listBy(RoleIds, Constants.ROLE,exam.getClazzName(),exam.getMajorId());
            page.getRecords().addAll(deptlist);
            page.getRecords().addAll(rolelist);
        } catch (Exception e) {

        }


        for (MyExam d : page.getRecords()) {
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
        return page;
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
