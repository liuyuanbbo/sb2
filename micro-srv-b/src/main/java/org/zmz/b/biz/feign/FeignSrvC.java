package org.zmz.b.biz.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.zmz.common.R;

import java.util.Map;

@FeignClient(name = "${srvc.service.name:srvc}", url = "${srvc.service.url:http://localhost:19523}", path = "/")
public interface FeignSrvC {

    @GetMapping("/foo/t1")
    Response fooT1();

    @GetMapping("/bar/t1")
    Response bar1();

    @GetMapping("/bar/t2")
    Response bar2();

    @GetMapping("/foo/t3_1")
    R<Map<String, String>> t3_1();

    @GetMapping("/foo/t3_2")
    R<Map<String, String>> t3_2();

    @GetMapping("/foo/t3_3")
    R<Map<String, String>> t3_3();

}
