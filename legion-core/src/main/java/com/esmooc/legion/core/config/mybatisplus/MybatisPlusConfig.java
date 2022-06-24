package com.esmooc.legion.core.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.esmooc.legion.core.config.datascope.DataScopeHandle;
import com.esmooc.legion.core.config.datascope.DataScopeInnerInterceptor;
import com.esmooc.legion.core.config.datascope.DataScopeSqlInjector;
import com.esmooc.legion.core.config.datascope.DefaultDatascopeHandle;
import com.esmooc.legion.core.config.tenant.TenantHandler;
import com.esmooc.legion.core.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 呆猫
 * @version 1.0
 * @date 2022年06月24日 15:10
 * @about :
 * ------------🌈-💨------------
 * 美 好 生 活 从 维 护 代 码 开 始
 * ----------------------------
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis plus 拦截器配置
     * @return DefaultDatascopeHandle
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户支持
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(TenantHandler());
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
        // 数据权限
		DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor();
		dataScopeInnerInterceptor.setDataScopeHandle(dataScopeHandle());
		interceptor.addInnerInterceptor(dataScopeInnerInterceptor);
        // 分页支持
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(2L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    /**
     * 创建租户维护处理器对象
     * @return 处理后的租户维护处理器
     */
    @Bean
	@ConditionalOnMissingBean
    public TenantHandler TenantHandler() {
        return new TenantHandler();
    }

    /**
     *  默认数据权限处理器
     * @return DefaultDatascopeHandle
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(User.class)
    public DataScopeHandle dataScopeHandle() {
        return new DefaultDatascopeHandle();
    }



    /**
     * 扩展 mybatis-plus baseMapper 支持数据权限
     * @return
     */
	@Bean
	@ConditionalOnBean(DataScopeHandle.class)
	public DataScopeSqlInjector dataScopeSqlInjector() {
		return new DataScopeSqlInjector();
	}
}
