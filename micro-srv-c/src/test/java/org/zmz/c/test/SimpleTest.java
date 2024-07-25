package org.zmz.c.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SimpleTest {
    @Test
    public void t1() {
        //1.69 - 1.12 = 0.57
        String s = "1101";
        String res = String.format("/ext/smart/proIndexList/proIndexDetail?indexId=%s&viewType=popupView", s);

        log.info("{}", res);

    }

    @Test
    public void t2() {
        log.info("{}", "0".equals(0));
        log.info("{}", Integer.valueOf(0).equals(0));
    }

    @Test
    public void t3() {
        String s = null;
        s = URLEncoder.encode("用户不存在", StandardCharsets.UTF_8);
        log.info("{}", s);
    }

    @Test
    public void t4() {
        Long indexId = 1001L;
        String pageUrl = String.format("/ext/smart/atomicIndicator/detail?indexId=%s&viewType=popupView", indexId);
        log.info("{}", pageUrl);
    }

    @Test
    public void t5() {
        //String s1 = "123,456,789";
        //String s2 = "456,789,123";
        //String s1 = "你好,我好,大家好";
        //String s2 = "我好,大家好,你好";
        //String s1 = "你好";
        //String s2 = "你好";

        String s1 = null;
        String s2 = "hello";
        if (isContains(s1, s2)) {
            log.info("{}", "包含");
        } else {
            log.info("{}", "不包含");
        }
    }

    private static boolean isContains(String str1, String str2) {
        List<String> items1 = StrUtil.split(str1, ",");
        List<String> items2 = StrUtil.split(str2, ",");
        return CollUtil.containsAll(items1, items2);
    }

    @Test
    public void t6() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0L);

        Object code = map.get("code");
        if ("0".equals(code) || Integer.valueOf(0).equals(code)) {
            log.info("成功");
        }
    }

    @Test
    public void t7() {
        String json = """
                {"success":true,"code":0,"msg":"http://135.32.120.118/mydiskdx/applyDownload.html#nodeIds=2c9a31a88f22c248018fec50a95a0403&token=97583a6c69dc2512640e973f1a55697a","data":null}
                """;
        HashMap<?, ?> map = JSONUtil.toBean(json, HashMap.class);
        log.info("{}", map);
        String code = getStringValue(map, "code");
        if ("0".equals(code)) {
            log.info("成功");
        }
        log.info("{} -- {}", code, code.getClass());
    }

    public static String getStringValue(Map<?, ?> paramMap, String key) {
        Object value = paramMap.get(key);
        if (value == null) {
            return "";
        }

        if (value instanceof String) {
            return (String) value;
        } else {
            return value.toString();
        }

    }

    @Test
    public void t8() {
        String json = """
                {"success":true,"code":0,"msg":"http://135.32.120.118/mydiskdx/applyDownload.html#nodeIds=2c9a31a88f22c248018fec50a95a0403&token=97583a6c69dc2512640e973f1a55697a","data":null}
                """;
        Map<?, ?> map = fromJson(json, Map.class);
        log.info("{}", map);
        String success = getStringValue(map, "success");
        String code = getStringValue(map, "code");
        if ("true".equals(success)) {
            log.info("成功");
        }
        if ("0".equals(code)) {
            log.info("code 是 0 ");
        }
        log.info("{} -- {}", code, code.getClass());
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T) fromJson(json, clazz, null);
    }

    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
        if (StrUtil.isBlank(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (StrUtil.isBlank(datePattern)) {
            datePattern = "yyyy-MM-dd HH:mm:ss";
        }
        builder.setDateFormat(datePattern);
        Gson gson = builder.create();
        try {
            return (T) gson.fromJson(json, clazz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void t9() {
        Long id = null;
        String s = String.valueOf(id);


        Map<String, String> map = new LinkedHashMap<>();
        map.put("aa", null);

        String aa = map.get("aa");
        log.info("{}", aa);

//        String did = null;
//        Long l = Long.valueOf(did);
//        log.info("{}", l);

        log.info("=============================================");

        Long[] list = new Long[]{1L, 2L, null, 3L};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.length; i++) {
            Long obj = list[i];
            if (obj != null) {
                sb.append(obj);
                if (i != list.length - 1) {
                    sb.append(",");
                }
            }
        }

        log.info("{}", sb);

        log.info("=============================================");
    }

    @Test
    public void t10() {
        String sql = "{123}{1213131}{213131g{eqe}}";
        Pattern pattern = Pattern.compile("\\{([^$][^}]*)\\}");
        Matcher matcher = pattern.matcher(sql);

        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String s = matcher.group(1);
            list.add(s);
        }

        log.info("{}", list);
    }

    @Test
    public void t11() {
        String step = "1";
        Map<String, Object> map = Maps.newHashMap("k1", "v1");
        String jsonString = JSON.toJSONString(map);
        log.info("{} {}", step, jsonString);
    }


    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class $_A {
        private String id;
        private String name;

        public $_A(String id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    @ToString
    static class $_B {
        List<$_A> as;
    }

    @Getter
    @Setter
    @ToString
    static class $_C {
        $_B b;
    }

    @Test
    public void t12() {
        $_C c = new $_C();

        $_B b = new $_B();

        List<$_A> as = List.of(new $_A("1"), new $_A("2"), new $_A("3"));

        b.setAs(as);

        c.setB(b);

        // 更改 as 里面的属性
        as.forEach(e -> e.setName(e.getId() + "_name_" + e.getId()));

        log.info("t12()");
        log.info("{}", c);
    }


    String getPartitionName(Map<String, String> partitionMap) {
        Object ptKey = partitionMap.keySet().toArray()[0];
        Object ptValue = partitionMap.get(ptKey);
        return ptKey.toString().toLowerCase() + "_" + ptValue.toString().toLowerCase();
    }

    String getPartitionNameV2(Map<String, String> partitionMap) {
        for (Map.Entry<String, String> entry : partitionMap.entrySet()) {
            return entry.getKey().toLowerCase() + "_" + entry.getValue().toLowerCase();
        }
        return "No Element";
    }

    @Test
    public void t13() {
        Map<String, String> partitionMap = new HashMap<>();
        partitionMap.put("MONTH_20240716", "1");
        partitionMap.put("MONTH_20240717", "2");
        partitionMap.put("MONTH_20240718", "3");

        String partitionName = getPartitionNameV2(partitionMap);
        log.info("{}", partitionName);
    }

    @Test
    public void t14() {
        String s1 = "你好";
        String s2 = s1;

        s1 = "hello world";

        log.info("{}", s1);
        log.info("{}", s2);
    }

}
