package org.zmz.c.test.obj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Slf4j
public class ObjTest {
    @Test
    public void tt1() {
        List<SimpleVo> list_1 = List.of(
                new SimpleVo(1L, 1L, "table_src_1", 11L, 11L, "table_tgt_1"),
                new SimpleVo(2L, 2L, "table_src_2", 22L, 22L, "table_tgt_2")
        );

        List<SimpleVo> list_2 = List.of(
                new SimpleVo(3L, 3L, "table_src_3", 33L, 33L, "table_tgt_3"),
                new SimpleVo(4L, 4L, "table_src_4", 44L, 44L, "table_tgt_4")
        );

        List<SimpleVo> list_3 = List.of(
                new SimpleVo(5L, 1L, "table_src_1", 55L, 11L, "table_tgt_1"),
                new SimpleVo(6L, 2L, "table_src_2", 66L, 22L, "table_tgt_2")
        );

        boolean b = list_1.equals(list_2);
        boolean c = list_1.equals(list_3);
        log.info("list_1 是否等于 list_2 {}", b);
        log.info("list_1 是否等于 list_3 {}", c);
    }

    @Test
    public void tt2() {
        List<OrgDimension> list = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> list.add(new OrgDimension(String.valueOf(i))));

        list.sort(
                Comparator.comparingInt((OrgDimension e) -> Integer.parseInt(e.getOrgLevel()))
        );

        list.forEach(e -> log.info("orgLevel: {}", e.getOrgLevel()));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class OrgDimension {
        private String orgLevel;

        public OrgDimension(String orgLevel) {
            this.orgLevel = orgLevel;
        }
    }

    @Test
    public void tt3() {
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("3", "CC");
        map.put("2", "BB");
        map.put("1", "AA");
        map.put("4", "DD");

        SortedMap<String, Object> sortMap = map.headMap("3");
        String firstKey = sortMap.firstKey();
        String lastKey = sortMap.lastKey();

        log.info("{} {}", firstKey, lastKey);
    }

    @Test
    public void tt4() {
        boolean b = true;
        List<String> list = List.of("A");
        b = b && list.contains("B");

        log.info("{}", b);
    }

    @Test
    public void tt5() {
        boolean b = "O".equalsIgnoreCase("0");
        boolean b2 = "O".equalsIgnoreCase("o");
        log.info("{}", b);
        log.info("{}", b2);
    }

}
