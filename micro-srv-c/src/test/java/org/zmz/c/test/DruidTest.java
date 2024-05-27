package org.zmz.c.test;

import com.alibaba.druid.util.JdbcUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DruidTest {

    public static final Logger LOG = LoggerFactory.getLogger(DruidTest.class);

    @Autowired
    DataSource localMariaDataSource;

    @Autowired
    DataSource localPgsqlDataSource;

    @Test
    public void t1() {
        String sql = "select * from t_data_center_storage";
        try {
            List<Map<String, Object>> result = JdbcUtils.executeQuery(localPgsqlDataSource, sql);
            for (Map<String, Object> map : result) {
                LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                map.forEach((k, v) -> LOG.info("{} = {}", k, v));
                LOG.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }
        } catch (SQLException e) {
            LOG.error("查询出错", e);
        }
    }
}
