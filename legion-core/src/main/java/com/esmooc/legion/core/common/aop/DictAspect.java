package com.esmooc.legion.core.common.aop;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.esmooc.legion.core.common.annotation.Dict;
import com.esmooc.legion.core.common.utils.ObjConvertUtils;
import com.esmooc.legion.core.common.utils.PageUtils;
import com.esmooc.legion.core.service.DictionariesService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zqj
 * @Create: 2021-11-16 13:44
 * @Description: aop切面类
 */
@Aspect
@Component
@Slf4j
public class DictAspect {

	//表对应字段加上_dictText即可显示出文本
    private static String DICT_TEXT_SUFFIX = "_Text";

    @Autowired
    private DictionariesService dataItemService;

    //定义切点Pointcut拦截所有对服务器的请求
    @Pointcut("execution(* com.esmooc.legion.*.*(..))")
    public void excudeService() {

    }

    /**
     * 这是触发DictionariesService的时候会执行的，在环绕通知中目标对象方法被调用后的结果进行再处理
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //这是方法并获取返回结果
        Object result = pjp.proceed();
        //开始解析（翻译字段内部的值凡是打了@Dict这玩意的都会被翻译）
        this.parseDictText(result);
        return result;
    }


    private void parseDictText(Object result) {
        if (result instanceof PageUtils) {
            List<JSONObject> items = new ArrayList<>();
            PageUtils pageUtils = (PageUtils) result;
            //循环查找出来的数据
            for (Object record : pageUtils.getList()) {
                ObjectMapper mapper = new ObjectMapper();
                String json = "{}";
                try {
                    //解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
                    json = mapper.writeValueAsString(record);
                } catch (JsonProcessingException e) {
                    log.error("Json解析失败：" + e);
                }

                JSONObject item = JSONUtil.parseObj(json);

                //解决继承实体字段无法翻译问题
                for (Field field : ObjConvertUtils.getAllFields(record)) {
                    //解决继承实体字段无法翻译问题
                    if (field.getAnnotation(Dict.class) != null) {//如果该属性上面有@Dict注解，则进行翻译
                        String datasource = field.getAnnotation(Dict.class).dictDataSource();//拿到注解的dictDataSource属性的值
                        String text = field.getAnnotation(Dict.class).dictText();//拿到注解的dictText属性的值
                        //获取当前带翻译的值
                        String key = String.valueOf(item.get(field.getName()));
                        //翻译字典值对应的text值
                        String textValue = translateDictValue(datasource, key);
                        //DICT_TEXT_SUFFIX的值为，是默认值：
                        //public static final String DICT_TEXT_SUFFIX = "_dictText";
                         log.debug("字典Val: " + textValue);
                        log.debug("翻译字典字段：" + field.getName() + DICT_TEXT_SUFFIX + "： " + textValue);
                        //如果给了文本名
                        if (!StringUtils.isBlank(text)) {
                            item.put(text, textValue);
                        } else {
                            //走默认策略
                            item.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                        }
                    }
                    //date类型默认转换string格式化日期
                    if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null && item.get(field.getName()) != null) {
                        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        item.put(field.getName(), aDate.format(new Date((Long) item.get(field.getName()))));
                    }
                }
                items.add(item);
            }
            pageUtils.setList(items);
        }
    }


    /**
     * 翻译字典文本
     * @param datasource
     * @param key
     * @return
     */
    private String translateDictValue(String datasource, String key) {
        //如果key为空直接返回就好了
        if (ObjConvertUtils.isEmpty(key)) {
            return null;
        }
        StringBuffer textValue = new StringBuffer();
        //分割key值
        String[] keys = key.split(",");
        //循环keys中的所有值
        for (String k : keys) {
            String tmpValue = null;
            log.debug("字典key：" + k);
            if (k.trim().length() == 0) {
                continue;//跳过循环
            }
            tmpValue = dataItemService.selectByFieldNameAndFieldValue(datasource, k.trim());

            if (tmpValue != null) {
                if (!"".equals(textValue.toString())) {
                    textValue.append(",");
                }
                textValue.append(tmpValue);
            }
        }
        //返回翻译的值
        return textValue.toString();
    }
}
