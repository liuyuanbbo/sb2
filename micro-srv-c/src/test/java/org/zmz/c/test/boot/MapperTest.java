package org.zmz.c.test.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.c.mapper.dataopen.DimIndexInfoMapper;
import org.zmz.c.mapper.dataopen.ProIndexMapper;
import org.zmz.c.pojo.dataopen.ProIndex;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    ProIndexMapper proIndexMapper;

    @Autowired
    DimIndexInfoMapper dimIndexInfoMapper;

    @Test
    public void testContext() {
        LocalDateTime proIndexNow = proIndexMapper.selectNow();
        LocalDateTime dimIndexNow = dimIndexInfoMapper.selectNow();

        log.info("{}", proIndexNow);
        log.info("{}", dimIndexNow);
    }

    @Test
    public void testList() {
        List<ProIndex> proIndexList = proIndexMapper.selectAll();
        List<ProIndex> subList = proIndexList.subList(1, 3);
        log.info("{}", subList.size());
        subList.forEach(e -> log.info("{}", e));
    }

}
