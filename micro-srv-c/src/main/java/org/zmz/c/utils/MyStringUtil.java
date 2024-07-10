package org.zmz.c.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class MyStringUtil {
    private MyStringUtil() {
    }

    /**
     * 一个字符串与一组字符串匹配，获取一组字符串里匹配值最高的一条
     */
    public static String getHighMatchStr(String matchStr, Collection<String> strs) {
        Map<String, Integer> matchPart = new HashMap<>();
        for (String str : strs) {
            String retainStr = getRetainStr(matchStr, str, false);
            if (StrUtil.isEmpty(retainStr)) {
                continue;
            }
            matchPart.put(str, retainStr.split(",").length);
        }
        if (MapUtil.isEmpty(matchPart)) {
            return "";
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(matchPart.entrySet());
        list.sort((o1, o2) -> {
            // 降序排列
            return (o2.getValue() - o1.getValue());
        });
        return list.get(0).getKey();
    }

    /**
     * 取字符串交集，从头开始匹配
     */
    public static String getRetainStr(String s1, String s2, boolean startWith) {
        Set<String> set1 = new LinkedHashSet<>(Arrays.asList(s1.split(",")));
        Set<String> set2 = new LinkedHashSet<>(Arrays.asList(s2.split(",")));
        // 从头开始取交集
        if (!startWith || set1.iterator().next().equals(set2.iterator().next())) {
            set1.retainAll(set2);
            return StrUtil.join(",", set1);
        }
        return "";
    }

    /**
     * 字符串按split分组后截取部分,比如getSubStr("n,1,2", ",", 2)取第二个逗号之后的部分的字符串
     */
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

}
