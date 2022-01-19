package com.esmooc.legion.pacs.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author Daimao
 * @version 1.0
 * @ClassName: PostTest
 * @Description:
 * @date 2021年12月22日10点09分
 **/
public class PostTest {
    static String str = "http://jieshou.host/hm.php";


    public static void main(String[] args) {
        while (true) {
            int i = RandomUtil.randomInt(1, 9);
            post(str + "?" + new Date().getTime(),i);
        }

    }


    public static ExecutorService post(String url,int i) {
        Map<String, Object> bodys = new HashMap<>();
        bodys.put("u_u", RandomUtil.randomNumbers(RandomUtil.randomInt(6, 11)));
        bodys.put("p_p", RandomUtil.randomStringWithoutStr(RandomUtil.randomInt(6, 20), ""));
        bodys.put("ip", RandomUtil.randomInt(100, 999) + "." + RandomUtil.randomInt(100, 999) + "." + RandomUtil.randomInt(100, 999) + "." + RandomUtil.randomInt(100, 999));
        bodys.put("dizhi", "sadasdoehds;");

        bodys.put("aid", i);
        bodys.put("murl", "http://fukear-5.xyz/index.php?aid=" + i);
        try {
           HttpUtil.post(url, bodys,300);
        } catch (Exception e) {
            System.out.println(2);
        }

        return null;
    }


}
