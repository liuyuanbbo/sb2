package org.zmz.c.test.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void t1() {
        StringRedisSerializer serializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

        String key = "datasched_alarmmessage111";
        String value = """
                {
                   "alarmContent": "派生指标CPU发烧了(cpu_shao)即将在2024-07-04进行变更，请及时评估其影响。如有必要，请在规定时间内取消订阅。",
                   "configSendTemplate": "${dataType}${dataName}(${dataCode})即将在${endTime}进行${operation}，请及时评估其影响。如有必要，请在规定时间内取消订阅。",
                   "receiveAddress": "广州",
                   "receiver": 20290,
                   "receiverName": "wKg/LXVU+Ml9oJlzTTSFpw==",
                   "sendTime": "2024-06-24 15:17:36"
                }
                """;
        byte[] k = serializer.serialize(key);
        byte[] v = jdkSerializer.serialize(value);

        if (k != null && v != null) {
            Long l = redisTemplate.opsForList().rightPush(key, v);
            log.info("{}", l);
        }
    }

    @Test
    public void t2() {
        String key = "datasched_alarmmessage";
        String value = """
                {
                   "alarmContent": "${dataType}${dataName}(${dataCode})即将在${endTime}进行${operation}，请及时评估其影响。如有必要，请在规定时间内取消订阅。",
                   "alarmStatus": "1",
                   "objDataId": "${objDataId}",
                   "objType": "${objType}",
                   "receiveType": "${receiveType}",
                   "targetId": "8689",
                   "targetName": "元数据发布影响告警",
                   "typeId": "10011"
                }
                """;
        Long l = stringRedisTemplate.opsForList().rightPush(key, value);
        log.info("{}", l);
    }

}
