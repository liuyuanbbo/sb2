package org.zmz.c.test.apache.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class ObjectUtilTest {
    @Test
    public void t1() {
        boolean b = ObjectUtils.isNotEmpty(null);
        log.info("{}", b);
    }
}
