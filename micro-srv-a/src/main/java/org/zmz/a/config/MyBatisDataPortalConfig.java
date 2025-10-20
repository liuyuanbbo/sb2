package org.zmz.a.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.zmz.a.mapper.dataportal", sqlSessionTemplateRef = "dataportalSqlSessionTemplate")
public class MyBatisDataPortalConfig {
    @Resource
    private DataSource dynamicDataPortalDataSource;

    @Bean(name = "dataportalSqlSessionFactory")
    @Primary
    public SqlSessionFactory dataportalSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataPortalDataSource);
        sqlSessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mapper/dataportal/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataportalTransactionManager")
    @Primary
    public DataSourceTransactionManager dataportalTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataPortalDataSource);
    }

    @Bean(name = "dataportalSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dataportalSqlSessionTemplate(
        @Qualifier("dataportalSqlSessionFactory") SqlSessionFactory dataportalSqlSessionFactory) {
        return new SqlSessionTemplate(dataportalSqlSessionFactory);
    }
}
