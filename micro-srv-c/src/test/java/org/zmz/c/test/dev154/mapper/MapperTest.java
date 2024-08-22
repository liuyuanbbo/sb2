package org.zmz.c.test.dev154.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.c.mapper.dataopen.AppSqlColumnMapper;
import org.zmz.c.mapper.dataopen.AppSqlConditionMapper;
import org.zmz.c.mapper.dataopen.AppSqlMapper;
import org.zmz.c.mapper.dataopen.DimIndexInfoMapper;
import org.zmz.c.mapper.dataopen.ObjColumnRelaMapper;
import org.zmz.c.mapper.dataopen.ObjKeyColumnRelaMapper;
import org.zmz.c.mapper.dataopen.ObjKeyTableRelaMapper;
import org.zmz.c.mapper.dataopen.ObjTableRelaMapper;
import org.zmz.c.mapper.dataopen.ProIndexMapper;
import org.zmz.c.pojo.dataopen.AppSql;
import org.zmz.c.pojo.dataopen.AppSqlColumn;

import java.util.List;

@Slf4j
@SpringBootTest
public class MapperTest {

    @Autowired
    private AppSqlMapper appSqlMapper;

    @Autowired
    private AppSqlColumnMapper appSqlColumnMapper;

    @Autowired
    AppSqlConditionMapper appSqlConditionMapper;

    @Autowired
    DimIndexInfoMapper dimIndexInfoMapper;

    @Autowired
    ProIndexMapper proIndexMapper;

    @Autowired
    ObjTableRelaMapper objTableRelaMapper;

    @Autowired
    ObjColumnRelaMapper objColumnRelaMapper;

    @Autowired
    ObjKeyTableRelaMapper objKeyTableRelaMapper;

    @Autowired
    ObjKeyColumnRelaMapper objKeyColumnRelaMapper;

    @Test
    public void t1() {
        log.info("{}", appSqlMapper);
        IPage<AppSql> page = Page.of(1, 2);
        List<AppSql> appSqls = appSqlMapper.selectList(page, null);
        for (AppSql appSql : appSqls) {
            log.info("{}", appSql);
        }

        log.info("=================================================================");
        AppSql appSql = appSqlMapper.selectById(869);
        log.info("{}", appSql);
    }

    @Test
    public void t2() {
        Long id = 4944L;
        AppSqlColumn appSqlColumnMp = appSqlColumnMapper.selectById(id);
        log.info(">>> {}", appSqlColumnMp);
        AppSqlColumn appSqlColumnTk = appSqlColumnMapper.selectByPrimaryKey(id);
        log.info("======== {}", appSqlColumnTk);
    }

}
