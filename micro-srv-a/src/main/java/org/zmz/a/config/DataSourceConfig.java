package org.zmz.a.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dynamicDataOpenDataSource")
    @ConfigurationProperties("spring.datasource.dataopen")
    public DataSource dataopenDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataPortalDataSource")
    @ConfigurationProperties("spring.datasource.dataportal")
    public DataSource dataportalDataSource() {
        return DataSourceBuilder.create().build();
    }
}