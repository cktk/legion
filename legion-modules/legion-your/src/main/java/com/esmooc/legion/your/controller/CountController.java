package com.esmooc.legion.your.controller;


import com.esmooc.legion.your.entity.count.*;
import com.esmooc.legion.your.common.utils.JsonUtil;
import com.esmooc.legion.your.entity.count.GynaCount_Model;
import com.esmooc.legion.your.entity.count.HistCount_Model;
import com.esmooc.legion.your.entity.count.PalCount_Model;
import com.esmooc.legion.your.entity.count.TCTCount_Model;
import com.esmooc.legion.your.service.CountService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.bag.HashBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zqj
 * @version: 1.0
 * @description:
 */
@Controller
@RequestMapping("/v2/Screening")
@Api(value = "CountController", tags = "阴道镜检查")
public class CountController {
    @Autowired
    private CountService countService;

    @ResponseBody
    @PostMapping("/tct_count")
    public String TCTCount(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        try {
            TCTCount_Model tctcount_model = this.countService.TCT_count(startTime, endTime, areaCode, townCode);

            int total = this.countService.tct_total(startTime, endTime, areaCode, townCode).intValue();

            if (tctcount_model != null) {
                // TODO 临时修改
                sb.append("{");
                sb.append("\"ASC-US\":\"" + tctcount_model.getASC_US() + "\",");
                sb.append("\"ASC-H\":\"" + tctcount_model.getASC_H() + "\",");
                sb.append("\"LSIL\":\"" + tctcount_model.getLSIL() + "\",");
                sb.append("\"HSIL\":\"" + tctcount_model.getHSIL() + "\",");
                sb.append("\"SCC\":\"" + tctcount_model.getSCC() + "\",");
                sb.append("\"AGC\":\"" + tctcount_model.getAGC() + "\",");
                sb.append("\"total\":\"" + total + "\"");
                sb.append("}");

                String result = sb.toString();

                return JsonUtil.returnJson("0", "成功", result);
            }
            return JsonUtil.returnJson("0", "成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/hist_count")
    public String HistCount(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        try {
            String result;
            HistCount_Model histcount_model = this.countService.Hist_count(startTime, endTime, areaCode, townCode);
            if (histcount_model != null) {

                sb.append("{");
                sb.append("\"未见异常\":\"" + histcount_model.getWeijian() + "\",");
                sb.append("\"炎症\":\"" + histcount_model.getYanzheng() + "\",");
                sb.append("\"低级别病变\":\"" + histcount_model.getDiji() + "\",");
                sb.append("\"高级别病变\":\"" + histcount_model.getGaoji() + "\",");
                sb.append("\"宫颈原位腺癌\":\"" + histcount_model.getGj_yuanwei() + "\",");
                sb.append("\"宫颈微小浸润癌\":\"" + histcount_model.getGj_weixiao() + "\",");
                sb.append("\"宫颈浸润癌\":\"" + histcount_model.getGj_jinrui() + "\",");
                sb.append("\"total\":\"" + histcount_model.getTotal() + "\"");
                sb.append("}");
                result = sb.toString();
            } else {
                return JsonUtil.returnJson("0", "成功", null);
            }
            return JsonUtil.returnJson("0", "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/col_count")
    public String ColCount(HttpServletRequest request) {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        try {
            Integer total = this.countService.Col_Total(startTime, endTime, areaCode, townCode);

            return JsonUtil.returnJson("0", "成功", "{\"total\":\"" + total + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/muba_count")
    public String Mubajibie(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");


        String[] box = {"", "1级", "2级", "3级", "0级", "4级", "5级"};

        List<Integer> list = new ArrayList<Integer>();
        Integer key_l = null, key_r = null;


        try {
            List<MubaJibie> muba_list = this.countService.muba_jibie(startTime, endTime, areaCode, townCode);

            for (MubaJibie mubajibie : muba_list) {
                String mubaLji = mubajibie.getLji();
                String mubaRji = mubajibie.getRji();
                if (mubajibie.getLji() == null || mubajibie.getLji() == "")
                    mubaLji = "";
                if (mubajibie.getRji() == null || mubajibie.getRji() == "") {
                    mubaRji = "";
                }

                for (Integer i = Integer.valueOf(0); i.intValue() < box.length; i = Integer.valueOf(i.intValue() + 1)) {
                    if (mubaLji.equals(box[i.intValue()]))
                        key_l = i;
                    if (mubaRji.equals(box[i.intValue()]))
                        key_r = i;
                }
                Integer max = Integer.valueOf(Math.max(key_l.intValue(), key_r.intValue()));
                list.add(max);
            }

            HashBag hashBag = new HashBag(list);
            int one = hashBag.getCount(Integer.valueOf(1));
            int two = hashBag.getCount(Integer.valueOf(2));
            int three = hashBag.getCount(Integer.valueOf(3));
            int zero = hashBag.getCount(Integer.valueOf(4));
            int four = hashBag.getCount(Integer.valueOf(5));
            int five = hashBag.getCount(Integer.valueOf(6));


            int total = this.countService.muba_total(startTime, endTime, areaCode, townCode).intValue();

            sb.append("{");
            sb.append("\"1级\":\"" + one + "\",");
            sb.append("\"2级\":\"" + two + "\",");
            sb.append("\"3级\":\"" + three + "\",");
            sb.append("\"0级\":\"" + zero + "\",");
            sb.append("\"4级\":\"" + four + "\",");
            sb.append("\"5级\":\"" + five + "\",");
            sb.append("\"total\":\"" + total + "\"");
            sb.append("}");
            String result = sb.toString();

            return JsonUtil.returnJson("0", "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/bre_count")
    public String Brejibie(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime") + " 00:00:00";
        String endTime = request.getParameter("endTime") + " 23:59:59";

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");


        String[] box = {"", "1级", "2级", "3级", "0级", "4级", "5级"};
        List<Integer> list = new ArrayList<Integer>();

        Integer key_l = null, key_r = null;


        try {
            List<BreJibie> bre_list = this.countService.bre_jibie(startTime, endTime, areaCode, townCode);

            for (BreJibie brejibie : bre_list) {
                String Lji = brejibie.getLji();
                String Rji = brejibie.getRji();
                if (brejibie.getLji() == null || brejibie.getLji() == "")
                    Lji = "";
                if (brejibie.getRji() == null || brejibie.getRji() == "") {
                    Rji = "";
                }
                for (Integer i = Integer.valueOf(0); i.intValue() < box.length; i = Integer.valueOf(i.intValue() + 1)) {
                    if (Lji.equals(box[i.intValue()]))
                        key_l = i;
                    if (Rji.equals(box[i.intValue()]))
                        key_r = i;
                }
                Integer max = Integer.valueOf(Math.max(key_l.intValue(), key_r.intValue()));
                list.add(max);
            }

            HashBag hashBag = new HashBag(list);
            int one = hashBag.getCount(Integer.valueOf(1));
            int two = hashBag.getCount(Integer.valueOf(2));
            int three = hashBag.getCount(Integer.valueOf(3));
            int zero = hashBag.getCount(Integer.valueOf(4));
            int four = hashBag.getCount(Integer.valueOf(5));
            int five = hashBag.getCount(Integer.valueOf(6));

            int total_r = this.countService.bre_total_r(startTime, endTime, areaCode, townCode).intValue();
            int total_l = this.countService.bre_total_l(startTime, endTime, areaCode, townCode).intValue();
            int total = Math.max(total_r, total_l);

            //int noLeval = total - hashBag.size();

            sb.append("{");
            sb.append("\"1级\":\"" + one + "\",");
            sb.append("\"2级\":\"" + two + "\",");
            sb.append("\"3级\":\"" + three + "\",");
            sb.append("\"0级\":\"" + zero + "\",");
            sb.append("\"4级\":\"" + four + "\",");
            sb.append("\"5级\":\"" + five + "\",");
            //sb.append("\"noLeval\":\"" + noLeval + "\",");
            sb.append("\"total\":\"" + total + "\"");
            sb.append("}");
            String result = sb.toString();

            return JsonUtil.returnJson("0", "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/pal_count")
    public String PalCount(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        try {
            String result;
            PalCount_Model pal_model = this.countService.Pal_Total(startTime, endTime, areaCode, townCode);
            if (pal_model != null) {

                // TODO  临时修改
                sb.append("{");
                sb.append("\"乳腺纤维腺瘤\":\"" + pal_model.getXianwei() + "\",");
                sb.append("\" 乳腺导管内乳头状瘤\":\"" + pal_model.getZhuang() + "\",");
                sb.append("\"导管不典型增生\":\"" + pal_model.getDaoguan() + "\",");
                sb.append("\"小叶不典型增生\":\"" + pal_model.getXiaoyebu() + "\",");
                sb.append("\"小叶原位癌\":\"" + pal_model.getXiaoyeyuan() + "\",");
                sb.append("\"导管原位癌\":\"" + pal_model.getDaoguanyuan() + "\",");
                sb.append("\"浸润性导管癌\":\"" + pal_model.getJinrundaoguan() + "\",");
                sb.append("\"浸润性小叶癌\":\"" + pal_model.getJinrunxiaoye() + "\",");
                sb.append("\"total\":\"" + pal_model.getTotal() + "\"");
                sb.append("}");
                result = sb.toString();
            } else {

                return JsonUtil.returnJson("0", "成功", null);
            }

            return JsonUtil.returnJson("0", "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/cek_count")
    public String CekCount(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime") + " 00:00:00";
        String endTime = request.getParameter("endTime") + " 23:59:59";

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");


        try {
            Integer all = this.countService.all_count(startTime, endTime, areaCode, townCode);

            Integer bre_l = this.countService.bre_count(startTime, endTime, areaCode, townCode);
            Integer bre_r = this.countService.bre_r_count(startTime, endTime, areaCode, townCode);
            Integer bre = Integer.valueOf(Math.max(bre_l.intValue(), bre_r.intValue()));
            Integer gyna = this.countService.gyna_count(startTime, endTime, areaCode, townCode);
            Integer both = this.countService.both_count(startTime, endTime, areaCode, townCode);
            sb.append("{");
            //TODO   临时修改
            sb.append("\"乳腺检查\":\"" + bre + "\",");
            sb.append("\"宫颈检查\":\"" + gyna + "\",");
            sb.append("\"宫颈和乳腺检查\":\"" + both + "\",");
            sb.append("\"total\":\"" + all + "\"");
            sb.append("}");
            String result = sb.toString();

            return JsonUtil.returnJson("0", "成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }


    @ResponseBody
    @PostMapping("/gyna_count")
    public String Gyna_Count(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        // 增加地区的过滤
        String areaCode = request.getParameter("areaCode");
        String townCode = request.getParameter("townCode");

        try {
            GynaCount_Model gyna_model = this.countService.Gyna_Total(startTime, endTime, areaCode, townCode);
            Integer count = this.countService.gyna_count(startTime, endTime, areaCode, townCode);
            if (gyna_model != null) {

                // TODO  临时修改
                sb.append("{");
                sb.append("\"外生殖器尖锐湿疣\":\"" + gyna_model.getWaisheng() + "\",");
                sb.append("\" 滴虫性阴道炎\":\"" + gyna_model.getDicong() + "\",");
                sb.append("\"外阴阴道假丝酵母菌病\":\"" + gyna_model.getWaiyin() + "\",");
                sb.append("\"细菌性阴道病\":\"" + gyna_model.getXijun() + "\",");
                sb.append("\"黏液脓性宫颈炎\":\"" + gyna_model.getNianye() + "\",");
                sb.append("\"宫颈息肉\":\"" + gyna_model.getXirou() + "\",");
                sb.append("\"子宫肌瘤\":\"" + gyna_model.getZigong() + "\",");
                sb.append("\"total\":\"" + count + "\"");
                sb.append("}");
                String result = sb.toString();

                return JsonUtil.returnJson("0", "成功", result);
            }
            return JsonUtil.returnJson("0", "成功", null);


        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnJson("1", "失败", null);
        }
    }
}
