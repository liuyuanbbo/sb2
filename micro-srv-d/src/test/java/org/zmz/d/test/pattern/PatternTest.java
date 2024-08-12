package org.zmz.d.test.pattern;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PatternTest {
    @Test
    public void t1() {
        String regex = "[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z0-9]{1,7}";
        Pattern pattern = Pattern.compile(regex);

        String s1 = "中s43q11";
        String s2 = "德哈卡电话卡电";
        String s3 = "1323654";
        String s4 = "a1324";

        Matcher matcher1 = pattern.matcher(s1);
        Matcher matcher2 = pattern.matcher(s2);
        Matcher matcher3 = pattern.matcher(s3);
        Matcher matcher4 = pattern.matcher(s4);

        boolean matches1 = matcher1.matches();
        boolean matches2 = matcher2.matches();
        boolean matches3 = matcher3.matches();
        boolean matches4 = matcher4.matches();

        log.info("{}", matches1);
        log.info("{}", matches2);
        log.info("{}", matches3);
        log.info("{}", matches4);
    }
}
