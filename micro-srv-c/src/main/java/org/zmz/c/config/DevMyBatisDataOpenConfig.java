package org.zmz.c.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.zmz.c.mapper.dataopen",
        sqlSessionTemplateRef = "devDataOpenSqlSessionTemplate")
public class DevMyBatisDataOpenConfig {
    @Resource
    private DataSource devDataOpenMysqlDataSource;

    @Bean(name = "devDataOpenSqlSessionFactory")
    @Primary
    public SqlSessionFactory devDataOpenSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(devDataOpenMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dataopen/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "devDataOpenTransactionManager")
    @Primary
    public DataSourceTransactionManager devDataOpenTransactionManager() {
        return new DataSourceTransactionManager(devDataOpenMysqlDataSource);
    }

    @Bean(name = "devDataOpenSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate devDataOpenSqlSessionTemplate(
            @Qualifier("devDataOpenSqlSessionFactory") SqlSessionFactory devDataOpenSqlSessionFactory) {
        return new SqlSessionTemplate(devDataOpenSqlSessionFactory);
    }
}
