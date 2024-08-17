package org.zmz.d.test.mapper.code01;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.code01.JobsMapper;
import org.zmz.d.pojo.code01.Jobs;

import java.util.List;

@SpringBootTest
@Slf4j

public class MapperTest {

    @Autowired
    JobsMapper jobsMapper;

    @Test
    public void t1() {
        List<Jobs> jobs = jobsMapper.selectList(null);
        for (Jobs job : jobs) {
            log.info("{}", ReflectionToStringBuilder.toString(job, ToStringStyle.MULTI_LINE_STYLE));
        }
    }
}
