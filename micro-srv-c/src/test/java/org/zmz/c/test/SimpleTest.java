package org.zmz.c.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void t5() {
        //String s1 = "123,456,789";
        //String s2 = "456,789,123";
        //String s1 = "你好,我好,大家好";
        //String s2 = "我好,大家好,你好";
        //String s1 = "你好";
        //String s2 = "你好";

        String s1 = null;
        String s2 = "hello";
        if (isContains(s1, s2)) {
            log.info("{}", "包含");
        } else {
            log.info("{}", "不包含");
        }
    }

    private static boolean isContains(String str1, String str2) {
        List<String> items1 = StrUtil.split(str1, ",");
        List<String> items2 = StrUtil.split(str2, ",");
        return CollUtil.containsAll(items1, items2);
    }

    @Test
    public void t6() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0L);

        Object code = map.get("code");
        if ("0".equals(code) || Integer.valueOf(0).equals(code)) {
            log.info("成功");
        }
    }

}
