package org.zmz.c.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ReflectTest {
    @Test
    public void t1() {
        DimIndex dimIndex = new DimIndex();
        dimIndex.setDimIndexId("1");
        dimIndex.setDimIndexName("1号");
        dimIndex.setFieldCode("1");

        log.info("{}", dimIndex);
        List<String> fieldCodes = List.of("dimIndexId", "dimIndexName", "fieldCode");
        for (String fieldCode : fieldCodes) {
            String methodName = "get" + upper(fieldCode);
            Method method = ReflectionUtils.findMethod(DimIndex.class, methodName);
            if (method != null) {
                method.setAccessible(true);
                Object o = ReflectionUtils.invokeMethod(method, dimIndex);
                System.out.println(o);
            }
        }
    }

    @Test
    public void t2() {
        DimIndex dimIndex = new DimIndex();
        dimIndex.setDimIndexId("1");
        dimIndex.setDimIndexName("1号");
        dimIndex.setFieldCode("101");
        dimIndex.setISort(1001);
        dimIndex.setGroupId(10001L);

        List<String> fieldCodes = List.of("dimIndexId", "dimIndexName", "fieldCode", "iSort", "groupId");

        for (String fieldCode : fieldCodes) {
            Object o = reflectGetterInvoke(dimIndex, fieldCode);
            log.info("{}", o);
        }
    }

    @Test
    public void t3() {
        Pattern PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = PATTERN.matcher("2023-03-04 19:53:12");
        System.out.println(matcher.find());
    }

    @Test
    public void t4() throws IOException {
        Path path1 = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\stan_template_field_dim.txt");
        Path path1_sort = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\stan_template_field_sort_dim.txt");

        Path path2 = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\pro_index.txt");
        Path path2_sort = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\pro_index_sort.txt");

        Path path3 = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\dim_index.txt");
        Path path3_sort = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\dim_index_sort.txt");

        Path path4 = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\stan_template_field_pro.txt");
        Path path4_sort = Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\main\\resources\\txt\\stan_template_field_sort_pro.txt");


        List<String> stanTemplateFieldsDim = Files.readAllLines(path1);
        Map<String, String> map1 = new TreeMap<>();
        stanTemplateFieldsDim.forEach(e -> map1.put(e, "1"));
        Files.writeString(path1_sort, toLine(map1.keySet()), StandardOpenOption.CREATE);

        List<String> proIndexFields = Files.readAllLines(path2);
        Map<String, String> map2 = new TreeMap<>();
        proIndexFields.forEach(e -> map2.put(e, "1"));
        Files.writeString(path2_sort, toLine(map2.keySet()), StandardOpenOption.CREATE);

        List<String> dimIndexFields = Files.readAllLines(path3);
        Map<String, String> map3 = new TreeMap<>();
        dimIndexFields.forEach(e -> map3.put(e, "1"));
        Files.writeString(path3_sort, toLine(map3.keySet()), StandardOpenOption.CREATE);

        List<String> stanTemplateFieldsPro = Files.readAllLines(path4);
        Map<String, String> map4 = new TreeMap<>();
        stanTemplateFieldsPro.forEach(e -> map4.put(e, "1"));
        Files.writeString(path4_sort, toLine(map4.keySet()), StandardOpenOption.CREATE);
    }

    private static String toLine(Collection<String> coll) {
        StringBuilder sb = new StringBuilder();
        for (String s : coll) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    public Object reflectGetterInvoke(Object o, String fieldName) {
        Class<?> clz = o.getClass();
        Map<String, Class<?>> map = new HashMap<>();
        for (Field field : clz.getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> clzType = field.getType();
            String name = field.getName();
            map.put(name, clzType);
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            MethodHandle methodHandle = lookup.findGetter(clz, fieldName, map.get(fieldName));
            return methodHandle.invoke(o);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String upper(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    @Data
    static class DimIndex {
        private String dimIndexId;
        private String dimIndexName;
        private String fieldCode;
        private Integer iSort;
        private Long groupId;
    }

    @Data
    static class CodeName {
        private String fieldCode;
    }
}
