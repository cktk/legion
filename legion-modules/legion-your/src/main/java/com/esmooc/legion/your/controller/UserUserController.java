//package com.esmooc.legion.your.controller;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.util.IdcardUtil;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.esmooc.legion.core.common.exception.LegionException;
//import com.esmooc.legion.core.common.utils.PageUtil;
//import com.esmooc.legion.core.common.utils.ResultUtil;
//import com.esmooc.legion.core.common.vo.PageVo;
//import com.esmooc.legion.core.common.vo.Result;
//import com.esmooc.legion.screening.entity.Deadline;
//import com.esmooc.legion.screening.entity.Info;
//import com.esmooc.legion.screening.entity.User;
//import com.esmooc.legion.screening.utils.JsonUtil;
//import com.esmooc.legion.your.common.constant.Screening;
//import com.esmooc.legion.your.common.utils.YearCompareUtils;
//import com.esmooc.legion.your.entity.UserDeadline;
//import com.esmooc.legion.your.entity.UserUser;
//import com.esmooc.legion.your.service.UserDeadlineService;
//import com.esmooc.legion.your.service.UserUserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author: zqj
// * @version: 1.0
// * @description:
// */
///**
// * @Author 呆猫
// * @Date: 2022/02/10/ 15:17
// * @Description:
// */
//@Slf4j
//@RestController
//@Api(tags = "基本信息上传")
//@Transactional
//@RequestMapping(value = "/v2/Screening")
//public class UserUserController {
//
//    @Autowired
//    private UserUserService userUserService;
//
//    @Autowired
//    private UserDeadlineService userDeadlineService;
//
//    private static final Integer YEAR = 3;
//
//    @GetMapping("/User_add")
//    @ApiOperation(value = "病人添加")
//    @Transactional
//    public Result<UserUser> test(UserUser userUser) {
//        if (IdcardUtil.getAgeByIdCard(userUser.getIdcard()) <= 34 || IdcardUtil.getAgeByIdCard(userUser.getIdcard()) >= 65) {
//            throw new LegionException("年龄不符合筛查条件");
//        }
//        UserUser oUser = userUserService.getByIdCardMax(userUser.getIdcard());
//        if (oUser == null) {
//            userUser.setCard(userUserService.getCurrentVal());
//            if (userUserService.save(userUser)) {
//                userUserService.updateCurrentVal();
//                userDeadlineService.save(new UserDeadline().setStatus(Screening.STATUS_OK).setUptime(new Date()).setUserId(userUser.getId()));
//            }
//        } else {
//            UserDeadline deadline = userDeadlineService.getByUserId(userUser.getId());
//            if (deadline == null) {
//                userDeadlineService.save(new UserDeadline().setStatus(Screening.STATUS_OK).setUptime(new Date()).setUserId(userUser.getId()));
//            } else {
//                if (!YearCompareUtils.DateCompare(deadline.getUptime(), new Date(), YEAR)) {
//                    throw new LegionException(YEAR + "年内已经检查过,无法再次进行检查");
//                }
//                userDeadlineService.updateById(deadline.setStatus(Screening.STATUS_OK));
//                userUserService.save(userUser);
//            }
//        }
//        return ResultUtil.data(userUser);
//
//
//    }
//
//
//    // 用户列表 --- 宫颈癌
//    @GetMapping("/User_list")                       //妇科检查条件            tct检查条件  条件
//    public Result<IPage<UserUser>> list(String startTime, String endTime, String diagnose, String tct, String input, PageVo pageVo) {
//
//        IPage<UserUser> userUser = null;
//        if (StrUtil.isNotEmpty(diagnose)) {
//            userUser = userUserService.getGynecologicalUser(startTime, endTime, input, diagnose, PageUtil.initMpPage(pageVo));
//        } else if (StrUtil.isNotEmpty(tct)) {
//            if (tct.equals("ASCUS")) {
//                tct = "US";
//            }
//            userUser = userUserService.getTctUser(startTime, endTime, input, tct, PageUtil.initMpPage(pageVo));
//        } else {
//            userUser = userUserService.getMuBaAndBreastUserNew(startTime, endTime, input, PageUtil.initMpPage(pageVo));
//        }
//
//        return ResultUtil.data(userUser);
//
//    }
//
//    // 用户列表 --- 乳腺癌
//    @GetMapping("/User_lists")
//    public Result<IPage<UserUser>> lists(String startTime, String endTime,
//                                         //木耙等级         //彩色等级
//                                         String mubale, String colorle,
//                                         String input , PageVo pageVo //条件
//                                          ) {
//        IPage<UserUser> muBaUserNew = null;
//        if (!"".equals(mubale)) {
//            muBaUserNew = userUserService.getMuBaUserNew(startTime, endTime, input, mubale, colorle, PageUtil.initMpPage(pageVo));
//        } else if (!"".equals(colorle)) {
//            muBaUserNew = userUserService.getBreastUserNew(startTime, endTime, input, mubale, colorle, PageUtil.initMpPage(pageVo));
//        } else {
//            muBaUserNew = userUserService.getMuBaAndBreastUserNew(startTime, endTime, input, PageUtil.initMpPage(pageVo));
//        }
//
//
//        return ResultUtil.data(muBaUserNew);
//    }
/*
    @Autowired
    private UserDeadlineService userDeadlineService;

    // 未录入
    @PostMapping("User_luru")
    public String luru(String luru) {

        // 根据时 间段先查状态表
        List<UserDeadline> list = userDeadlineService.list();
        QueryWrapper<UserDeadline> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().le(UserDeadline::getUptime, DateUtil.date()).ge()



        userDeadlineService.list(queryWrapper);



            List<Deadline> lists = deadlineService.getByTime(new Date());
        //select * from user_deadline where upTime between #{arg0} and #{arg1}
            User users = null;


            for (Deadline deadline : lists) {

                if ("true".equals(luru)) {
                    Info info = infoService.getByUserId(deadline.getUserId());
                    users = userService.getById(deadline.getUserId());
                }


                if ("false".equals(luru)) {

                    if (luru.equals("false")) {
                        users = userService.getById(deadline.getUserId());
                    }

                }


            }


            return JsonUtil.returnJson("0", "成功", users);

        }*/
/*
    // 用户资料补充
    @ResponseBody
    @RequestMapping(value = "User_update", produces = "text/html; charset=utf-8")
    public String update(HttpServletRequest request) {

        String idcard = request.getParameter("idcard");
        String phone = request.getParameter("phone");
        String education = request.getParameter("education");

        User user = userService.getByIdcard(idcard);
        userService.update(user.getId(), phone, education);
        User users = userService.getById(user.getId());

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd"));

        return JsonUtil.returnJson("0", "成功", JsonUtil.formatJson(users, jsonConfig));
    }

    // 宫颈细胞学检查上传
    @ResponseBody
    @RequestMapping(value = "User_upload", produces = "text/html; charset=utf-8")
    public String upload(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String upload = request.getParameter("upload");
        if (StringUtil.isEmpty(request.getParameter("upload"))) {
            return JsonUtil.returnJson("1", "tct条码不能为空,无法取消tct检查", null);
        }
        userService.upload(Integer.valueOf(userId), upload);
        return JsonUtil.returnJson("0", "成功", null);
    }

    // 用id查看
    @ResponseBody
    @RequestMapping(value = "User_id", produces = "text/html; charset=utf-8")
    public String iid(HttpServletRequest request) {
        String result = "";
        String id = request.getParameter("id");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd"));
        User user = userService.getById(Integer.valueOf(id));
        result = JsonUtil.formatJson(user, jsonConfig);
        if (user != null) {
            return JsonUtil.returnJson("0", "成功", result);
        } else {
            return JsonUtil.returnJson("1", "查无此人", null);
        }
    }

    // 用身份证查看
    @ResponseBody
    @RequestMapping(value = "User_idcard", produces = "text/html; charset=utf-8")
    public String iidcard(HttpServletRequest request) {
        String result = "";
        String idcard = request.getParameter("idcard");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd"));
        User user = userService.getByIdcard(idcard);
        result = JsonUtil.formatJson(user, jsonConfig);
        if (user != null) {
            return JsonUtil.returnJson("0", "成功", result);
        } else {
            return JsonUtil.returnJson("1", "查无此人", null);
        }
    }

    // 检验科用upload 查询数据
    @ResponseBody
    @RequestMapping(value = "User_uploads", produces = "text/html; charset=utf-8")
    public String uploads(HttpServletRequest request) {

        String result = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd"));

        String upload = request.getParameter("upload");
        User user = userService.uploads(upload);
        Upload uploade = new Upload();
        if (user != null) {
            Info info = infoService.getByUserId(user.getId());
            if (info == null) {
                info = new Info();
                info.setMenstruation(" ");
                info.setMenopause1(" ");
                info.setYunci(" ");
                info.setContraceptive(" ");
                info.setFenmianci(" ");
            }
            uploade.setMc(info.getMenstruation());
            uploade.setName(user.getName());
            uploade.setId(user.getId());
            uploade.setUpload(user.getUpload());
            uploade.setPhone(user.getPhone());
            uploade.setSex("2");
            uploade.setBl("细胞");
            uploade.setPd("QL020106");
            uploade.setJl("体检");
            uploade.setPn("液基薄层细胞制片术（TCT");
            uploade.setApplySection(ProperUtil.getKeyValue("project"));

            int age = Integer.valueOf(sdf.format(new Date()).substring(0, 4))
                    - Integer.valueOf(sdf.format(user.getBirthday()).substring(0, 4));

            uploade.setAge(String.valueOf(age));
            uploade.setMark("绝经 :" + info.getMenopause1() + ",   " + "孕次 :" + info.getYunci() + ",   " + "避孕方法 :"
                    + info.getContraceptive() + ",   " + "分娩次 :" + info.getFenmianci());
            result = JsonUtil.formatJson(uploade, jsonConfig);
            return JsonUtil.returnJson("0", "成功", result);
        } else {
            return JsonUtil.returnJson("1", "查无此人", null);
        }
    }

    // 人员档案条件查询name,idcard,id
    @ResponseBody
    @RequestMapping(value = "User_info", produces = "text/html; charset=utf-8")
    public String UserinfoSelect(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder result = new StringBuilder();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String input = request.getParameter("input");
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));

        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        if (startTime == "") {
            return JsonUtil.returnJson("1", "失败", null);
        }
        if (endTime == "") {
            return JsonUtil.returnJson("1", "失败", null);
        }
        try {
            // 根据对象进行查询
            IPage<UserView> list = userService.getByInfoByAreaTown(startTime, endTime, input, areaCode, townCode, PageUtil.initMpPage(new PageVo(currentPage,pageSize)));
            // 判断拼接
            if (list.getRecords().size() > 0) {
                for (UserView user : list.getRecords()) {
                    result.append("{");
                    result.append("\"id\":\"" + user.getId() + "\",");
                    result.append("\"name\":\"" + user.getName() + "\",");
                    result.append("\"gender\":\"" + user.getGender() + "\",");
                    result.append("\"nation\":\"" + user.getNation() + "\",");
                    result.append("\"birthday\":\"" + sdf.format(user.getBirthday()) + "\",");
                    result.append("\"address\":\"" + user.getAddress() + "\",");
                    result.append("\"idcard\":\"" + user.getIdcard() + "\",");
                    result.append("\"phone\":\"" + user.getPhone() + "\",");
                    result.append("\"education\":\"" + user.getEducation() + "\",");
                    result.append("\"healthid\":\"" + user.getId() + "\",");//
                    result.append("\"gjSF\":\"" + user.getGjSF() + "\",");
                    result.append("\"rxSF\":\"" + user.getRxSF() + "\",");
                    result.append("\"upload_flag\":\"" + user.getUpload_flag() + "\",");
                    result.append("\"download_flag\":\"" + user.getDownload_flag() + "\",");
                    result.append("\"area_code\":\"" + user.getArea_code() + "\",");
                    result.append("\"town_code\":\"" + user.getTown_code() + "\",");
                    result.append("\"upload\":\"" + user.getUpload() + "\"");
                    result.append("},");
                }
                String result1 = result.toString();
                result1 = result1.isEmpty() ? result1 : result1.substring(0, result1.length() - 1);
                return JsonUtil.returnJson("1", "成功", JsonUtil.returnPageJson((int) list.getTotal(), "[" + result1 + "]"));
            } else {
                return JsonUtil.returnJson("0", "查无此人", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("0", "查询异常", null);
        }
    }

    //更新档案信息
    @ResponseBody
    @RequestMapping(value = {"UserInfo_update"}, produces = {"text/html; charset=utf-8"})
    public String updateInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String nation = request.getParameter("nation");
        String address = request.getParameter("address");
        String idcard = request.getParameter("idcard");
        String phone = request.getParameter("phone");
        String education = request.getParameter("education");
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");
        try {
            this.userService.updateUserInfo(id, name, nation, address, idcard, phone, education, areaCode, townCode);
            return JsonUtil.returnJson("0", "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "修改失败", null);
        }
    }

    // 卫宁数据对接
    @ResponseBody
    @RequestMapping(value = {"uploadwn"}, produces = {"text/html; charset=utf-8"})
    public String uploadwn(HttpServletRequest request) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            // 获取前台传过来日期条件
            String startTimes = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");

            Calendar canlandarstart = Calendar.getInstance();// 开始时间
            Calendar canlandarend = Calendar.getInstance();// 结束时间
            Date startTime1 = sf.parse(startTimes);// yyyy-MM-dd格式
            Date endTime1 = sf.parse(endTime);// yyyy-MM-dd格式
            canlandarstart.setTime(startTime1);
            canlandarend.setTime(endTime1);
            JsonConfig jsonConfig = new JsonConfig();

            while (canlandarstart.compareTo(canlandarend) < 1) {
                String startTime = sf.format(canlandarstart.getTime());
                String endTime2 = sf.format(canlandarstart.getTime());
                List<UploadWn> user = this.userService.uploadwn(startTime, endTime2);
                for (int i = 0; i < user.size(); i++) {
                    //String fullPath = "D:\\高新区两癌数据\\" + "370990" + df.format(canlandarstart.getTime()) + "\\twoCancerRegister\\"+ (i + 1) + ".data";
                    //String fullPath = "D:\\岱岳区两癌数据\\" + "2020" + df.format(canlandarstart.getTime()) + "\\twoCancerRegister\\" + (i + 1) + ".data";
                    String fullPath = "D:\\岱岳区两癌数据\\" + "2020" + "\\twoCancerRegister\\" + user.get(i).getCertNo() + df.format(canlandarstart.getTime()) + ".data";
                    File file = new File(fullPath);
                    if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                        file.getParentFile().mkdirs();
                    }
                    if (file.exists()) { // 如果已存在,删除旧文件
                        file.delete();
                    }
                    file.createNewFile();
                    String json = JsonUtil.formatPageJson(user.get(i), jsonConfig);
                    // 将格式化后的字符串写入文件
                    Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    write.write(json);
                    write.flush();
                    write.close();
                }
                canlandarstart.add(canlandarstart.DATE, 1);// 每次循环增加一天
            }
            return JsonUtil.returnJson("0", "下载成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "下载失败", null);
        }
    }


    *//**
         * 获取地区信息
         *//*
    @ResponseBody
    @RequestMapping(value = {"getAddressAreaAndTown"}, produces = {"text/html; charset=utf-8"})
    public String getAddressAreaAndTown() {
        try {
            Map<String, Object> map = userService.getAddressAreaAndTown();
            JsonConfig jsonConfig = new JsonConfig();
            String result = JsonUtil.formatJson(map, jsonConfig);
            return JsonUtil.returnJson("0", "获取信息成功", result);
        } catch (Exception e) {
            LogUtil.error(e);
            return JsonUtil.returnJson("1", "获取信息失败", null);
        }
    }*/

//    }
