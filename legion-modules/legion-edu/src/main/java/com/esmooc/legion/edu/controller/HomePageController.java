package com.esmooc.legion.edu.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.vo.RoleDTO;
import com.esmooc.legion.edu.common.constant.Constants;
import com.esmooc.legion.edu.entity.vo.ExamVO;
import com.esmooc.legion.edu.entity.vo.HomePageVO;
import com.esmooc.legion.edu.service.CourseService;
import com.esmooc.legion.edu.service.LearningRecordFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商务主页控制器
 *
 * @author Daimao
 * @date 2022年01月19日 11点01分26秒
 */
@DS("edu")
@Api(tags = "首页接口")
@RestController
@RequestMapping("/edu/homePage")
public class HomePageController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LearningRecordFileService learningRecordFileService;

    @Autowired
    private SecurityUtil securityUtil;


    @ApiOperation(value = "管理端首页接口")
    @GetMapping("/admin")
    public Object adminHomePage(ExamVO examVO) {
        Map ajax = new HashMap<String, Object>();
        //视频 内部
        ajax.put("internalVideoStatistics", courseService.selectHomeStatistics(Constants.INTERNAL.intValue(), Constants.INTERNALSTUDENTS + "", Constants.VIDEOCOURSE));
        //视频 外部
        ajax.put("internalPdfStatistics", courseService.selectHomeStatistics(Constants.EXTERNAL.intValue(), Constants.EXTERNALSTUDENTS + "", Constants.VIDEOCOURSE));
        //公开 内部
        ajax.put("externalVideoStatistics", courseService.selectHomeStatistics(Constants.INTERNAL.intValue(), Constants.INTERNALSTUDENTS + "", Constants.SOCIETYCOURSE));
        //公开 外部
        ajax.put("externalPdfStatistics", courseService.selectHomeStatistics(Constants.EXTERNAL.intValue(), Constants.EXTERNALSTUDENTS + "", Constants.SOCIETYCOURSE));
        //公开 内部
        ajax.put("videoSize", courseService.selectcourseSize(Constants.VIDEOCOURSE));
        //公开 外部
        ajax.put("pdfSize", courseService.selectcourseSize(Constants.SOCIETYCOURSE));
        //公开 内部
        ajax.put("video", courseService.selectcourse(Constants.VIDEOCOURSE));
        //公开 外部
        ajax.put("pdf", courseService.selectcourse(Constants.SOCIETYCOURSE));
        //内部人员
        //sysUserService.selectUserSzie(Constants.INTERNAL)
        ajax.put("internalSize", "内部人员需要处理");
        //外部人员  sysUserService.selectUserSzie(Constants.EXTERNAL)
        ajax.put("externalSize", "外部人员");

        List<Integer> videoNumber = new ArrayList<>();
        List<Integer> openNumber = new ArrayList<>();
        List<String> areaName = new ArrayList<>();
        for (RoleDTO role :
                securityUtil.getCurrUser().getRoles()) {
            if (Constants.CITY.equals(role.getId())) {
                //videoNumber = sysAreaService.selectCityNumber(Constants.FLAGCOUNTY, Constants.VIDEOCOURSE, user.getAu());
                //openNumber = sysAreaService.selectCityNumber(Constants.FLAGCOUNTY, Constants.SOCIETYCOURSE, user.getAu());
                //areaName = sysAreaService.selectAreaName(Constants.FLAGCOUNTY, user.getAu());
                break;
            } else {
                //videoNumber = sysAreaService.selectNumber(Constants.AU, Constants.VIDEOCOURSE, user.getProvince());
                //openNumber = sysAreaService.selectNumber(Constants.AU, Constants.SOCIETYCOURSE, user.getProvince());
                //areaName = sysAreaService.selectAreaName(Constants.AU, user.getProvince());
            }
        }
        ajax.put("videoNumber", videoNumber);
        ajax.put("openNumber", openNumber);
        ajax.put("areaName", areaName);

        ajax.put("videoUnitRanking", learningRecordFileService.selectVideoUnitRanking());
        ajax.put("openUnitRanking", learningRecordFileService.selectOpenUnitRanking());

        return ajax;
    }

    @ApiOperation(value = "公共")
    @GetMapping("/common")
    public Object commonHomePage(ExamVO examVO) {
        String videoTime = "", pdfTime = "";
        List<HomePageVO> video = new ArrayList<>();
        List<HomePageVO> pdf = new ArrayList<>();
        Map ajax = new HashMap<String, Object>();
      /*  for (SysRole role :
                user.getRoles()) {
            if (role.getRoleId().longValue() == Constants.INTERNAL.longValue()) {
                //视频 内部
                videoTime = courseService.selectcourseVideoPeriod(Constants.INTERNALSTUDENTS + "", user.getUserId());
                pdfTime = courseService.selectcoursePdfPeriod(Constants.INTERNALSTUDENTS + "", user.getUserId());
                video = courseService.selectcourse1(Constants.VIDEOCOURSE, Constants.INTERNALSTUDENTS + "");
                pdf = courseService.selectcourse1(Constants.SOCIETYCOURSE, Constants.INTERNALSTUDENTS + "");
                break;
            } else if (role.getRoleId().longValue() == Constants.EXTERNAL.longValue()) {
                //视频 外部
                videoTime = courseService.selectcourseVideoPeriod(Constants.EXTERNALSTUDENTS + "", user.getUserId());
                pdfTime = courseService.selectcoursePdfPeriod(Constants.EXTERNALSTUDENTS + "", user.getUserId());
                video = courseService.selectcourse1(Constants.VIDEOCOURSE, Constants.EXTERNALSTUDENTS + "");
                pdf = courseService.selectcourse1(Constants.SOCIETYCOURSE, Constants.EXTERNALSTUDENTS + "");
                break;
            }
        }*/
        ajax.put("videoTime", videoTime);
        ajax.put("pdfTime", pdfTime);
        //公开 内部
        ajax.put("video", video);
        //公开 外部
        ajax.put("pdf", pdf);

        ajax.put("videoStudentRanking", learningRecordFileService.selectVideoStudentRanking());
        ajax.put("openStudentRanking", learningRecordFileService.selectOpenStudentRanking());
        return ajax;
    }

}
