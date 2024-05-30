package org.zmz.b.infra.config;

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
@MapperScan(basePackages = "org.zmz.b.infra.repository.mapper.code01.maria",
        sqlSessionTemplateRef = "code01MariaSqlSessionTemplate")
public class MyBatisCode01MariaConfig {
    @Resource
    private DataSource yoga14sMysqlDataSource;

    @Bean(name = "code01MariaSqlSessionFactory")
    @Primary
    public SqlSessionFactory code01MariaSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(yoga14sMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/code01/maria/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "code01MariaTransactionManager")
    @Primary
    public DataSourceTransactionManager code01MariaTransactionManager() {
        return new DataSourceTransactionManager(yoga14sMysqlDataSource);
    }

    @Bean(name = "code01MariaSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate code01MariaSqlSessionTemplate(
            @Qualifier("code01MariaSqlSessionFactory") SqlSessionFactory code01MariaSqlSessionFactory) {
        return new SqlSessionTemplate(code01MariaSqlSessionFactory);
    }
}
