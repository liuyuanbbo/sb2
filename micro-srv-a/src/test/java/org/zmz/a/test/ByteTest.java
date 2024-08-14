package org.zmz.a.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ByteTest {
    @Test
    public void testByteMaxAndMin() {
        System.out.println(Byte.MAX_VALUE);
        byte b = 3;
        int i = b << 7;
        System.out.println(i);
    }

    @Test
    public void testListSize() {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);

        log.info("list size: {}", list.size());
    }
}
