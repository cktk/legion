package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.common.utils.PdfUtil;
import com.esmooc.legion.edu.entity.ExamPaper;
import com.esmooc.legion.edu.mapper.PaperMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@DS("edu")
@Api(tags = "打印")
@Controller
@RequestMapping("/edu/print")
public class PrintController {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SecurityUtil securityUtil;


    @GetMapping("/print/{id}")
    @ResponseBody
    @ApiOperation(value = "根据ID打印")
    public String print(@PathVariable("id") String id) {
        Map<String, Object> o = new HashMap<>();
        String company = "测试好或或或或或";


        ExamPaper examPaper = paperMapper.getPaperById(id);
        String clazzName = examPaper.getClazzName();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        Date date = null;
        try {
            date = sdf.parse(examPaper.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strDateFormat = "yyyy";
        sdf = new SimpleDateFormat(strDateFormat);
        String year = sdf.format(date);
        strDateFormat = "yyyy年MM月dd日";
        sdf = new SimpleDateFormat(strDateFormat);
        String str = sdf.format(date);
        String con1 = "    于" + str + "参加在业务培训平台举办的" + clazzName + "课程的考试，完成考试并及格通过，颁发此证书。";
        o.put("title", "" + year + "年度业务培训平台考试合格证书");
        o.put("name", "学员姓名：" + securityUtil.getCurrUser().getNickname());
        o.put("company", company);
        o.put("date", str);
        o.put("con1", con1.substring(0, 36));
        if (con1 != "" && con1.length() > 36) {
            if (con1 != "" && con1.length() > 70) {
                o.put("con1", con1.substring(0, 36));
                if (con1 != "" && con1.length() > 102) {
                    o.put("con2", con1.substring(36, 70));
                    if (con1 != "" && con1.length() > 130) {
                        o.put("con3", con1.substring(70, 102));
                    } else {
                        o.put("con3", con1.substring(70, 102));
                    }
                } else {
                    o.put("con2", con1.substring(36, 70));
                    o.put("con3", con1.substring(70, con1.length()));
                }
            } else {
                o.put("con1", con1.substring(0, 36));
                o.put("con2", con1.substring(36, con1.length()));
            }
        } else {
            o.put("con1", con1.substring(0, con1.length()));
        }
        return PdfUtil.fillTemplate(o, "", Constants.FILE_PATH + "/muban.pdf");
    }

}
