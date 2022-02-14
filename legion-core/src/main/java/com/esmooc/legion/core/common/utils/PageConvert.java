package com.esmooc.legion.core.common.utils;

/**
 * @Author 呆猫
 * @Date: 2022/02/11/ 10:39
 * @Description:
 */
public class PageConvert {

    public static <T> T  jpaToMb(T page){

        org.springframework.data.domain.Page jpaPage   =(org.springframework.data.domain.Page) page;
        com.baomidou.mybatisplus.extension.plugins.pagination.Page mbPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page();
        mbPage.setRecords(jpaPage.getContent());
        mbPage.setTotal(jpaPage.getTotalElements());
        //page1.setSize(5);
        //page1.setCurrent(1);
        mbPage.setPages(jpaPage.getTotalPages());
        //page1.setOrders(null);
        return (T) mbPage;
    }
}
