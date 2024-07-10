package org.zmz.c.test.dataset.nodb;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DataSetUtilTest {
    public static String getSubStr(String str, String split, int... startEnd) {
        if (StrUtil.isEmpty(str)) {
            return "";
        }
        // 排序
        List<String> strList = Stream.of(str.split(split)).collect(Collectors.toList());
        int start = startEnd[0];
        int end = strList.size();
        if (startEnd.length > 1) {
            end = startEnd[1];
        }
        return StringUtils.join(strList.subList(start, end), split);
    }

    @Test
    public void t1() {
        String str1 = "n,1,2";
        String split = ",";
        int n1 = 2;

        String s1 = getSubStr(str1, split, n1);
        log.info("{}", s1);
    }
}
