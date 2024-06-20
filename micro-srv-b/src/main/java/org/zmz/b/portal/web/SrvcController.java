package org.zmz.b.portal.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.b.biz.feign.FeignSrvC;
import org.zmz.common.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/srvc")
@Slf4j
public class SrvcController {

    @Resource
    FeignSrvC feignSrvC;

    @Resource
    ObjectMapper objectMapper;

    @Resource
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/fooT1")
    public R<?> fooT1(HttpServletResponse response) {
        response.addHeader("error", "srvB error");
        try (Response resp = feignSrvC.fooT1();
             Response.Body feignBody = resp.body()) {
            Map<String, Object> bodyMap = objectMapper.readValue(feignBody.asInputStream(), new TypeReference<>() {
            });
            log.info("feignBody bodyMap{}", bodyMap);
            return R.ok(bodyMap);
        } catch (Exception e) {
            log.error("发生异常", e);
        }
        return R.ok();
    }

    @GetMapping("/bar1")
    public R<Map<String, Object>> bar1(HttpServletResponse response) {
        String mvcResponse = response.getHeader("response");
        log.info("springmvc 请求头: {}", mvcResponse);
        try (Response resp = feignSrvC.bar1();
             Response.Body feignBody = resp.body()) {
            Map<String, Object> bodyMap = objectMapper.readValue(feignBody.asInputStream(), new TypeReference<>() {
            });
            log.info("feignBody bodyMap{}", bodyMap);
            Collection<String> feignResponse = resp.headers().get("response");
            if (feignResponse != null && !feignResponse.isEmpty()) {
                String headerBody = feignResponse.iterator().next();
                String decodeJson = URLDecoder.decode(headerBody, Charset.defaultCharset());
                log.info("{}", decodeJson);
                Map<String, Object> map = objectMapper.readValue(decodeJson, new TypeReference<>() {
                });
                return R.ok(map);
            }
        } catch (Exception e) {
            log.error("json处理错误", e);
        }
        return R.ok(null);
    }

    @GetMapping("/bar2")
    public void bar2(HttpServletResponse response) {
        try (Response resp = feignSrvC.bar2();
             PrintWriter writer = response.getWriter()) {
            Collection<String> feignResponse = resp.headers().get("externalFileSystemResponse");
            if (feignResponse != null && !feignResponse.isEmpty()) {
                String headerBody = feignResponse.iterator().next();
                String decodeJson = URLDecoder.decode(headerBody, Charset.defaultCharset());
                log.info("{}", decodeJson);
                Map<String, Object> map = objectMapper.readValue(decodeJson, new TypeReference<>() {
                });
                writer.println(map);
                //writer.flush();
            }
        } catch (Exception e) {
            log.error("json处理错误", e);
        }
    }

    @GetMapping("/foo_t3_1_2_3_sync")
    public R<Map<String, String>> foo_t3_1_2_3_sync() {
        long start = System.currentTimeMillis();
        log.info("开始: {}", start);
        log.info("远程调用 foo_t3_1 开始: {}", System.currentTimeMillis());
        R<Map<String, String>> r1 = feignSrvC.t3_1();
        log.info("远程调用 foo_t3_1 结果: {} , 结束: {}", r1, System.currentTimeMillis());
        log.info("远程调用 foo_t3_2 开始: {}", System.currentTimeMillis());
        R<Map<String, String>> r2 = feignSrvC.t3_2();
        log.info("远程调用 foo_t3_2 结果: {} , 结束: {}", r2, System.currentTimeMillis());
        log.info("远程调用 foo_t3_3 开始: {}", System.currentTimeMillis());
        R<Map<String, String>> r3 = feignSrvC.t3_3();
        log.info("远程调用 foo_t3_3 结果: {} , 结束: {}", r3, System.currentTimeMillis());
        log.info("总耗时: {}", (System.currentTimeMillis() - start) / 1000);
        Map<String, String> map1 = r1.getData();
        map1.putAll(r2.getData());
        map1.putAll(r3.getData());

        return r1;
    }

    @GetMapping("/foo_t3_1_2_3_async")
    public R<Map<String, String>> foo_t3_1_2_3_async() {
        long start = System.currentTimeMillis();
        log.info("开始: {}", start);
        R<Map<String, String>> r = R.ok();
        r.setData(new TreeMap<>());
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    log.info("远程调用 foo_t3_1 开始: {}", System.currentTimeMillis());
                    R<Map<String, String>> r1 = feignSrvC.t3_1();
                    log.info("远程调用 foo_t3_1 结果: {} , 结束: {}", r1, System.currentTimeMillis());
                    r.getData().putAll(r1.getData());
                    return r1;
                }, threadPoolTaskExecutor),
                CompletableFuture.supplyAsync(() -> {
                    log.info("远程调用 foo_t3_2 开始: {}", System.currentTimeMillis());
                    R<Map<String, String>> r2 = feignSrvC.t3_2();
                    log.info("远程调用 foo_t3_2 结果: {} , 结束: {}", r2, System.currentTimeMillis());
                    r.getData().putAll(r2.getData());
                    return r2;
                }, threadPoolTaskExecutor),
                CompletableFuture.supplyAsync(() -> {
                    log.info("远程调用 foo_t3_3 开始: {}", System.currentTimeMillis());
                    R<Map<String, String>> r3 = feignSrvC.t3_3();
                    log.info("远程调用 foo_t3_3 结果: {} , 结束: {}", r3, System.currentTimeMillis());
                    r.getData().putAll(r3.getData());
                    return r3;
                }, threadPoolTaskExecutor)
        ).join();
        log.info("总耗时: {}", (System.currentTimeMillis() - start) / 1000);
        return r;
    }

}
