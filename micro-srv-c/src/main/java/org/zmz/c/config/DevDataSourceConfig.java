package org.zmz.c.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DevDataSourceConfig {
    @Bean(name = "devDataOpenMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dev.dataopen")
    public DataSource devDataOpenMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "devDataPortalMysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.dev.dataportal")
    public DataSource devDataPortalMysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "devPgsqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql.dev")
    public DataSource devPgsqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
