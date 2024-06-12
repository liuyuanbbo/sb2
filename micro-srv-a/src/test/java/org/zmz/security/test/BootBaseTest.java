package org.zmz.security.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.security.mapper.dataportal.StanAuditOrderMapper;
import org.zmz.security.model.StanAuditOrder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class BootBaseTest {

    @Resource
    StanAuditOrderMapper stanAuditOrderMapper;

    @Test
    public void t1() {
        log.info("{}", stanAuditOrderMapper.selectNow());
    }

    @Test
    public void t2() {
        List<StanAuditOrder> list = stanAuditOrderMapper.simpleList();
        list.forEach(e -> log.info("{}", e));
    }

}
