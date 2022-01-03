package com.esmooc.legion.core.common.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author Daimao
 */
@Slf4j
@Component
public class IpInfoUtil {

    @Value("${legion.qqlbs.key}")
    private String key;

    @Autowired
    private AsyncUtil asyncUtil;

    /**
     * 获取客户端IP地址
     * @param request 请求
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    log.warn(e.toString());
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取IP返回地理信息
     * @param
     * @return
     */
    public String getIpCity(HttpServletRequest request) {

        String url = "https://apis.map.qq.com/ws/location/v1/ip?key=" + key + "&ip=" + getIpAddr(request);
        String result = "未知";
        try {
            JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.get(url, 3000));
            if ("0".equals(jsonObject.getStr("status"))) {
                JSONObject adInfo = jsonObject.getJSONObject("result").getJSONObject("ad_info");
                String nation = adInfo.getStr("nation");
                String province = adInfo.getStr("province");
                String city = adInfo.getStr("city");
                String district = adInfo.getStr("district");
                if (StrUtil.isNotBlank(nation) && StrUtil.isBlank(province)) {
                    result = nation;
                } else {
                    result = province;
                    if (StrUtil.isNotBlank(city)) {
                        result += " " + city;
                    }
                    if (StrUtil.isNotBlank(district)) {
                        result += " " + district;
                    }
                }
            }
        } catch (Exception e) {
            log.info("获取IP地理信息失败");
        }
        return result;
    }


}
