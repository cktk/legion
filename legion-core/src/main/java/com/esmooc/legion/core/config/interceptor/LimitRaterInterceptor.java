package com.esmooc.legion.core.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.esmooc.legion.core.common.annotation.RateLimiter;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.constant.SettingConstant;
import com.esmooc.legion.core.common.exception.LimitException;
import com.esmooc.legion.core.common.limit.RedisRaterLimiter;
import com.esmooc.legion.core.common.utils.IpInfoUtil;
import com.esmooc.legion.core.config.properties.LegionIpLimitProperties;
import com.esmooc.legion.core.config.properties.LegionLimitProperties;
import com.esmooc.legion.core.entity.Setting;
import com.esmooc.legion.core.service.SettingService;
import com.esmooc.legion.core.vo.OtherSetting;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 限流拦截器
 *
 * @author DaiMao
 */
@Slf4j
@Component
public class LimitRaterInterceptor implements HandlerInterceptor {

    @Autowired
    private LegionLimitProperties limitProperties;

    @Autowired
    private LegionIpLimitProperties ipLimitProperties;

    @Autowired
    private RedisRaterLimiter redisRaterLimiter;

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @Autowired
    private SettingService settingService;

    public OtherSetting getOtherSetting() {

        Setting setting = settingService.get(SettingConstant.OTHER_SETTING);
        if (StrUtil.isBlank(setting.getValue())) {
            return null;
        }
        return new Gson().fromJson(setting.getValue(), OtherSetting.class);
    }

    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）
     * 第三个参数为响应的处理器，即controller
     * 返回true，表示继续流程，调用下一个拦截器或者处理器
     * 返回false，表示流程中断，通过response产生响应
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String ip = ipInfoUtil.getIpAddr(request);

        if (ipLimitProperties.getEnable()) {
            Boolean token1 = redisRaterLimiter.acquireByRedis(ip,
                    ipLimitProperties.getLimit(), ipLimitProperties.getTimeout());
            if (!token1) {
              //  throw new LimitException("你手速怎么这么快，请点慢一点");
            }
        }

        if (limitProperties.getEnable()) {
            Boolean token2 = redisRaterLimiter.acquireByRedis(CommonConstant.LIMIT_ALL,
                    limitProperties.getLimit(), limitProperties.getTimeout());
            if (!token2) {
                throw new LimitException("当前访问总人数太多啦，请稍后再试");
            }
        }

        // IP黑名单
        OtherSetting os = getOtherSetting();
        if (os != null && StrUtil.isNotBlank(os.getBlacklist())) {
            String[] list = os.getBlacklist().split("\n");
            for (String item : list) {
                if (item.equals(ip)) {
                    throw new LimitException("您的IP已被添加至黑名单，请滚");
                }
            }
        }

        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object bean = handlerMethod.getBean();
            Method method = handlerMethod.getMethod();
            RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
            if (rateLimiter != null) {
                String name = rateLimiter.name();
                Long limit = rateLimiter.rate();
                Long timeout = rateLimiter.rateInterval();
                if (StrUtil.isBlank(name)) {
                    name = StrUtil.subBefore(bean.toString(), "@", false) + "_" + method.getName();
                }
                if (rateLimiter.ipLimit()) {
                    name += "_" + ip;
                }
                Boolean token3 = redisRaterLimiter.acquireByRedis(name, limit, timeout);
                if (!token3) {
                    String msg = "当前访问人数太多啦，请稍后再试";
                    if (rateLimiter.ipLimit()) {
                        msg = "你手速怎么这么快，请点慢一点";
                    }
                    throw new LimitException(msg);
                }
            }
        } catch (LimitException e) {
            throw new LimitException(e.getMsg());
        } catch (Exception e) {

        }

        return true;
    }

    /**
     * 当前请求进行处理之后，也就是Controller方法调用之后执行，
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用。
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 方法将在整个请求结束之后，也就是在DispatcherServlet渲染了对应的视图之后执行。
     * 这个方法的主要作用是用于进行资源清理工作的。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
