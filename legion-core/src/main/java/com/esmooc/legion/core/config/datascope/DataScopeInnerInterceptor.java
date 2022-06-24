package com.esmooc.legion.core.config.datascope;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.esmooc.legion.core.common.utils.SecurityUtil;
import com.esmooc.legion.core.config.tenant.TenantConfigProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Slf4j
@Component
public class DataScopeInnerInterceptor implements InnerInterceptor {

    @Setter
    private DataScopeHandle dataScopeHandle;
    private static TenantConfigProperties tenantConfigProperties = SpringUtil.getBean(TenantConfigProperties.class);


    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) {
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);

        String originalSql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        DataScope dataScope = null;
        for (String table : tenantConfigProperties.getScopeTables()) {
            if (StrUtil.contains(originalSql, table)) {
                dataScope = new DataScope();
                break;
            }
        }


        if (dataScope == null) {
            return;
        }

        String scopeName = dataScope.getScopeName();

        List<String> deptIds = SecurityUtil.getDeparmentIds();


        // 优先获取赋值数据
        if (deptIds == null) {
            originalSql = String.format("SELECT %s FROM (%s) temp_data_scope", dataScope.getFunc().getType(),
                    originalSql);
            mpBs.sql(originalSql);
            return;
        }

        if (deptIds.size() == 0) {
            originalSql = String.format("SELECT %s FROM (%s) temp_data_scope WHERE 1 = 2",
                    dataScope.getFunc().getType(), originalSql);
        } else {
            String join = CollectionUtil.join(deptIds, ",");
            originalSql = String.format("SELECT %s FROM (%s) temp_data_scope WHERE temp_data_scope.%s IN (%s)",
                    dataScope.getFunc().getType(), originalSql, scopeName, join);
        }

        mpBs.sql(originalSql);
    }

    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                }
            }
        }
        return null;
    }

}
