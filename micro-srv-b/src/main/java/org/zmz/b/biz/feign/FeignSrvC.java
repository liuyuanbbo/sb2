package org.zmz.b.biz.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${srvc.service.name:srvc}", url = "${srvc.service.url:http://localhost:9523}", path = "/")
public interface FeignSrvC {

    @GetMapping("/foo/t1")
    Response fooT1();

}
