package com.marvin.apollo.common.dal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @author hufeng
 * @version DataSourceConfig.java, v 0.1 2019-01-08 00:34 Exp $
 */
@Configuration
@MapperScan("com.marvin.apollo.common.dal.mybatis")
public class DataSourceConfig {

    @Value("classpath:schema.sql")
    private Resource schemaScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(schemaScript);
        databasePopulator.setSqlScriptEncoding("UTF-8");
        return databasePopulator;
    }

}
