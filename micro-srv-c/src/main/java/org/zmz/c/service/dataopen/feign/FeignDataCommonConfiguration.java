package org.zmz.c.service.dataopen.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * 公共配置，使用用户名密码，不加cookie
 */
public class FeignDataCommonConfiguration {

    @Bean
    public RequestInterceptor feignDataCommonRequestInterceptor() {
        return new FeignDataCommonRequestInterceptor();
    }
}