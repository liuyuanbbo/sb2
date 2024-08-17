package org.zmz.d.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Zmz
 */
@Configuration
public class SrvDDataSourceConfig {
    @Bean(name = "dev156DataOpenMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dev156.dataopen")
    public DataSource devDataOpenMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dev154DataPortalMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dev154.dataportal")
    public DataSource dev154DataPortalMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "yoga14sDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.yoga14s")
    public DataSource yoga14sDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "code01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.code01")
    public DataSource code01DataSource() {
        return DataSourceBuilder.create().build();
    }
}
