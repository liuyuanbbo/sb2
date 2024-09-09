package org.zmz.c.test.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void t1() {
        redisTemplate.opsForHash().put("User:Counter", "1024", "1");
        Object o = redisTemplate.opsForHash().get("User:Counter", "1024");
        log.info("{}", o);
        stringRedisTemplate.opsForHash().put("User:Counter", "1024", "2");
        Object os = stringRedisTemplate.opsForHash().get("User:Counter", "1024");
        log.info("{}", os);
    }

}
