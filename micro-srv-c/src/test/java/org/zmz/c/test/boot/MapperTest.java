package org.zmz.c.test.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.c.mapper.dataopen.DimIndexMapper;
import org.zmz.c.mapper.dataopen.ProIndexMapper;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    ProIndexMapper proIndexMapper;

    @Autowired
    DimIndexMapper dimIndexMapper;

    @Test
    public void testContext() {
        LocalDateTime proIndexNow = proIndexMapper.selectNow();
        LocalDateTime dimIndexNow = dimIndexMapper.selectNow();

        log.info("{}", proIndexNow);
        log.info("{}", dimIndexNow);
    }

}
