package org.zmz.b.biz.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.UUID;

@Component
@Slf4j
public class SrvcFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info(">>>>>>>>>>开始进入Srvc Feign调用拦截器>>>>>>>>>>");
        requestTemplate.header("reqId", UUID.randomUUID().toString());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestTemplate.header("token", (String) requestAttributes.getAttribute("token", 0));
        }
    }
}
