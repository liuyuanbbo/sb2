package org.zmz.b.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "code01MariaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.code01.maria")
    public DataSource code01MariaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "yoga14sMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.yoga14s.mysql")
    public DataSource yoga14sMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}