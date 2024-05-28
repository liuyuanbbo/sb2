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
}