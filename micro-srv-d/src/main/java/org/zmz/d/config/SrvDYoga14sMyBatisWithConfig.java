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
@MapperScan(basePackages = "org.zmz.d.mapper.yoga14s",
        sqlSessionTemplateRef = "yoga14sSqlSessionTemplate")
public class SrvDYoga14sMyBatisWithConfig {
    @Resource
    private DataSource yoga14sDataSource;

    @Bean(name = "yoga14sSqlSessionFactory")
    public SqlSessionFactory yoga14sSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(yoga14sDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/yoga14s//*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "yoga14sTransactionManager")
    public DataSourceTransactionManager yoga14sTransactionManager() {
        return new DataSourceTransactionManager(yoga14sDataSource);
    }

    @Bean(name = "yoga14sSqlSessionTemplate")
    public SqlSessionTemplate yoga14sSqlSessionTemplate(
            @Qualifier("yoga14sSqlSessionFactory") SqlSessionFactory yoga14sSqlSessionFactory) {
        return new SqlSessionTemplate(yoga14sSqlSessionFactory);
    }
}
