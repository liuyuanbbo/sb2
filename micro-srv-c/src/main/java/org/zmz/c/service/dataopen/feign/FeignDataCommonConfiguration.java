package org.zmz.c.service.dataopen.feign;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共配置，使用用户名密码，不加cookie
 */
@Configuration
@EnableFeignClients
public class FeignDataCommonConfiguration {

    @Bean
    public RequestInterceptor feignDataCommonRequestInterceptor() {
        return new FeignDataCommonRequestInterceptor();
    }
}