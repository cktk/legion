package com.esmooc.legion.core.common.aop;

import com.esmooc.legion.core.base.LegionBaseEntity;
import com.esmooc.legion.core.common.constant.CommonConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResultAop {

    @Pointcut("execution(* *..controller..*Controller*.*(..))")
    public void BrokerAspect() {

    }

    @Before("BrokerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof LegionBaseEntity) {
                try {
                    LegionBaseEntity base = (LegionBaseEntity) arg;
                    base.setCreateId(null);
                    base.setCreateBy(null);
                    base.setCreateTime(null);
                    base.setDelFlag(CommonConstant.STATUS_NORMAL);
                    base.setUpdateBy(null);
                    base.setUpdateId(null);
                    base.setUpdateTime(null);
                } catch (Exception e) {
                }

                break;
            }
        }


    }


}