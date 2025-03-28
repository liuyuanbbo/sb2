package org.zmz.c.test.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class JsonObjectTest {

    public static long chineseToLong(String chinese) {
        StringBuilder unicodeString = new StringBuilder();
        for (char c : chinese.toCharArray()) {
            // 将每个字符转换为其 Unicode 编码
            unicodeString.append((int) c);
        }
        try {
            // 将 Unicode 编码的字符串转换为 long 型数字
            return Long.parseLong(unicodeString.toString());
        }
        catch (NumberFormatException e) {
            System.out.println("转换失败: " + e.getMessage());
            return -1;
        }
    }

    @Test
    public void t1() {
        String s = "{}";
        Map<String, Object> map = JSONObject.parseObject(s, new TypeReference<>() {
        });
        log.info("{}", JSONObject.toJSONString(map));
    }

    @Test
    public void t2() {
        String chineseText = "李梦飞";
        long result = chineseToLong(chineseText);
        System.out.println("转换结果: " + result);
        System.out.println("================");
        System.out.println(chineseText.hashCode());
    }
}
