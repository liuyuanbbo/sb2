package org.zmz.d.test.mapper.dev154.dataopen;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zmz.d.mapper.dev154.dataopen.DimIndexInfoMapper;

import lombok.extern.slf4j.Slf4j;
import org.zmz.d.mapper.dev154.dataopen.ProIndexMapper;

import java.util.Collection;
import java.util.Set;

@Slf4j
@SpringBootTest
public class MapperTest {

    @Autowired
    DimIndexInfoMapper dimIndexInfoMapper;

    @Autowired
    ProIndexMapper proIndexMapper;

    @Test
    public void t1() {
        Set<Long> dimIds = dimIndexInfoMapper.selectDimIndexIds();
        log.info("dimIds:{}", dimIds.size());

        Set<Long> proIds = proIndexMapper.selectProIndexIds();
        log.info("proIds:{}", proIds.size());

        if (CollectionUtils.containsAll(dimIds, proIds)) {
            log.info("派生指标全部包含原子指标");
        }

        Collection<Long> subtractIds1 = CollectionUtils.subtract(dimIds, proIds);

        Collection<Long> subtractIds2 = CollectionUtils.subtract(proIds, dimIds);

        log.info("{}", subtractIds1);
        log.info("{}", subtractIds2);

        log.info("交集: {}", CollectionUtils.union(dimIds, proIds));
    }
}
