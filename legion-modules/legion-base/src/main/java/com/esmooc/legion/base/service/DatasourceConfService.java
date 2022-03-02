package com.esmooc.legion.base.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.esmooc.legion.base.entity.DatasourceConf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmooc.legion.core.datasource.util.DsConfTypeEnum;
import com.esmooc.legion.core.datasource.util.DsJdbcUrlEnum;
import org.jasypt.encryption.StringEncryptor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author 呆猫
 *
 * @Date: 2022/03/02/ 10:47
 * @Description: 
 */
public interface DatasourceConfService extends IService<DatasourceConf>{

    /**
     * 保存数据源并且加密
     * @param DatasourceConf
     * @return
     */
    Boolean saveDsByEnc(DatasourceConf DatasourceConf);

    /**
     * 更新数据源
     * @param DatasourceConf
     * @return
     */
    Boolean updateDsByEnc(DatasourceConf DatasourceConf);

    /**
     * 更新动态数据的数据源列表
     * @param datasourceConf
     * @return
     */
    void addDynamicDataSource(DatasourceConf datasourceConf);

    /**
     * 校验数据源配置是否有效
     * @param datasourceConf 数据源信息
     * @return 有效/无效
     */
    Boolean checkDataSource(DatasourceConf datasourceConf);

    /**
     * 通过数据源名称删除
     * @param dsId 数据源ID
     * @return
     */
    Boolean removeByDsId(Long dsId);
}
