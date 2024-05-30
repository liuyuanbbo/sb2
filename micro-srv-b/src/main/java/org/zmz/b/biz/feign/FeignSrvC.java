package org.zmz.b.biz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.zmz.common.R;

import java.util.Map;

@FeignClient(name = "${srvc.service.name:srvc}", url = "${srvc.service.url:http://localhost:9523}", path = "/")
public interface FeignSrvC {

    @GetMapping("/foo/t1")
    R<Map<String,Object>> fooT1();

}
