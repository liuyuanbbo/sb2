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

    @Bean(name = "yoga14sMysqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.yoga14s.mysql")
    public DataSource yoga14sMysqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "yoga14sPgsqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.yoga14s.pgsql")
    public DataSource yoga14sPgsqlDatasource() {
        return DataSourceBuilder.create().build();
    }
}
