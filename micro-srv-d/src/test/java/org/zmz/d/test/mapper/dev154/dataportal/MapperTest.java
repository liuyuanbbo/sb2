package org.zmz.d.test.mapper.dev154.dataportal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.dev154.dataportal.StanTagMapper;
import org.zmz.d.mapper.dev154.dataportal.StanTagRelaMapper;
import org.zmz.d.pojo.dev154.dataportal.StanTag;
import org.zmz.d.pojo.dev154.dataportal.StanTagRela;

import java.util.List;

@SpringBootTest
@Slf4j
public class MapperTest {
    @Autowired
    StanTagMapper stanTagMapper;

    @Autowired
    StanTagRelaMapper stanTagRelaMapper;


    @Test
    public void t1() {
        StanTag stanTag = stanTagMapper.selectById(10L);
        log.info("{}", ReflectionToStringBuilder.toString(stanTag, ToStringStyle.MULTI_LINE_STYLE));
    }

    @Test
    public void t2() {
        List<StanTagRela> stanTagRelas = stanTagRelaMapper.selectList(null);
        log.info("{}", ReflectionToStringBuilder.toString(stanTagRelas, ToStringStyle.MULTI_LINE_STYLE));
    }

}
