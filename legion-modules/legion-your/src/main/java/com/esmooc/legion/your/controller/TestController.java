package com.esmooc.legion.your.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.esmooc.legion.core.common.annotation.RateLimiter;
import com.esmooc.legion.core.common.lock.Callback;
import com.esmooc.legion.core.common.lock.RedisLockTemplate;
import com.esmooc.legion.core.common.utils.ResultUtil;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.common.vo.Result;
import com.esmooc.legion.your.server.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @author DaiMao
 */
@Slf4j
@Controller
@Api(tags = "测试接口")
@Transactional
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private RedisLockTemplate redisLockTemplate;


    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/lockAndLimit", method = RequestMethod.GET)
    @RateLimiter(rate = 1, rateInterval = 5000)
    @ApiOperation(value = "同步锁限流测试")
    @ResponseBody
    public Result<Object> test() {

        redisLockTemplate.execute("订单流水号", 3, null, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() {
                // TODO 获得锁后要做的事
                log.info("生成订单流水号");
                bookService.decrypt();

                return null;
            }

            @Override
            public Object onTimeout() {
                // TODO 未获取到锁（获取锁超时）后要做的事
                log.info("oops 没拿到锁");
                return null;
            }
        });

        return ResultUtil.data(null);
    }


}


//           sqlplus HIS/HIS@ORCL
//
//
//        alter session set container= ORCL;
//        create user HIS identified by HIS;
//        grant dba,connect,resource,create view to HIS;
//        grant select any table to HIS;
//        grant update any table to HIS;
//        grant insert any table to HIS;
//        grant delete any table to HIS;
//        grant create session to HIS;
//        grant sysdba to HIS;
//
//
//        create tablespace HIS datafile '/opt/oracle/oradata/ORACLE/ORCL/HIS.dbf' size 50M;
//
//        CREATE TEMPORARY TABLESPACE HIS_TMP TEMPFILE '/opt/oracle/oradata/ORACLE/ORCL/HIS_TMP.dbf' SIZE 2000M autoextend on next 500M maxsize 5000M;
//
//
//        alter user his default tablespace his temporary tablespace his_tmp;
//ALTER DATABASE DATAFILE '/opt/oracle/oradata/ORACLE/ORCL/HIS.dbf' AUTOEXTEND ON;
//ALTER DATABASE DATAFILE '/opt/oracle/oradata/ORACLE/ORCL/HIS.dbf' AUTOEXTEND ON NEXT 200M ;
