package com.hyt.publish.config.core.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MybatisConfig
 * Author:   JiangXincan
 * Date:     2018-5-1 13:29
 * Description: mybatis配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Configuration
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.hyt.publish.entity.*.**");
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath*:mapper/**/*.xml"));
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("com.hyt.publish.mapper.*.**");
        Properties properties = new Properties();
        properties.setProperty("mappers","com.hyt.publish.config.common.universal.IBaseMapper");
        properties.setProperty("notEmpty","false");
        properties.setProperty("IDENTITY","SELECT REPLACE(UUID(),''-'','''')");
        properties.setProperty("ORDER","BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
