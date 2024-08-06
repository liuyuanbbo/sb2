package org.zmz.c.test.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class JsonObjectTest {
    @Test
    public void t1() {
        String s = "{}";
        Map<String, Object> map = JSONObject.parseObject(s, new TypeReference<>() {
        });
        log.info("{}", JSONObject.toJSONString(map));
    }
}
