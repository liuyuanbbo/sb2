package org.zmz.c.test;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class AlgorithmTest {
    @Test
    public void t1() {
        Map<String, String> params = MapUtil.newHashMap();
        params.put("primaryKey", "4,3,1,5,2,6");
        params.put("keyCode", "b,c,a,e,d");

        Pair<String, String> pair = o1(params);
        /*
         expect result
         * 1 2 3 4 5
         * a d c b e
         */
        log.info("{}", pair.getLeft());
        log.info("{}", pair.getRight());
    }

    private Pair<String, String> o1(Map<?, ?> map) {
        String primaryKey = MapUtil.getStr(map, "primaryKey");
        String keyCode = MapUtil.getStr(map, "keyCode");

        List<String> valueItems = StrUtil.split(primaryKey, ",");
        List<String> codeItems = StrUtil.split(keyCode, ",");

        if (valueItems.size() != codeItems.size()) {
            throw new RuntimeException("primaryKey 与 keyCode 值的个数不对应");
        }

        Map<String, String> treeMap = new TreeMap<>();
        for (int i = 0; i < valueItems.size(); i++) {
            treeMap.put(valueItems.get(i), codeItems.get(i));
        }

        String sortPrimaryKey = StrUtil.join(",", treeMap.keySet());
        String sortKeyCode = StrUtil.join(",", treeMap.values());

        return Pair.of(sortPrimaryKey, sortKeyCode);
    }
}
