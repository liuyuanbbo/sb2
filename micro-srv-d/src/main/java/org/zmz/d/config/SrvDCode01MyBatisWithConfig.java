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
@MapperScan(basePackages = "org.zmz.d.mapper.code01",
        sqlSessionTemplateRef = "code01SqlSessionTemplate")
public class SrvDCode01MyBatisWithConfig {
    @Resource
    private DataSource code01Datasource;

    @Bean(name = "code01SqlSessionFactory")
    public SqlSessionFactory code01SqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(code01Datasource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/code01//*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "code01TransactionManager")
    public DataSourceTransactionManager code01TransactionManager() {
        return new DataSourceTransactionManager(code01Datasource);
    }

    @Bean(name = "code01SqlSessionTemplate")
    public SqlSessionTemplate code01SqlSessionTemplate(
            @Qualifier("code01SqlSessionFactory") SqlSessionFactory code01SqlSessionFactory) {
        return new SqlSessionTemplate(code01SqlSessionFactory);
    }
}
