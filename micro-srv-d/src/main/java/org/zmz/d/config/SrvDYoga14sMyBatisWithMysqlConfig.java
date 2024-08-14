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
@MapperScan(basePackages = "org.zmz.d.mapper.yoga14s.mysql",
        sqlSessionTemplateRef = "yoga14sMysqlSqlSessionTemplate")
public class SrvDYoga14sMyBatisWithMysqlConfig {
    @Resource
    private DataSource yoga14sMysqlDatasource;

    @Bean(name = "yoga14sMysqlSqlSessionFactory")
    public SqlSessionFactory yoga14sMysqlSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(yoga14sMysqlDatasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/yoga14s/mysql/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "yoga14sMysqlTransactionManager")
    public DataSourceTransactionManager yoga14sMysqlTransactionManager() {
        return new DataSourceTransactionManager(yoga14sMysqlDatasource);
    }

    @Bean(name = "yoga14sMysqlSqlSessionTemplate")
    public SqlSessionTemplate yoga14sMysqlSqlSessionTemplate(
            @Qualifier("yoga14sMysqlSqlSessionFactory") SqlSessionFactory yoga14sMysqlSqlSessionFactory) {
        return new SqlSessionTemplate(yoga14sMysqlSqlSessionFactory);
    }
}
