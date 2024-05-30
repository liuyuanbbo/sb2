package org.zmz.c.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.common.R;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/foo")
public class FooController {

    @GetMapping("/t1")
    public R<Map<String, Object>> t1(HttpServletResponse response) {
        response.setHeader("error", "This is error");
        return R.ok(Map.of("code", "000000", "msg", "", "data", "OK"));
    }

}
