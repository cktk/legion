package com.esmooc.legion.core.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段填充审计
 *
 * @author DaiMao
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        if (metaObject.hasSetter("createId")) {
            metaObject.setValue("createId", null);
        }

        if (metaObject.hasSetter("createBy")) {
            metaObject.setValue("createBy", null);
        }
        if (metaObject.hasSetter("createTime")) {
            metaObject.setValue("createTime", null);
        }


        try {
            User user = SecurityUtil.getUser();
            String id = user.getId();
            String username = user.getUsername();
            this.setFieldValByName("createId", Long.parseLong(id), metaObject);
            this.setFieldValByName("createBy", username, metaObject);
        } catch (Exception e) {

        }

        this.setFieldValByName("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        if (metaObject.hasSetter("updateId")) {
            metaObject.setValue("updateId", null);
        }

        if (metaObject.hasSetter("updateBy")) {
            metaObject.setValue("updateBy", null);
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", null);
        }

        try {
            User user = SecurityUtil.getUser();
            String id = user.getId();
            String username = user.getUsername();
            this.setFieldValByName("updateId", Long.parseLong(id), metaObject);
            this.setFieldValByName("updateBy", username, metaObject);
        } catch (Exception e) {

        }

        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}



