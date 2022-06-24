package com.esmooc.legion.core.config.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.esmooc.legion.core.common.constant.CommonConstant;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.config.datascope.DataScope;
import com.esmooc.legion.core.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 16:02
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("mybatis plus start insert fill ....");

        // 审计字段自动填充
        fillValIfNullByName("createTime",  new Date(), metaObject, false);
        fillValIfNullByName("createBy", getUserName(), metaObject, false);
        fillValIfNullByName("createId", getUserId(), metaObject, false);

        fillValIfNullByName(DataScope.of().getScopeName(), getDepartmentId(), metaObject, false);
        // 删除标记自动填充
        fillValIfNullByName("delFlag",CommonConstant.STATUS_NORMAL+"", metaObject, false);
    }



    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("mybatis plus start update fill ....");
        fillValIfNullByName("updateTime", new Date() , metaObject, false);
        fillValIfNullByName("updateId", getUserId(), metaObject, false);
        fillValIfNullByName("updateBy", getUserName(), metaObject, false);
    }

    /**
     * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
     *
     * @param fieldName  属性名
     * @param fieldVal   属性值
     * @param metaObject MetaObject
     * @param isCover    是否覆盖原有值,避免更新操作手动入参
     */
    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        // 0. 如果填充值为空
        if (fieldVal == null) {
            return;
        }
        // 1. 没有 get 方法
        if (!metaObject.hasSetter(fieldName)) {
            return;
        }
        // 2. 如果用户有手动设置的值
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        // 3. field 类型相同时设置
        Class<?> getterType = metaObject.getGetterType(fieldName);
        if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
            metaObject.setValue(fieldName, fieldVal);
        }

    }

    /**
     * 获取 spring security 当前的用户名
     *
     * @return 当前用户名
     */
    private String getUserName() {
        try {
            User user = SecurityUtil.getUser();
            return user.getUsername();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取 spring security 当前的用户ID
     *
     * @return 当前用户名
     */
    private Long getUserId() {
        try {
            User user = SecurityUtil.getUser();
            return Long.parseLong(user.getId());
        } catch (Exception e) {
        }
        return null;
    }


    private String getDepartmentId() {
        try {
            User user = SecurityUtil.getUser();
            return user.getDepartmentId();
        } catch (Exception e) {
        }
        return null;
    }

}
