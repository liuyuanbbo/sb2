package org.zmz.c.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    @GetMapping("/t2")
    public void t2(HttpServletResponse response) {
        Map<String, Object> map = of2("code", 200, "msg", "OK");
        try (PrintWriter writer = response.getWriter()) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            String jsonStr = JSONUtil.toJsonStr(map);
            writer.println(jsonStr);
            writer.flush();
        }
        catch (Exception e) {
            log.error("输出响应流出错", e);
        }
        log.info("刷出响应流成功");
    }

    @GetMapping("/")
    public R<Map<String, String>> t3() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        log.info("当前线程: {}", currentThread.getName());
        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(1);
        return R.ok(of("foo", "t3"));
    }

    @GetMapping("/t3_1")
    public R<Map<String, String>> t3_1(HttpServletRequest request) throws InterruptedException {
        String reqId = request.getHeader("reqId");
        if (StrUtil.isBlank(reqId)) {
            throw new RuntimeException("t3_1 缺少reqId");
        }
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new RuntimeException("t3_1 缺少token");
        }
        Thread currentThread = Thread.currentThread();
        log.info("reqId : {} , t3-1当前线程: {} 开始: {}", reqId, currentThread.getName(), System.currentTimeMillis());
        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(1);
        log.info("t3-1当前线程: {} 结束: {}", currentThread.getName(), System.currentTimeMillis());
        return R.ok(of("foo_t3_1", "t3_1"));
    }

    @GetMapping("/t3_2")
    public R<Map<String, String>> t3_2(HttpServletRequest request) throws InterruptedException {
        String reqId = request.getHeader("reqId");
        if (StrUtil.isBlank(reqId)) {
            throw new RuntimeException("t3_2 缺少reqId");
        }
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new RuntimeException("t3_2 缺少token");
        }
        Thread currentThread = Thread.currentThread();
        log.info("reqId : {} , t3-2 当前线程: {} 开始: {}", reqId, currentThread.getName(), System.currentTimeMillis());
        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(2);
        log.info("t3-2 当前线程: {} 结束: {}", currentThread.getName(), System.currentTimeMillis());
        return R.ok(of("foo_t3_2", "t3_2"));
    }

    @GetMapping("/t3_3")
    public R<Map<String, String>> t3_3(HttpServletRequest request) throws InterruptedException {
        String reqId = request.getHeader("reqId");
        if (StrUtil.isBlank(reqId)) {
            throw new RuntimeException("t3_3 缺少reqId");
        }
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new RuntimeException("t3_3 缺少token");
        }
        log.info("reqId : {} , t3-3 当前线程: {} 开始: {}", reqId, Thread.currentThread().getName(),
            System.currentTimeMillis());
        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(3);
        log.info("t3-3 当前线程: {} 结束: {}", Thread.currentThread().getName(), System.currentTimeMillis());
        return R.ok(of("foo_t3_3", "t3_3"));
    }

    public static <K, V> Map<K, V> of(K k, V v) {
        Map<K, V> map = new HashMap<>();
        map.put(k, v);
        return map;
    }

    public static <K, V> Map<K, V> of2(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

}
