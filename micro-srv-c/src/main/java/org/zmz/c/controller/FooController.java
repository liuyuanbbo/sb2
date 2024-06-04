package org.zmz.c.controller;

import cn.hutool.core.net.URLEncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
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
        String s = "嗲话发简历阿达用户不存在";
        String us = URLEncodeUtil.encode(s);

        log.info("{}  -----  {}", us, us.length());
        response.setHeader("error", us);
        return R.ok(Map.of("code", "000000", "msg", "", "data", "OK"));
    }

}
