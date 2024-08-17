package org.zmz.d.test.mapper.yoga14s;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.yoga14s.SequenceMapper;

@SpringBootTest
@Slf4j
public class MapperTest {

    @Autowired
    SequenceMapper yoga14sSequenceMapper;

    @Test
    public void t1() {
        Long seq = yoga14sSequenceMapper.nextVal("seq_stan_tag");
        log.info("{}", seq);
    }
}
