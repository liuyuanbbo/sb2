package org.zmz.c.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class RegexTest {
    @Test
    public void t1() {
        boolean b1 = NumberUtils.isCreatable("123");
        boolean b2 = NumberUtils.isCreatable("1.331");
        boolean b3 = NumberUtils.isCreatable("71991L");
        boolean b4 = NumberUtils.isCreatable("");
        boolean b5 = NumberUtils.isCreatable("e1");

        log.info("{} {} {} {} {}", b1, b2, b3, b4, b5);
    }
}
