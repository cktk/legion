package com.esmooc.legion.your.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

public class JsonUtil {

    // 格式化json
    public static String formatJson(Object object, JsonConfig str) {

        if (object instanceof List) {
            return JSONArray.fromObject(object, str).toString();
        } else {
            return JSONObject.fromObject(object, str).toString();
        }
    }

    // 格式化json
    public static String formatPageJson(Object object, JsonConfig str) {

        if (object instanceof List) {
            return JSONArray.fromObject(object, str).toString();
        } else {
            return JSONObject.fromObject(object, str).toString();
        }
    }

    // 返回标准格式数据
    public static String returnJson(String status, String msg, String result) {

        if (("").equals(result)) {
            return "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\", \"data\":{}}";
        } else {
            return "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\", \"data\":" + result + "}";
        }
    }
    // 返回标准格式数据
    public static String returnJson(String status, String msg, Object obj) {
        String data = JSONUtil.toJsonStr(obj);
        status = StrUtil.isEmpty(status) ? "1" : status;
        msg = StrUtil.isEmpty(msg) ? "1" : msg;
        data = StrUtil.isEmpty(data) ? "1" : data;
        return "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\", \"data\":" + data + "}";

    }

    // 返回标准格式数据
    public static String returnPageJson(int total, String result) {

        if (("").equals(result)) {
            return "{\"total\":\"" + total + "\", \"rows\":{}}";
        } else {
            return "{\"total\":\"" + total + "\", \"rows\":" + result + "}";
        }
    }
}
