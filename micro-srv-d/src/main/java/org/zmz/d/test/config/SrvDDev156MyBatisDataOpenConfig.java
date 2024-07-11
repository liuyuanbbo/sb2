package org.zmz.d.test.config;

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

@Configuration
@MapperScan(basePackages = "org.zmz.d.test.mapper.dev156.dataopen",
        sqlSessionTemplateRef = "dev156DataOpenSqlSessionTemplate")
public class SrvDDev156MyBatisDataOpenConfig {
    @Resource
    private DataSource dev156DataOpenMysqlDataSource;

    @Bean(name = "dev156DataOpenSqlSessionFactory")
    public SqlSessionFactory dev156DataOpenSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dev156DataOpenMysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dev156/dataopen/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dev156DataOpenTransactionManager")
    public DataSourceTransactionManager dev156DataOpenTransactionManager() {
        return new DataSourceTransactionManager(dev156DataOpenMysqlDataSource);
    }

    @Bean(name = "dev156DataOpenSqlSessionTemplate")
    public SqlSessionTemplate dev156DataOpenSqlSessionTemplate(
            @Qualifier("dev156DataOpenSqlSessionFactory") SqlSessionFactory dev156DataOpenSqlSessionFactory) {
        return new SqlSessionTemplate(dev156DataOpenSqlSessionFactory);
    }
}
