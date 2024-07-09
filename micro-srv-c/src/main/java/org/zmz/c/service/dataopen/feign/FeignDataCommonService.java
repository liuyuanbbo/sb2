package org.zmz.c.service.dataopen.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zmz.c.qo.dataopen.ModelInfoQo;

import java.util.Map;

@FeignClient(name = "${data.service.name:DataService}", url = "${data.service.url:}", path = "/dataservice",
        configuration = FeignDataCommonConfiguration.class)
public interface FeignDataCommonService {
    @RequestMapping(value = "/ModelMgrController/queryModelInfoBatch", method = RequestMethod.GET)
    Map<?, ?> queryAllModelInfoBatch(ModelInfoQo params);
}
