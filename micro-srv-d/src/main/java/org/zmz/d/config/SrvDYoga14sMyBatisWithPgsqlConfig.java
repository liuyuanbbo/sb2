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
@MapperScan(basePackages = "org.zmz.d.mapper.yoga14s.pgsql",
        sqlSessionTemplateRef = "yoga14sPgsqlSqlSessionTemplate")
public class SrvDYoga14sMyBatisWithPgsqlConfig {
    @Resource
    private DataSource yoga14sPgsqlDatasource;

    @Bean(name = "yoga14sPgsqlSqlSessionFactory")
    public SqlSessionFactory yoga14sPgsqlSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(yoga14sPgsqlDatasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/yoga14s/pgsql/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "yoga14sPgsqlTransactionManager")
    public DataSourceTransactionManager yoga14sPgsqlTransactionManager() {
        return new DataSourceTransactionManager(yoga14sPgsqlDatasource);
    }

    @Bean(name = "yoga14sPgsqlSqlSessionTemplate")
    public SqlSessionTemplate yoga14sPgsqlSqlSessionTemplate(
            @Qualifier("yoga14sPgsqlSqlSessionFactory") SqlSessionFactory yoga14sPgsqlSqlSessionFactory) {
        return new SqlSessionTemplate(yoga14sPgsqlSqlSessionFactory);
    }
}
