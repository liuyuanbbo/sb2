package org.zmz.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataopenDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.dataopen")
    public DataSource dataopenDataSource() {
        return DataSourceBuilder.create().build();
    }
}