package org.zmz.d.test.mapper.yoga14s.mysql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.yoga14s.mysql.SequenceMapper;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    SequenceMapper yoga14sMysqlSequenceMapper;

    @Test
    public void t1() {
        Long seq = yoga14sMysqlSequenceMapper.nextVal("seq_stan_tag");
        log.info("{}", seq);
    }
}
