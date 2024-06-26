package org.zmz.c.test.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedissonTest {
    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedissonClient redissonSingleClient;

    // 测试 Redisson 单节点配置
    @Test
    public void t1() {
        String id = redissonSingleClient.getId();
        log.info("{}", id);
    }

}
