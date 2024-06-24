package org.zmz.c.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class RegexTest {
    @Test
    public void t1() {
        boolean b1 = NumberUtils.isCreatable("123");
        boolean b2 = NumberUtils.isCreatable("1.331");
        boolean b3 = NumberUtils.isCreatable("71991L");
        boolean b4 = NumberUtils.isCreatable("");
        boolean b5 = NumberUtils.isCreatable("e1");

        log.info("{} {} {} {} {}", b1, b2, b3, b4, b5);
    }

    @Test
    public void t3() {
        String sourceTemplate = "hello ${A1} world ${B2} ni ${C3} hao ${D4} welcome ${E5} to ${F6} china ${G7}";

        Map<String, Object> map = new HashMap<>();
        map.put("A1", "a");
        map.put("B2", "b");
        map.put("C3", "c");
        map.put("D4", "d");
        map.put("E5", "e");
        map.put("F6", "f");
        map.put("G7", "g");

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            Object value = entry.getValue();
            if (sourceTemplate.contains(key) && value != null) {
                sourceTemplate = sourceTemplate.replace(key, String.valueOf(value));
            }
        }

        log.info("{}", sourceTemplate);
    }

    // wKg/LXVU+Ml9oJlzTTSFpw==
    @Test
    public void t4() {
        String json = """
                {
                   "alarmContent": "您创建的客户群:标签定制周期客户群,客户群id:3248,即将于2024-05-10 21:45:03到达发布失效日期,请及时进行处理！",
                   "alarmStatus": "1",
                   "objDataId": "10010",
                   "objType": "TAR_GRP",
                   "receiveAddress": "1380013805",
                   "receiver": 20119,
                   "receiverName": "tele_v7",
                   "receiveType": "MSN",
                   "targetId": "8689",
                   "targetName": "客户群续期",
                   "typeId": "10011"
                }
                """;

        LinkedHashMap<String, Object> linkMap = JSON.parseObject(json, new TypeReference<>() {
        });

        String jsonString = JSON.toJSONString(linkMap);
        log.info("{}", jsonString);

    }
}
