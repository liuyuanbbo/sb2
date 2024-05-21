package org.zmz.security.test;

import org.junit.jupiter.api.Test;

public class ByteTest {
    @Test
    public void testByteMaxAndMin(){
        System.out.println(Byte.MAX_VALUE);
        byte b=3;
        int i = b << 7;
        System.out.println(i);
    }
}
