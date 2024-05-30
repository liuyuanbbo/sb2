package org.zmz.b.portal.web;

import feign.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.b.biz.feign.FeignSrvC;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/srvc")
public class SrvcController {

    @Resource
    FeignSrvC feignSrvC;

    @GetMapping("/fooT1")
    public Response fooT1(HttpServletResponse response) {
        response.addHeader("error", "srvB error");
        Response resp = feignSrvC.fooT1();
        return resp;
    }

}
