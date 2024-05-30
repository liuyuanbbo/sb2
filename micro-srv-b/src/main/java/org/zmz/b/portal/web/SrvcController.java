package org.zmz.b.portal.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.b.biz.feign.FeignSrvC;
import org.zmz.common.R;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/srvc")
public class SrvcController {

    @Resource
    FeignSrvC feignSrvC;

    @GetMapping("/fooT1")
    public R<Map<String, Object>> fooT1() {
        return feignSrvC.fooT1();
    }

}
