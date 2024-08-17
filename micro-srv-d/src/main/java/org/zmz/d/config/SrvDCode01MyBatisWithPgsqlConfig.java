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
@MapperScan(basePackages = "org.zmz.d.mapper.code01.pgsql",
        sqlSessionTemplateRef = "code01PgsqlSqlSessionTemplate")
public class SrvDCode01MyBatisWithPgsqlConfig {
    @Resource
    private DataSource code01PgsqlDatasource;

    @Bean(name = "code01PgsqlSqlSessionFactory")
    public SqlSessionFactory code01PgsqlSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(code01PgsqlDatasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/code01/pgsql/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "code01PgsqlTransactionManager")
    public DataSourceTransactionManager code01PgsqlTransactionManager() {
        return new DataSourceTransactionManager(code01PgsqlDatasource);
    }

    @Bean(name = "code01PgsqlSqlSessionTemplate")
    public SqlSessionTemplate code01PgsqlSqlSessionTemplate(
            @Qualifier("code01PgsqlSqlSessionFactory") SqlSessionFactory code01PgsqlSqlSessionFactory) {
        return new SqlSessionTemplate(code01PgsqlSqlSessionFactory);
    }
}
