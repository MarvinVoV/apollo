package com.apollo.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author hufeng
 * @version DataSourceConfig.java, v 0.1 2019-01-08 00:34 Exp $
 */
@Configuration
@MapperScan("com.apollo.dao.mapper")
public class DataSourceConfig {

}
