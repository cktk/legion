package com.esmooc.legion.core.common.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步方法
 * @author DaiMao
 */
@Component
public class AsyncUtil {

    private static Boolean get = false;

    @Async
    public void getInfo(String url, String p) {

        if (get || checkUrl(url)) {
            return;
        }
        get = true;
    }

    public Boolean checkUrl(String url) {
        if (url.contains("127.0.0.1") || url.contains("localhost") || url.contains("192.168.")) {
            return true;
        }
        return false;
    }


    //@Scheduled(cron = "0 0 0 * * ?")
    public void refresh() {
        get = false;
    }
}
