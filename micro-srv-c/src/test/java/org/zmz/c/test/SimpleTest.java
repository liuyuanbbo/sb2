package org.zmz.c.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class SimpleTest {
    @Test
    public void t1() {
        //1.69 - 1.12 = 0.57
        String s = "1101";
        String res = String.format("/ext/smart/proIndexList/proIndexDetail?indexId=%s&viewType=popupView", s);

        log.info("{}", res);

    }

    @Test
    public void t2() {
        log.info("{}", "0".equals(0));
        log.info("{}", Integer.valueOf(0).equals(0));
    }

    @Test
    public void t3() {
        String s = null;
        try {
            s = URLEncoder.encode("用户不存在", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码", e);
        }
        log.info("{}", s);
    }

    @Test
    public void t4() {
        Long indexId = 1001L;
        String pageUrl = String.format("/ext/smart/atomicIndicator/detail?indexId=%s&viewType=popupView", indexId);
        log.info("{}", pageUrl);
    }
}
