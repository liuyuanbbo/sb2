package org.zmz.c.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class DataSourceCmdLineRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceCmdLineRunner.class);

    @Autowired
    private List<DataSource> dataSources;

    @Override
    public void run(String... args) throws Exception {
        LOG.info(">>>>> DataSourceCmdLineRunner Execute <<<<<<");
        LOG.info("app env : {}", System.getProperty("app.env"));
        for (DataSource dataSource : dataSources) {
            try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT VERSION()")) {

                DatabaseMetaData metaData = connection.getMetaData();
                String driverName = metaData.getDriverName();
                String driverVersion = metaData.getDriverVersion();
                String url = metaData.getURL();
                if (resultSet.next()) {
                    String mysqlVersion = resultSet.getString(1);
                    LOG.info("Connected to {}, driverName: {}, driverVersion: {} , queryVersion: {}", url, driverName,
                        driverVersion, mysqlVersion);
                }
            }
            catch (Exception e) {
                LOG.error("Failed to get dataSource Connection: {}", e.getMessage());
            }
        }
    }
}