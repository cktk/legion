package com.esmooc.legion.core.common.aop;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.esmooc.legion.core.common.annotation.DefaultDs;
import com.esmooc.legion.core.common.exception.LegionException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>
 * 数据源选择器切面
 * </p>
 *
 * @author starsray
 * @since 2021-11-10
 */
@Aspect
@Component
public class DsInterceptor implements HandlerInterceptor {

    //@Pointcut("execution(public * com.esmooc.legion.*.*.*(..))")
    @Pointcut("execution(* com.esmooc.legion.*.*(..))")
    public void datasourcePointcut() {
    }

    /**
     * 前置操作，拦截具体请求，获取header里的数据源id，设置线程变量里，用于后续切换数据源
     */
    @Before("datasourcePointcut()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 排除不可切换数据源的方法
        DefaultDs annotation = method.getAnnotation(DefaultDs.class);
        if (null != annotation) {
            DynamicDataSourceContextHolder.push("master");
        } else {
            //TODO 拦截class 根据mode 进行处理
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            String header = request.getHeader("tenantName");
            System.err.println("切换数据源了");
            System.err.println("切换数据源了");
            System.err.println("切换数据源了");
            System.err.println("切换数据源了");
            if (StringUtils.isNotBlank(header)) {
                DynamicDataSourceContextHolder.push(header);
            } else {
                throw new LegionException("数据源切换失败");
            }
        }
    }

    /**
     * 后置操作，设置回默认的数据源id
     */
    @AfterReturning("datasourcePointcut()")
    public void doAfter() {
        DynamicDataSourceContextHolder.push("master");
    }


    public String getDSName() {
        return "ds";
    }

}
