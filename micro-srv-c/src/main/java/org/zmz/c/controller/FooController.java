package org.zmz.c.controller;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {

    @GetMapping("/t1")
    public R<Map<String, Object>> t1(HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(1, 255).forEach(sb::append);
        log.info("{}", sb);
        log.info("{}", sb.length());
        String s = "嗲话发简历阿达用户不存在dadafafbkwfnwfnkwfnwfnlwfnwlfnwlfnwlfnwlfnwlfnwlfnwlfnlwnflwfnlwnflw饭卡别烦我开办费课外班反馈无法别看我不管看我别看我";
        String us = URLEncodeUtil.encode(s);

        String json = """
                {
                    "resultCode": "0",
                    "resultMsg": null,
                    "resultObject": {
                        "comAcctId": 1351,
                        "comments": "q125",
                        "createDate": 1717484169000,
                        "createType": "virtual",
                        "dataCntType": "3",
                        "datasourceId": 59752,
                        "dirId": null,
                        "domain": null,
                        "grpId": 118302,
                        "keyCode": "bill_user_flag",
                        "layer": null,
                        "metaTableId": 24709,
                        "objectAlias": null,
                        "objectCode": "q125",
                        "objectId": 27360,
                        "objectName": "q125",
                        "operId": 20290,
                        "orderNo": null,
                        "parentId": -1,
                        "primaryKey": "146892",
                        "projectId": 10007,
                        "statusCd": "你好",
                        "tableCode": "dwd_prod_inst_m",
                        "tableType": "gp",
                        "version": null
                    },
                    "success": true
                }
                """;

        log.info("{}  -----  {}", us, us.length());
        String jsonUrlEncode = URLEncodeUtil.encode(json);
        log.info("jsonUrlEncode length {} ", jsonUrlEncode.length());
        response.setHeader("error", jsonUrlEncode);
        return R.ok(Map.of("code", "000000", "msg", "", "data", json));
    }


    @GetMapping("/t2")
    public void t2(HttpServletResponse response) {
        Map<String, Object> map = Map.of("code", 200, "msg", "OK");
        try (PrintWriter writer = response.getWriter()) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            String jsonStr = JSONUtil.toJsonStr(map);
            writer.println(jsonStr);
            writer.flush();
        } catch (Exception e) {
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
        return R.ok(Map.of("foo", "t3"));
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
        return R.ok(Map.of("foo_t3_1", "t3_1"));
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
        return R.ok(Map.of("foo_t3_2", "t3_2"));
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
        log.info("reqId : {} , t3-3 当前线程: {} 开始: {}", reqId, Thread.currentThread().getName(), System.currentTimeMillis());
        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(3);
        log.info("t3-3 当前线程: {} 结束: {}", Thread.currentThread().getName(), System.currentTimeMillis());
        return R.ok(Map.of("foo_t3_3", "t3_3"));
    }

}
