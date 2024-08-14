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
@MapperScan(basePackages = "org.zmz.a.mapper.pgsql.hrdb",
        sqlSessionTemplateRef = "hrdbPgsqlSqlSessionTemplate")
public class MyBatisPgSQLHrDBConfig {
    @Resource
    private DataSource hrdbPgsqlDataSource;

    @Bean(name = "hrdbPgsqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory hrdbPgsqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(hrdbPgsqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pgsql/hrdb/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "hrdbPgsqlTransactionManager")
    @Primary
    public DataSourceTransactionManager hrdbPgsqlTransactionManager() {
        return new DataSourceTransactionManager(hrdbPgsqlDataSource);
    }

    @Bean(name = "hrdbPgsqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate hrdbPgsqlSqlSessionTemplate(
            @Qualifier("hrdbPgsqlSqlSessionFactory") SqlSessionFactory hrdbPgsqlSqlSessionFactory) {
        return new SqlSessionTemplate(hrdbPgsqlSqlSessionFactory);
    }

}
