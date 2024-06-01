package org.zmz.c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SimpleTest {
    @Test
    public void t1() {
        //1.69 - 1.12 = 0.57
        String s = "1101";
        String res = String.format("/ext/smart/proIndexList/proIndexDetail?indexId=%s&viewType=popupView", s);

        log.info("{}", res);

    }
}
