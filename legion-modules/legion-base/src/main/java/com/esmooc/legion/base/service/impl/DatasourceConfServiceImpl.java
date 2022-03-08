package com.esmooc.legion.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmooc.legion.base.entity.DatasourceConf;
import com.esmooc.legion.base.mapper.DatasourceConfMapper;
import com.esmooc.legion.base.service.DatasourceConfService;
import com.esmooc.legion.core.common.utils.SpringContextHolder;
import com.esmooc.legion.core.datasource.util.DsConfTypeEnum;
import com.esmooc.legion.core.datasource.util.DsJdbcUrlEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author 呆猫
 * @Date: 2022/03/02/ 10:47
 * @Description:
 */
@Slf4j
@Service
@AllArgsConstructor
public class DatasourceConfServiceImpl extends ServiceImpl<DatasourceConfMapper, DatasourceConf> implements DatasourceConfService {
    private final StringEncryptor stringEncryptor;

    private final DataSourceCreator druidDataSourceCreator;

    /**
     * 保存数据源并且加密
     *
     * @param conf
     * @return
     */
    @Override
    public Boolean saveDsByEnc(DatasourceConf conf) {
        // 校验配置合法性
        if (!checkDataSource(conf)) {
            return Boolean.FALSE;
        }

        // 添加动态数据源
        addDynamicDataSource(conf);

        // 更新数据库配置
        conf.setPassword(stringEncryptor.encrypt(conf.getPassword()));

        log.info("添加数据源的时候数据源的ID {} conf", conf.getId());
        this.save(conf);
        log.info("------------------------------------------");
        log.info("添加数据源的时候数据源完成后 {} conf", conf);
        return Boolean.TRUE;
    }

    /**
     * 更新数据源
     *
     * @param conf 数据源信息
     * @return
     */
    @Override
    public Boolean updateDsByEnc(DatasourceConf conf) {
        if (!checkDataSource(conf)) {
            return Boolean.FALSE;
        }
        // 先移除
        DynamicRoutingDataSource dynamicRoutingDataSource = SpringContextHolder.getBean(DynamicRoutingDataSource.class);
        dynamicRoutingDataSource.removeDataSource(baseMapper.selectById(conf.getId()).getName());

        // 再添加
        addDynamicDataSource(conf);

        // 更新数据库配置
        if (StrUtil.isNotBlank(conf.getPassword())) {
            conf.setPassword(stringEncryptor.encrypt(conf.getPassword()));
        }
        this.baseMapper.updateById(conf);
        return Boolean.TRUE;
    }

    /**
     * 通过数据源名称删除
     *
     * @param dsId 数据源ID
     * @return
     */
    @Override
    public Boolean removeByDsId(Long dsId) {
        DynamicRoutingDataSource dynamicRoutingDataSource = SpringContextHolder.getBean(DynamicRoutingDataSource.class);
        dynamicRoutingDataSource.removeDataSource(baseMapper.selectById(dsId).getName());
        this.baseMapper.deleteById(dsId);
        return Boolean.TRUE;
    }

    /**
     * 添加动态数据源
     *
     * @param conf 数据源信息
     */
    @Override
    public void addDynamicDataSource(DatasourceConf conf) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(conf.getName());
        dataSourceProperty.setUrl(conf.getUrl());
        dataSourceProperty.setUsername(conf.getUsername());
        dataSourceProperty.setPassword(conf.getPassword());

        // 增加 ValidationQuery 参数
        DruidConfig druidConfig = new DruidConfig();
        DsJdbcUrlEnum urlEnum = DsJdbcUrlEnum.get(conf.getDsType());
        druidConfig.setValidationQuery(urlEnum.getValidationQuery());
        dataSourceProperty.setDruid(druidConfig);
        DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);

        DynamicRoutingDataSource dynamicRoutingDataSource = SpringContextHolder.getBean(DynamicRoutingDataSource.class);
        dynamicRoutingDataSource.addDataSource(dataSourceProperty.getPoolName(), dataSource);
    }

    /**
     * 校验数据源配置是否有效
     *
     * @param conf 数据源信息
     * @return 有效/无效
     */
    @Override
    public Boolean checkDataSource(DatasourceConf conf) {
        String url;
        // JDBC 配置形式
        if (DsConfTypeEnum.JDBC.getType().equals(conf.getConfType())) {
            url = conf.getUrl();
        } else if (DsJdbcUrlEnum.MSSQL.getDbName().equals(conf.getDsType())) {
            // 主机形式 sql server 特殊处理
            DsJdbcUrlEnum urlEnum = DsJdbcUrlEnum.get(conf.getDsType());
            url = String.format(urlEnum.getUrl(), conf.getHost(), conf.getPort(), conf.getDsName());
        } else {
            DsJdbcUrlEnum urlEnum = DsJdbcUrlEnum.get(conf.getDsType());
            url = String.format(urlEnum.getUrl(), conf.getHost(), conf.getPort(), conf.getDsName());
        }

        conf.setUrl(url);

        try (Connection connection = DriverManager.getConnection(url, conf.getUsername(), conf.getPassword())) {
        } catch (SQLException e) {
            log.error("数据源配置 {} , 获取链接失败", conf.getName(), e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
