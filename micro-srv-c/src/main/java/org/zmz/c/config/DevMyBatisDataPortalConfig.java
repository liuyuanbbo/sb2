package org.zmz.c.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.zmz.c.mapper.dataportal",
        sqlSessionTemplateRef = "devDataPortalSqlSessionTemplate")
public class DevMyBatisDataPortalConfig {
    @Resource
    private DataSource devDataPortalMysqlDataSource;

    @Bean(name = "devDataPortalSqlSessionFactory")
    public SqlSessionFactory devDataPortalSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(devDataPortalMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dataportal/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "devDataPortalTransactionManager")
    public DataSourceTransactionManager devDataPortalTransactionManager() {
        return new DataSourceTransactionManager(devDataPortalMysqlDataSource);
    }

    @Bean(name = "devDataPortalSqlSessionTemplate")
    public SqlSessionTemplate devDataPortalSqlSessionTemplate(
            @Qualifier("devDataPortalSqlSessionFactory") SqlSessionFactory devDataPortalSqlSessionFactory) {
        return new SqlSessionTemplate(devDataPortalSqlSessionFactory);
    }
}
