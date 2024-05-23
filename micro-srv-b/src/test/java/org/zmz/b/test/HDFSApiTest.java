package org.zmz.b.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HDFSApiTest {

    static class BizException extends RuntimeException {
        public BizException(String message) {
            super(message);
        }

        public BizException(Throwable cause) {
            super(cause);
        }

        public BizException() {
        }
    }

    @Test
    public void testMkDir() throws URISyntaxException {
        // 连接集群地址
        URI uri = new URI("hdfs://hadoop101:9020");
        Configuration cfg = new Configuration();
        String user = "root";
        try (FileSystem fs = FileSystem.get(uri, cfg, user)) {
            fs.mkdirs(new Path("/xiyou/sunwukong"));
        } catch (IOException | InterruptedException e) {
            throw new BizException(e);
        }
    }

    @Test
    public void testMapPutIfAbsent() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");
        String ov1 = map.putIfAbsent("aa", "bb");
        String ov2 = map.putIfAbsent("bb", "cc");
        log.info("返回的旧值1: {}", ov1);
        log.info("返回的旧值2: {}", ov2);
        log.info("testMapPutIfAbsent 获取值1: {}", map.get("aa"));
        log.info("testMapPutIfAbsent 获取值2: {}", map.get("bb"));
    }

    @Test
    public void testMapPutIfPresent() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");
        String ov1 = map.computeIfPresent("aa", (newValue, oldValue) -> {
            log.info("newValue1: {} , oldValue1: {}", newValue, oldValue);
            return newValue + "==>" + oldValue;
        });
        String ov2 = map.computeIfPresent("bb", (newValue, oldValue) -> {
            log.info("newValue2: {} , oldValue2: {}", newValue, oldValue);
            return newValue + "==>" + oldValue;
        });
        log.info("testMapPutIfPresent 返回值1: {}", ov1);
        log.info("testMapPutIfPresent 返回值2: {}", ov2);
        log.info("testMapPutIfPresent 获取值1: {}", map.get("aa"));
        log.info("testMapPutIfPresent 获取值2: {}", map.get("bb"));
    }

    @Test
    public void testMapPutAbsent() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");

        String ov1 = map.computeIfAbsent("aa", newValue -> {
            log.info("testMapPutAbsent newValue1: {}", newValue);
            return newValue + "OOO";
        });
        String ov2 = map.computeIfAbsent("bb", newValue -> {
            log.info("testMapPutAbsent newValue2: {}", newValue);
            return newValue + "GGG";
        });
        log.info("testMapPutAbsent 返回值1: {}", ov1);
        log.info("testMapPutAbsent 返回值2: {}", ov2);
        log.info("testMapPutAbsent 获取值1: {}", map.get("aa"));
        log.info("testMapPutAbsent 获取值2: {}", map.get("bb"));
    }

    @Test
    public void testMapCompute() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");

        String ov1 = map.compute("aa", (newValue, oldValue) -> {
            log.info("testMapCompute newValue1: {} , testMapCompute oldValue1: {}", newValue, oldValue);
            return newValue + "==>" + oldValue;
        });

        String ov2 = map.compute("bb", (newValue, oldValue) -> {
            log.info("testMapCompute newValue2: {} , testMapCompute oldValue2: {}", newValue, oldValue);
            return newValue + "==>" + oldValue;
        });

        log.info("testMapCompute 返回值1: {}", ov1);
        log.info("testMapCompute 返回值2: {}", ov2);
        log.info("testMapCompute 获取值1: {}", map.get("aa"));
        log.info("testMapCompute 获取值2: {}", map.get("bb"));
    }
}
