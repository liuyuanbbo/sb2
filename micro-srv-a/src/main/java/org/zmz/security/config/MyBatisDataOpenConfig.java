package org.zmz.security.config;

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
@MapperScan(basePackages = "org.zmz.security.mapper.dataopen",
        sqlSessionTemplateRef = "dataopenSqlSessionTemplate")
public class MyBatisDataOpenConfig {
    @Resource
    private DataSource dataopenMysqlDataSource;

    @Bean(name = "dataopenSqlSessionFactory")
    @Primary
    public SqlSessionFactory dataopenSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataopenMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dataopen/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dataopenTransactionManager")
    @Primary
    public DataSourceTransactionManager dataopenTransactionManager() {
        return new DataSourceTransactionManager(dataopenMysqlDataSource);
    }

    @Bean(name = "dataopenSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate dataopenSqlSessionTemplate(
            @Qualifier("dataopenSqlSessionFactory") SqlSessionFactory dataopenSqlSessionFactory) {
        return new SqlSessionTemplate(dataopenSqlSessionFactory);
    }
}
