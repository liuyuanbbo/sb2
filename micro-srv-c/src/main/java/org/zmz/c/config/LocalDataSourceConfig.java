package org.zmz.c.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LocalDataSourceConfig {
    @Bean(name = "localMariaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.maria.local")
    public DataSource localMariaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "localPgsqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql.local")
    public DataSource localPgsqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
