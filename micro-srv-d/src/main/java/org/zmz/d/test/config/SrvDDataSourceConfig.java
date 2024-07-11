package org.zmz.d.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SrvDDataSourceConfig {
    @Bean(name = "dev156DataOpenMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dev156.dataopen")
    public DataSource devDataOpenMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
