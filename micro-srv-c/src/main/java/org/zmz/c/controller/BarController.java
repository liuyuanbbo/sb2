package org.zmz.c.controller;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.ResponseUtil;
import org.zmz.common.R;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/bar")
@Slf4j
public class BarController {

    @GetMapping("/t1")
    public R<Map<?, ?>> t1(HttpServletResponse response) {
        String json = """
                {"success":true,"code":0,"msg":"http://135.32.120.118/mydiskdx/applyDownload.html#nodeIds=2c9a31a88f22c248018fec50a95a0403&token=97583a6c69dc2512640e973f1a55697a","data":null}
                """;
        Map<String, Object> map = JSONUtil.toBean(json, new TypeReference<>() {
        }, true);
        Object data = map.get("data");
        Object msg = map.get("msg");
        if (data == null) {
            map.put("data", msg);
        }
        map.put("test", "专门为了测试");
        String jsonStr = JSONUtil.toJsonStr(map);
        String urlEncodeJson = URLEncodeUtil.encode(jsonStr);
        response.addHeader("response", urlEncodeJson);
        log.info("开始调用: {} , 方法: {}", this.getClass(), "bar1()");

        String urlJson = response.getHeader("response");
        log.info("urlJson：{}", urlJson);
        return R.ok(map);
    }

    @GetMapping("/t2")
    public R<ResponseUtil> t2(HttpServletResponse response) {
        String json = """
                {"success":true,"code":0,"msg":"http://135.32.120.118/mydiskdx/applyDownload.html#nodeIds=2c9a31a88f22c248018fec50a95a0403&token=97583a6c69dc2512640e973f1a55697a","data":null}
                """;
        Map<String, Object> map = JSONUtil.toBean(json, new TypeReference<>() {
        }, true);
        ResponseUtil responseUtil = new ResponseUtil();
        Object data = map.get("data");
        Object msg = map.get("msg");
        responseUtil.setResultObject(data == null ? msg : data);
        responseUtil.setResultMsg(msg.toString());
        String jsonResponse = JsonUtil.toJson(responseUtil);
        String urlEncodeJson = URLEncodeUtil.encode(jsonResponse);
        response.setHeader("externalFileSystemResponse", urlEncodeJson);
        return R.ok(responseUtil);
    }

}
