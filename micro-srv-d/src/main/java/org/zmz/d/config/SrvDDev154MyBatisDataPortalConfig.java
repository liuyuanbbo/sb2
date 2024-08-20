package org.zmz.d.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Zmz
 */
@Configuration
@MapperScan(basePackages = {"org.zmz.d.mapper.dev154.dataportal", "org.zmz.d.mapper.dev154.dataopen"},
        sqlSessionTemplateRef = "dev154DataPortalSqlSessionTemplate")
public class SrvDDev154MyBatisDataPortalConfig {
    @Resource
    private DataSource dev154DataPortalMysqlDataSource;

    @Bean(name = "dev154DataPortalSqlSessionFactory")
    public SqlSessionFactory dev154DataPortalSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dev154DataPortalMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/dev154/data*/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dev154DataPortalTransactionManager")
    public DataSourceTransactionManager dev154DataPortalTransactionManager() {
        return new DataSourceTransactionManager(dev154DataPortalMysqlDataSource);
    }

    @Bean(name = "dev154DataPortalSqlSessionTemplate")
    public SqlSessionTemplate dev154DataPortalSqlSessionTemplate(
            @Qualifier("dev154DataPortalSqlSessionFactory") SqlSessionFactory dev154DataPortalSqlSessionFactory) {
        return new SqlSessionTemplate(dev154DataPortalSqlSessionFactory);
    }
}
