package org.zmz.c.test.obj;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
