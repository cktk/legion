package com.esmooc.legion.core.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName QueryWrapper自定义SQL查询构造器
 * @Author Lu Ze
 * @Date 2020/4/1
 * @Version V1.0
 **/
public class SearchUtil {

    //TODO 全局不构造
    private static HashMap<String, String> IGNORE_KEYS = new HashMap<String, String>() {{
        put("id", "忽略");

    }};

    /**
     * 解析sql在哪里
     * eq：相等<br />
     * ne：不等<br />
     * like：两边模糊查询<br />
     * leftLike：左边模糊查询<br />
     * rightLike：右边模糊查询<br />
     * notLike：不与%xx%相等<br />
     * gt：大于<br />
     * lt：小于<br />
     * ge：大于等于<br />
     * le：小于等于<br />
     * bet：...之间 bet_age=15,20
     * notBet：不在...之间 netBet_age=15,20
     * or：在紧接着下一个条件使用，否则不生效 <br />
     *
     * @param condition 条件
     * @return {@link QueryWrapper}
     */
    public static QueryWrapper parseWhereSql(Map<String, Object> condition) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (condition != null && condition.size() > 0) {
            condition.forEach((k, v) -> {

                if (k != null && v != null && k.contains("_") && StrUtil.isNotEmpty(v.toString())) {
                    String pre = k.substring(0, k.indexOf("_"));
                    String column = humpToLine(k.substring(k.indexOf("_") + 1));
                    switch (pre) {
                        case "eq":
                            queryWrapper.eq(column, v);
                            break;
                        case "ne":
                            queryWrapper.ne(column, v);
                            break;
                        case "like":
                            queryWrapper.like(column, v);
                            break;
                        case "leftLike":
                            queryWrapper.likeLeft(column, v);
                            break;
                        case "rightLike":
                            queryWrapper.likeRight(column, v);
                            break;
                        case "notLike":
                            queryWrapper.notLike(column, v);
                            break;
                        case "gt":
                            queryWrapper.gt(column, v);
                            break;
                        case "lt":
                            queryWrapper.lt(column, v);
                            break;
                        case "ge":
                            queryWrapper.ge(column, v);
                            break;
                        case "le":
                            queryWrapper.le(column, v);
                            break;
                        case "bet":
                            String[] arr = v.toString().split(",");
                            queryWrapper.between(column, arr[0], arr[1]);
                            break;
                        case "notBet":
                            arr = v.toString().split(",");
                            queryWrapper.notBetween(column, arr[0], arr[1]);
                            break;
                        case "or":
                            queryWrapper.or(true);
                            break;
                    }


                }

            });
        }
        return queryWrapper;
    }


    private static Map<String, Object> parseWhereSqlToMap(Object obj) {
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        Map<String, Object> maps = BeanUtil.beanToMap(obj);
        //TODO 现在传对象默认为 eq
        map.forEach((k, v) -> {

            if (v instanceof Date) {

            } else if (v instanceof String) {
                maps.put("eq_" + k, v);
            } else if (v instanceof Boolean) {
                maps.put("eq_" + k, v);
            } else if (v instanceof Integer) {
                maps.put("eq_" + k, v);
            } else if (v instanceof Double) {
                maps.put("eq_" + k, v);
            } else if (v instanceof Byte) {
                maps.put("eq_" + k, v);
            }


        });
        return maps;
    }

    public static QueryWrapper parseWhereSql(Object... obj) {
        Map<String, Object> map = BeanUtil.beanToMap(obj);
        for (Object o : obj) {
            map.putAll(parseWhereSqlToMap(o));
        }
        return parseWhereSql(map);
    }

    /**
     * 驼峰转下划线
     *
     * @param column
     * @return
     */
    private static String humpToLine(String column) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(column);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}

