package org.zmz.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Container;
import org.apache.catalina.core.StandardHost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
@EnableRedisRepositories
public class MicroSrvA_App {

    public static void main(String[] args) {
        log.info("springboot 在端口 {} 启动", 9521);
        SpringApplication.run(MicroSrvA_App.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> errorReportValveCustomizer() {
        return (factory) -> factory.addContextCustomizers(context -> {
            final Container parent = context.getParent();
            if (parent instanceof StandardHost) {
                // above class FQCN
                ((StandardHost) parent).setErrorReportValveClass(
                        "org.jeecg.config.init.CustomTomcatErrorValve");
            }
        });
    }
}
