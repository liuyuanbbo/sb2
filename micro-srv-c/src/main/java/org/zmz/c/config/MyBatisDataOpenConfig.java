package org.zmz.c.config;

import javax.sql.DataSource;

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

@Configuration
@MapperScan(basePackages = "org.zmz.c.mapper.dataopen", sqlSessionTemplateRef = "dataopenSqlSessionTemplate")
public class MyBatisDataOpenConfig {

    @Bean(name = "dataopenSqlSessionFactory")
    @Primary
    public SqlSessionFactory dataopenSqlSessionFactory(@Qualifier("dynamicDataOpenDataSource") DataSource dataSource)
        throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mapper/dataopen/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataopenTransactionManager")
    @Primary
    public DataSourceTransactionManager dataopenTransactionManager(
        @Qualifier("dynamicDataOpenDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dataopenSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dataopenSqlSessionTemplate(
        @Qualifier("dataopenSqlSessionFactory") SqlSessionFactory dataopenSqlSessionFactory) {
        return new SqlSessionTemplate(dataopenSqlSessionFactory);
    }
}
