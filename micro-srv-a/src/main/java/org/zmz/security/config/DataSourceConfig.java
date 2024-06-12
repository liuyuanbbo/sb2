package org.zmz.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataopenMariaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.dataopen")
    public DataSource dataopenMariaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataopenMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dataopen")
    public DataSource dataopenMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataportalMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dataportal")
    public DataSource dataportalMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hrdbPgsqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql.hrdb")
    public DataSource hrdbPgsqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}