package com.esmooc.legion.core.common.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmooc.legion.core.common.exception.LegionException;
import com.esmooc.legion.core.common.vo.PageVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Daimao
 */
public class PageUtil {
    private final static String[] KEYWORDS = {"master", "truncate", "insert", "select",
            "delete", "update", "declare", "alter", "drop", "sleep"};

    private PageUtil() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * 条件筛选的时候一块处理了吧  虽然我也不是很喜欢   单是很方便啊
     * 写Integer 强转的时候不会抛出异常 省心
     *
     * @param map pageNumber   pageSize   sort   order
     * @return {@link Page}
     */
    public static Page initPage(Map<String, Object> map) {
        PageVo pageVo = new PageVo();
        pageVo.setPageNumber((Integer) map.get("pageNumber"));
        pageVo.setPageSize((Integer) map.get("pageSize"));

        String sort = (String) map.get("sort");
        if (StrUtil.isNotEmpty(sort)) {
            pageVo.setSort(sort);

            String order = (String) map.get("order");
            if (StrUtil.isNotEmpty(order)) {
                pageVo.setOrder(order);
            } else {
                pageVo.setOrder("desc");
            }
        }


        return initPage(pageVo);

    }

    /**
     * Mybatis-Plus分页封装
     * 里面过滤了sql注入和初始化了默认数据
     *
     * @param page
     * @return
     */
    public static Page initPage(PageVo page) {

        Page p = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        SQLInject(sort);

        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;
        if (pageSize > 100) pageSize = 100;
        if (StrUtil.isNotBlank(sort)) {
            Boolean isAsc = false;
            if (StrUtil.isBlank(order)) {
                isAsc = false;
            } else {
                if ("desc".equalsIgnoreCase(order)) {
                    isAsc = false;
                } else if ("asc".equalsIgnoreCase(order)) {
                    isAsc = true;
                }
            }
            p = new Page(pageNumber, pageSize);
            if (isAsc) {
                p.addOrder(OrderItem.asc(camel2Underline(sort)));
            } else {
                p.addOrder(OrderItem.desc(camel2Underline(sort)));
            }

        } else {
            p = new Page(pageNumber, pageSize);
        }
        return p;
    }

    /**
     * List 手动分页
     *
     * @param page
     * @param list
     * @return
     */
    public static List listToPage(PageVo page, List list) {

        int pageNumber = page.getPageNumber() - 1;
        int pageSize = page.getPageSize();

        if (pageNumber < 0) {
            pageNumber = 0;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = pageNumber * pageSize + pageSize;

        if (fromIndex > list.size()) {
            return new ArrayList();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }

    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {

        if (StrUtil.isBlank(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                sb.append("_" + Character.toLowerCase(str.charAt(i)));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0) + sb.toString()).toLowerCase();
    }

    /**
     * 防Mybatis-Plus order by注入
     *
     * @param param
     */
    public static void SQLInject(String param) {

        if (StrUtil.isBlank(param)) {
            return;
        }

        // 转换成小写
        param = param.toLowerCase();
        // 判断是否包含非法字符
        for (String keyword : KEYWORDS) {
            if (param.contains(keyword)) {
                throw new LegionException(param + "包含非法字符");
            }
        }
    }


}
